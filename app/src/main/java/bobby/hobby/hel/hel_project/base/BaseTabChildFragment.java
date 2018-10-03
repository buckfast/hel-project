package bobby.hobby.hel.hel_project.base;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseTabChildFragment<T extends BaseViewModel> extends BaseFragment<T> {
    protected T mTabViewModel;
    private BaseTabHostFragment mFragment;

    protected Class<T> returnTabViewModel() {
        return null;
    }

    public void returnTabHostFragment(BaseTabHostFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (returnTabViewModel() != null) {
            mTabViewModel = ViewModelProviders.of(mFragment).get(returnTabViewModel());
        }
    }
}
