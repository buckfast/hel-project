package bobby.hobby.hel.hel_project;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public class FragmentToActivityViewModel extends BaseViewModel {
    public MutableLiveData<String> data = new MutableLiveData<>();
    public FragmentToActivityViewModel(@NonNull Application application) {
        super(application);
        data.setValue("YOYO");
    }
}
