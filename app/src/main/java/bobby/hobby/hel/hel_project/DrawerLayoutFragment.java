package bobby.hobby.hel.hel_project;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseNavViewListChildFragment;
import bobby.hobby.hel.hel_project.base.view.fragment.master.BaseNavViewListHostFragment;

public class DrawerLayoutFragment extends BaseNavViewListHostFragment<FragmentToFragmentViewModel, FragmentToActivityViewModel> {

    @Override
    protected Class<FragmentToFragmentViewModel> returnFragmentsViewModel() {
        return FragmentToFragmentViewModel.class;
    }

    @Override
    protected Class<FragmentToActivityViewModel> returnViewModel() {
        return FragmentToActivityViewModel.class;
    }

    @Override
    protected BaseNavViewListChildFragment returnLeftChild() {
        DrawerChildOneFragment fragment = new DrawerChildOneFragment();
        fragment.returnHostFragment(this);
        return fragment;
    }

    @Override
    protected BaseNavViewListChildFragment returnRightChild() {
        DrawerChildTwoFragment fragment = new DrawerChildTwoFragment();
        fragment.returnHostFragment(this);
        return fragment;
    }

    @Override
    public BaseFragment returnFragment() {
        return this;
    }
}
