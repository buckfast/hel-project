package bobby.hobby.hel.hel_project.base.view.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {
    protected T mViewModel;

    protected abstract Class<T> returnViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (returnViewModel() != null) {
            mViewModel = ViewModelProviders.of(this).get(returnViewModel());
        }
    }
}
