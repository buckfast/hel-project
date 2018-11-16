package bobby.hobby.hel.hel_project.ui.fragment;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import bobby.hobby.hel.hel_project.MainActivity;
import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.Util;
import bobby.hobby.hel.hel_project.base.view.activity.BaseActivity;
import bobby.hobby.hel.hel_project.base.view.activity.BaseDrawerActivity;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.ui.AuthActivity;
import bobby.hobby.hel.hel_project.ui.viewmodel.AuthFragmentViewModel;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

public class LoginFragment extends BaseFragment<AuthFragmentViewModel>{
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
                Log.d("asd", "login click");
            }
        });

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container, new SwipeFragment()).commit();
            }
        });

        mViewModel.loggedIn.setValue(false);
        //Log.d("asd", String.valueOf(mViewModel.currentUser.getValue()));

        mViewModel.clearTitle.setValue(!mViewModel.clearTitle.getValue());

        mViewModel.loggedIn.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean loggedin) {
                if (loggedin == true) {
                    Log.d("asd", "from login fargment: cughh");
                    //mViewModel.loggedIn.removeObserver(this);
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    //context.getSupportFragmentManager().beginTransaction().replace(R.id.container, new TabHostFragment()).commit();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (BaseActivity) context;
    }

    @Override
    protected Class<AuthFragmentViewModel> returnViewModel() {
        return AuthFragmentViewModel.class;
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
    }
}