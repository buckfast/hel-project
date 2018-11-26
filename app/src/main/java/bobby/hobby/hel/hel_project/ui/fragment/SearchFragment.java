package bobby.hobby.hel.hel_project.ui.fragment;

import android.support.v4.app.Fragment;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

public class SearchFragment extends BaseFragment<FragmentViewModel>{
    public SearchFragment() {
    }

    @Override
    protected Class<FragmentViewModel> returnViewModel() {
        return FragmentViewModel.class;
    }
}
