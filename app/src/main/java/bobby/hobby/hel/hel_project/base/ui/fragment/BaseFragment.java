package bobby.hobby.hel.hel_project.base.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.Objects;

import bobby.hobby.hel.hel_project.base.ui.viewmodel.BaseViewModel;


public abstract class BaseFragment<T extends BaseViewModel> extends Fragment {
    protected T mViewModel;

    protected Class<T> returnViewModel() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (returnViewModel() != null) {
            mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(returnViewModel());
        }
    }
}
