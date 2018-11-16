package bobby.hobby.hel.hel_project.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.Util;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;


public class RegisterFragment extends BaseFragment<FragmentViewModel> {

    private EditText nickname, password, email;
    private Button signupButton;


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
    }

    @Override
    protected Class<FragmentViewModel> returnViewModel() {
        return FragmentViewModel.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel.clearTitle.setValue(!mViewModel.clearTitle.getValue());

        mViewModel.signedup.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean b) {
                if (b) {
                    Log.d("asd", "from register::"+String.valueOf(b));
                    Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container, new TabHostFragment()).commit();
                    mViewModel.signedup.removeObserver(this);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    private void attemptSignup() {
        User user = new User();
        user.setName(nickname.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.setHobbies(mViewModel.signupLikedHobbies);
        mViewModel.signup(user);
    }
}
