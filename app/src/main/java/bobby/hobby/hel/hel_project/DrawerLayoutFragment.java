package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseNavViewListChildFragment;
import bobby.hobby.hel.hel_project.base.view.fragment.master.BaseNavViewListHostFragment;

public class DrawerLayoutFragment extends BaseNavViewListHostFragment<FragmentToFragmentDrawerViewModel, FragmentToActivityViewModel> {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentsViewModel.dataAccross.observe(this, data -> {
            mViewModel.dataAccross.setValue(data);
        });
    }

    @Override
    protected Class<FragmentToFragmentDrawerViewModel> returnFragmentsViewModel() {
        return FragmentToFragmentDrawerViewModel.class;
    }

    @Override
    protected Class<FragmentToActivityViewModel> returnViewModel() {
        return FragmentToActivityViewModel.class;
    }

    @Override
    protected BaseNavViewListChildFragment returnLeftChild() {
        return new DrawerChildOneFragment();
    }

    @Override
    protected BaseNavViewListChildFragment returnRightChild() {
        return new DrawerChildTwoFragment();
    }

    //Enforce user (that mean you) to give a layout that must have 3 ids: "left_child_drawer",
    // "right_child_drawer" and "guideline", refer to BaseNavViewListHostFragment for more details
    @Override
    protected int returnDrawerHostLayout() {
        return R.layout.fragment_navview_list;
    }

    @Override
    public BaseFragment returnFragment() {
        return this;
    }
}