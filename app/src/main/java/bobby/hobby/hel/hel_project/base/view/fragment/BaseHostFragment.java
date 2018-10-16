package bobby.hobby.hel.hel_project.base.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Objects;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseHostFragment<T extends BaseViewModel, V extends BaseViewModel> extends BaseFragment<V> {
    protected T mFragmentsViewModel;

    protected abstract Class<T> returnFragmentsViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (returnFragmentsViewModel() != null) {
            mFragmentsViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(returnFragmentsViewModel());
        }
    }
}
