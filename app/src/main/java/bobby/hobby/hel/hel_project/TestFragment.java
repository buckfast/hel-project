package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.repository.internal.model.User;

public class TestFragment extends BaseFragment<ViewModelTest> implements BaseFragment.LongRunningTaskBehaviour, BaseFragment.SwipeToRefreshBehaviour{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User newUser = new User();
        newUser.setPassword("hoangl2@gmail.com");
        newUser.setEmail("hoangl2@gmail.com");
        mViewModel.login(newUser);
        mViewModel.user.observe(this, user -> {
            String token = Objects.requireNonNull(user).getToken();
            Toast.makeText(getActivity(), token, Toast.LENGTH_SHORT).show();
        });
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(),android.R.color.holo_blue_bright),
                ContextCompat.getColor(getActivity(),android.R.color.holo_green_light),
                ContextCompat.getColor(getActivity(),android.R.color.holo_orange_light),
                ContextCompat.getColor(getActivity(),android.R.color.holo_red_light));

    }

    @Override
    protected LongRunningTaskBehaviour returnLongRunningTaskBehaviour() {
        return this;
    }

    @Override
    protected SwipeToRefreshBehaviour returnSwipeToRefresh() {
        return this;
    }

    @Override
    protected Class<ViewModelTest> returnViewModel() {
        return ViewModelTest.class;
    }

    @Override
    public int returnProgressBarContainer() {
        return R.id.container;
    }

    @Override
    public Fragment returnProgressBarFragment() {
        return new TestProgressBarFragment();
    }

    @Override
    public int returnSwipeRefreshLayout() {
        return R.id.refresh;
    }

    @Override
    public void refresh(SwipeRefreshLayout layout) {
        Toast.makeText(getActivity(), "Refreshed", Toast.LENGTH_SHORT).show();
        layout.setRefreshing(false);
    }
}
