package bobby.hobby.hel.hel_project.ui;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public class FragmentViewModel extends BaseViewModel {
    MutableLiveData<Integer> listPosition = new MutableLiveData<>();
    MutableLiveData<List<String>> drawerList = new MutableLiveData<>();
    public FragmentViewModel(@NonNull Application application) {
        super(application);
    }
}
