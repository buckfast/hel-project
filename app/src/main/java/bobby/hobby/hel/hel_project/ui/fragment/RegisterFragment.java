package bobby.hobby.hel.hel_project.ui.fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import bobby.hobby.hel.hel_project.MainActivity;
import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.Util;
import bobby.hobby.hel.hel_project.base.view.activity.BaseActivity;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

/**
 * Description: Register fragment
 * Features:
 * - holds views for register page layout and connects to base net code via viewmodel
 */
public class RegisterFragment extends BaseFragment<FragmentViewModel> implements BaseFragment.LongRunningTaskBehaviour {

    private EditText nickname, password, email;
    private Button signupButton;
    private BaseActivity context;

    public RegisterFragment() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Util.fixBgRepeat(view.findViewById(R.id.bottom_image));
        password = view.findViewById(R.id.password);
        email = view.findViewById(R.id.email);
        nickname = view.findViewById(R.id.nickname);
        signupButton = view.findViewById(R.id.signup_button);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignup();
            }
        });

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    Util.hideKeyboard(getActivity(), textView);
                    attemptSignup();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected Class<FragmentViewModel> returnViewModel() {
        return FragmentViewModel.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel.clearTitle.setValue(!mViewModel.clearTitle.getValue());

        /**
         * observer for listening to auth error messages from server
         */
        mViewModel.authError.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer == 2) {
                    Toast.makeText(getActivity(), "error: check your credentials", Toast.LENGTH_SHORT).show();
                    mViewModel.authError.setValue(-1);
                }
            }
        });

        /**
         * observes userdata. launches the main activity after register is successful and user data is fully fetched
         */
        mViewModel.currentUserRegister.observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                Log.d("asd", "from register::" + String.valueOf(user));
                mViewModel.currentUserRegister.removeObservers(context);
                //if (signedup) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("token", mViewModel.token);
                intent.putExtra("user", user);
                startActivity(intent);
                //getActivity().finish();

                //}
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (BaseActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    private void attemptSignup() {
        mViewModel.longRunningTask(true);
        User user = new User();
        user.setName(nickname.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.setHobbies(mViewModel.signupLikedHobbies);
        mViewModel.signup(user);
    }

    @Override
    public int returnProgressBarContainer() {
        return R.id.register_progressbar_container;
    }

    @Override
    public Fragment returnProgressBarFragment() {
        return new ProgressBarFragment();
    }

    @Override
    protected LongRunningTaskBehaviour returnLongRunningTaskBehaviour() {
        return this;
    }

}
