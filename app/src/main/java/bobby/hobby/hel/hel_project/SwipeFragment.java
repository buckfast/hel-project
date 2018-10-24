package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bobby.hobby.hel.hel_project.base.view.fragment.master.BaseSwipeHostFragment;

//Viewmodel is there for show, not rlly using it, adding it here so that we can pass the generic SwipeChildFragment type
public class SwipeFragment extends BaseSwipeHostFragment<SwipeViewModel, SwipeViewModel, SwipeChildFragment> {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_swipe_host, container, false);
    }

    @Override
    protected int returnViewPagerId() {
        return R.id.viewpager;
    }

    @Override
    protected int returnNumberOfFragment() {
        return 10;
    }

    @Override
    protected FragmentManager returnFragmentManager() {
        return getChildFragmentManager();
    }

    @Override
    protected Class<SwipeChildFragment> returnChildFragmentClass() {
        return SwipeChildFragment.class;
    }


    @Override
    protected Class<SwipeViewModel> returnFragmentsViewModel() {
        return SwipeViewModel.class;
    }

    @Override
    protected Class<SwipeViewModel> returnViewModel() {
        return null;
    }
}
