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
