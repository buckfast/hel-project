package bobby.hobby.hel.hel_project.base.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.Objects;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

/**
 * Description: This class setup viewmodel connection between fragment and activity
 * Works with:
 * {@link bobby.hobby.hel.hel_project.base.view.activity.BaseActivity}
 */

public abstract class BaseFragment<T extends BaseViewModel> extends Fragment {
    protected T mViewModel;

    protected abstract Class<T> returnViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (returnViewModel() != null) {
            mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(returnViewModel());
        }
    }
}
