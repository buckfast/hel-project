package bobby.hobby.hel.hel_project.base.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.Objects;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

/**
 * Description: This class setup viewmodel connection between fragment and activity
 * Works with:
 * {@link bobby.hobby.hel.hel_project.base.view.activity.BaseActivity}
 */

public abstract class BaseFragment<T extends BaseViewModel> extends Fragment {
    protected T mViewModel;
    protected LongRunningTaskBehaviour progressBehaviour;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected SwipeToRefreshBehaviour swipeToRefreshBehaviour;

    protected abstract Class<T> returnViewModel();
    protected LongRunningTaskBehaviour returnLongRunningTaskBehaviour(){
        return null;
    }

    protected SwipeToRefreshBehaviour returnSwipeToRefresh() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (returnViewModel() != null) {
            mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(returnViewModel());
        }
        if (returnLongRunningTaskBehaviour() != null) {
            progressBehaviour = returnLongRunningTaskBehaviour();
        }
        if (returnSwipeToRefresh() != null) {
            swipeToRefreshBehaviour = returnSwipeToRefresh();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (progressBehaviour != null) {
            if (progressBehaviour.returnProgressBarContainer() != -1 && progressBehaviour.returnProgressBarFragment() != null) {
                Fragment f = progressBehaviour.returnProgressBarFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(progressBehaviour.returnProgressBarContainer(), f)
                        .commit();
            }
        }
        if (swipeToRefreshBehaviour != null) {
            swipeRefreshLayout = view.findViewById(swipeToRefreshBehaviour.returnSwipeRefreshLayout());
            swipeRefreshLayout.setOnRefreshListener(() -> swipeToRefreshBehaviour.refresh(swipeRefreshLayout));
        }
    }

    public interface LongRunningTaskBehaviour {
        int returnProgressBarContainer();
        Fragment returnProgressBarFragment();
    }

    public interface SwipeToRefreshBehaviour {
        int returnSwipeRefreshLayout();
        void refresh(SwipeRefreshLayout layout);
    }
}
