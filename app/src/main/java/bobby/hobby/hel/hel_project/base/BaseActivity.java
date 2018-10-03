package bobby.hobby.hel.hel_project.base;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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
