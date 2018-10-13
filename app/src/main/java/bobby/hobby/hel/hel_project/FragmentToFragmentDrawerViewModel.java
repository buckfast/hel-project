package bobby.hobby.hel.hel_project;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public class FragmentToFragmentDrawerViewModel extends BaseViewModel {
    MutableLiveData<List<String>> drawerList = new MutableLiveData<>();
    MutableLiveData<Integer> position = new MutableLiveData<>();
    MutableLiveData<String> dataAccross = new MutableLiveData<>();
    public FragmentToFragmentDrawerViewModel(@NonNull Application application) {
        super(application);
    }
}
