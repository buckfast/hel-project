package bobby.hobby.hel.hel_project.base.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import bobby.hobby.hel.hel_project.base.ui.viewmodel.BaseViewModel;

public abstract class BaseHostFragment<T extends BaseViewModel, V extends BaseViewModel> extends BaseFragment<V> {
    protected T mFragmentsViewModel;

    protected Class<T> returnFragmentsViewModel(){return null;}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (returnFragmentsViewModel() != null) {
            mFragmentsViewModel = ViewModelProviders.of(this).get(returnFragmentsViewModel());
        }
    }
}
