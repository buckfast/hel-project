package bobby.hobby.hel.hel_project.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseProgressBarFragment;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

/**
 * Description: Progress bar fragment implemented from base code
 */
public class ProgressBarFragment extends BaseProgressBarFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_progressbar, container, false);
    }

    @Override
    protected Class returnViewModel() {
        return FragmentViewModel.class;
    }


}
