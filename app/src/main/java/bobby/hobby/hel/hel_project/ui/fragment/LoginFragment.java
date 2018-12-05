package bobby.hobby.hel.hel_project.ui.fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import bobby.hobby.hel.hel_project.MainActivity;
import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.Util;
import bobby.hobby.hel.hel_project.base.view.activity.BaseActivity;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

public class LoginFragment extends BaseFragment<FragmentViewModel> implements BaseFragment.LongRunningTaskBehaviour{
    private TextView newAccount;
    private TextInputEditText password;
    private EditText email;
    private Button loginButton;
    private BaseActivity context;

    public LoginFragment() {

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
        loginButton = view.findViewById(R.id.login_button);
        newAccount = view.findViewById(R.id.newaccount);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.clearFocus();
                email.clearFocus();
                //InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                //imm.hideSoftInputFromWindow(email.getWindowToken(), 0);
                //imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
                attemptLogin();
                //Log.d("asd", "login click");
            }
        });

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container, new SwipeFragment()).commit();
            }
        });

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    Util.hideKeyboard(getActivity(), textView);
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        //mViewModel.loggedIn.setValue(false);
        //Log.d("asd", String.valueOf(mViewModel.currentUser.getValue()));

        mViewModel.clearTitle.setValue(!mViewModel.clearTitle.getValue());

        mViewModel.authError.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer == 1) {
                    Toast.makeText(getActivity(), "incorrect email or password", Toast.LENGTH_SHORT).show();
                    mViewModel.authError.setValue(-1);
                }
            }
        });

        mViewModel.currentUser.observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                mViewModel.currentUser.removeObservers(context);
                //if (loggedin == true) {

               // Log.d("asd", "from login fargment: cughh");
                    //mViewModel.loggedIn.removeObserver(this);
                    Intent intent = new Intent(context, MainActivity.class);
                    //Log.d("asd", "log in fargment, token: "+String.valueOf(mViewModel.token));
                    intent.putExtra("token", mViewModel.token);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    //getActivity().finish();
                    //context.getSupportFragmentManager().beginTransaction().replace(R.id.container, new TabHostFragment()).commit();
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
    protected Class<FragmentViewModel> returnViewModel() {
        return FragmentViewModel.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

/*
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuItem item = menu.findItem(R.id.logout);
        item.setVisible(false);
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    private void attemptLogin() {
        User user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        mViewModel.login(user);
        mViewModel.longRunningTask(true);
    }

    @Override
    public int returnProgressBarContainer() {
        return R.id.progressbar_container;
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
