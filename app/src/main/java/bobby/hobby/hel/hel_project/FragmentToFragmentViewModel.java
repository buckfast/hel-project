package bobby.hobby.hel.hel_project;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public class FragmentToFragmentViewModel extends BaseViewModel {
    public MutableLiveData<List<String>> data = new MutableLiveData<>();
    public MutableLiveData<Integer> position = new MutableLiveData<>();
    public FragmentToFragmentViewModel(@NonNull Application application) {
        super(application);
    }
}
