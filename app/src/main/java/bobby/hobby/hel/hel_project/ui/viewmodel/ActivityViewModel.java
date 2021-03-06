package bobby.hobby.hel.hel_project.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;
import bobby.hobby.hel.hel_project.repository.internal.SocketClient;
import bobby.hobby.hel.hel_project.repository.internal.model.User;

public class ActivityViewModel extends BaseViewModel {
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<Integer> accountButtonClick = new MutableLiveData<>();
    public MutableLiveData<Boolean> logoutClick = new MutableLiveData<>();
    public MutableLiveData<User> currentUser = new MutableLiveData<>();

    public MutableLiveData<Boolean> hostViewCreated = new MutableLiveData<>();
    //public MutableLiveData<EventList> linkedEvents = new MutableLiveData<>();
    //public MutableLiveData<List<String>> hobbyList = new MutableLiveData<>();
    //public MutableLiveData<User> currentUser = new MutableLiveData<>();

    public ActivityViewModel(@NonNull Application application) {
        super(application);
        logoutClick.setValue(false);
        hostViewCreated.setValue(false);
    }

    public void logout() {
        mRepository.logout();
        logoutClick.setValue(true);
    }

    @Override
    protected SocketClient.EventListener returnSocketListener() {
        return null;
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }


}
