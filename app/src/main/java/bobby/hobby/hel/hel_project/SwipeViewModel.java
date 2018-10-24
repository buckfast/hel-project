package bobby.hobby.hel.hel_project;

import android.app.Application;
import android.support.annotation.NonNull;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;
import bobby.hobby.hel.hel_project.repository.internal.SocketClient;

public class SwipeViewModel extends BaseViewModel {
    //Not doing anything
    public SwipeViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected SocketClient.EventListener returnSocketListener() {
        return null;
    }
}
