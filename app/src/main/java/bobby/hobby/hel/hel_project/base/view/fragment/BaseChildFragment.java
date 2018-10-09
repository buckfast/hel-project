package bobby.hobby.hel.hel_project.base.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseChildFragment<T extends BaseViewModel> extends BaseFragment<T> {
    protected T mFragmentsViewModel;
    private BaseHostFragment mHostFragment;

    protected abstract Class<T> returnFragmentsViewModel();

    @Override
    protected final Class<T> returnViewModel() {
        return null;
    }

    public void returnHostFragment(BaseHostFragment fragment) { mHostFragment = fragment; }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (returnFragmentsViewModel() != null && mHostFragment != null) {
            mFragmentsViewModel = ViewModelProviders.of(mHostFragment).get(returnFragmentsViewModel());
        }
    }
}
