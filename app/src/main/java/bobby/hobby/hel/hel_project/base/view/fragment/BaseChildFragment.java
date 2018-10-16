package bobby.hobby.hel.hel_project.base.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Objects;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseChildFragment<T extends BaseViewModel> extends BaseFragment<T> {
    protected T mFragmentsViewModel;

    protected abstract Class<T> returnFragmentsViewModel();

    @Override
    protected final Class<T> returnViewModel() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (returnFragmentsViewModel() != null) {
            mFragmentsViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(returnFragmentsViewModel());
        }
    }
}
