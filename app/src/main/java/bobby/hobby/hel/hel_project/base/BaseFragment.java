package bobby.hobby.hel.hel_project.base;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.Objects;

public abstract class BaseFragment<T extends BaseViewModel> extends Fragment {
    protected T mViewModel;

    public abstract Class<T> returnViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(returnViewModel());
    }
}
