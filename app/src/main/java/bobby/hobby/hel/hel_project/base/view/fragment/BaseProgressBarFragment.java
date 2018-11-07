package bobby.hobby.hel.hel_project.base.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.Objects;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

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