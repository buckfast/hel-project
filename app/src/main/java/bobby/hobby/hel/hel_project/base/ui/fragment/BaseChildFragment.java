package bobby.hobby.hel.hel_project.base.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import bobby.hobby.hel.hel_project.base.ui.viewmodel.BaseViewModel;

public abstract class BaseChildFragment<T extends BaseViewModel> extends BaseFragment<T> {
    protected T mFragmentsViewModel;
    private BaseHostFragment mHostFragment;

    protected Class<T> returnFragmentsViewModel(){return null;}

    public void returnHostFragment(BaseHostFragment fragment) { mHostFragment = fragment; }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (returnFragmentsViewModel() != null) {
            mFragmentsViewModel = ViewModelProviders.of(mHostFragment).get(returnFragmentsViewModel());
        }
    }
}
