package bobby.hobby.hel.hel_project.base.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.Objects;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

/**
 * Description: This class setup the appear/disappear of the progressbar
 * Works with:
 * {@link bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment}
 */

public abstract class BaseProgressBarFragment<T extends BaseViewModel> extends BaseFragment<T> {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mViewModel != null) {
            mViewModel.getRunningTaskFlag().observe(this, value -> {
                if (value) {
                    Objects.requireNonNull(getView()).setVisibility(View.VISIBLE);
                } else {
                    Objects.requireNonNull(getView()).setVisibility(View.GONE);
                }
            });
        }
    }
}
