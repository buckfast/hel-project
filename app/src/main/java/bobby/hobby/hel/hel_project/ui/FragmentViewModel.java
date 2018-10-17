package bobby.hobby.hel.hel_project.ui;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;
import bobby.hobby.hel.hel_project.ui.model.DrawerListItem;
import bobby.hobby.hel.hel_project.ui.model.EventItem;

public class FragmentViewModel extends BaseViewModel {
    MutableLiveData<View> lastView = new MutableLiveData<>();
    //MutableLiveData<Integer> lastPosition = new MutableLiveData<>();
    MutableLiveData<Integer> listPosition = new MutableLiveData<>();
    MutableLiveData<List<DrawerListItem>> drawerList = new MutableLiveData<>();
    MutableLiveData<List<EventItem>>  eventList = new MutableLiveData<>();

    public FragmentViewModel(@NonNull Application application) {
        super(application);
    }
}
