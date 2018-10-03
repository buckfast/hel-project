package bobby.hobby.hel.hel_project.base.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import bobby.hobby.hel.hel_project.base.ui.viewmodel.BaseViewModel;

public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {
    protected T mViewModel;

    protected Class<T> returnViewModel() {
        return null;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (returnViewModel() != null) {
            mViewModel = ViewModelProviders.of(this).get(returnViewModel());
        }
    }
}
