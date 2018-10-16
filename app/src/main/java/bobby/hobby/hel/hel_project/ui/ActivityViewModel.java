package bobby.hobby.hel.hel_project.ui;

import android.support.annotation.NonNull;
import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public class ActivityViewModel extends BaseViewModel {
    MutableLiveData<String> data = new MutableLiveData<>();
    public ActivityViewModel(@NonNull Application application) {
        super(application);
    }
}
