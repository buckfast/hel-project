package bobby.hobby.hel.hel_project.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.TextView;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;


public class RegisterFragment extends BaseFragment<FragmentViewModel> {

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

    }

    @Override
    protected Class<FragmentViewModel> returnViewModel() {
        return FragmentViewModel.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    private void attemptSignup() {
        User user = new User();
        user.setEmail("hoangl@mail.com");
        user.setPassword("hoangl@gmail.com");
        user.setHobbies(mViewModel.signupLikedHobbies.getValue());
        mViewModel.signup(user);
    }
}
