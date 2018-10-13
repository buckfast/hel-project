package bobby.hobby.hel.hel_project;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public class FragmentToFragmentTabViewModel extends BaseViewModel {
    public MutableLiveData<String> tabData = new MutableLiveData<>();
    public MutableLiveData<String> dataAccross = new MutableLiveData<>();
    public FragmentToFragmentTabViewModel(@NonNull Application application) {
        super(application);
        tabData.setValue("Hello");
    }
}
