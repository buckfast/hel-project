package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import bobby.hobby.hel.hel_project.base.API.BaseClient;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseTabChildFragment;
import bobby.hobby.hel.hel_project.repository.Repository;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import okhttp3.ResponseBody;

public class TestChildFragment extends BaseTabChildFragment<FragmentToFragmentTabViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn = view.findViewById(R.id.button1);
        TextView txt = view.findViewById(R.id.result1);

        btn.setOnClickListener(v -> {
            if (Objects.equals(mFragmentsViewModel.tabData.getValue(), "Hello")) {
                mFragmentsViewModel.tabData.setValue("world!");
            } else {
                mFragmentsViewModel.tabData.setValue("Hello");
            }

            User newUser = new User();
            newUser.setEmail("hoangl2@gmail.com");
            newUser.setPassword("hoangl2@gmail.com");
            mFragmentsViewModel.getUser();
//            mFragmentsViewModel.login(newUser);
        });

        mFragmentsViewModel.user.observe(this, user -> {
            Toast.makeText(getContext(), Objects.requireNonNull(user).getToken(), Toast.LENGTH_SHORT).show();
        });

        mFragmentsViewModel.tabData.observe(this, txt::setText);

        mFragmentsViewModel.dataAccross.observe(this, data -> {
            Toast.makeText(getContext(), "This is from TestChildFragment, the data is: "+data, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected Class<FragmentToFragmentTabViewModel> returnFragmentsViewModel() {
        return FragmentToFragmentTabViewModel.class;
    }
}
