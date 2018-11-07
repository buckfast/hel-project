package bobby.hobby.hel.hel_project;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import bobby.hobby.hel.hel_project.base.API.BaseClient;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;
import bobby.hobby.hel.hel_project.repository.internal.SocketClient;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import okhttp3.ResponseBody;

public class ViewModelTest extends BaseViewModel {
    public MutableLiveData<User> user = new MutableLiveData<>();
    public ViewModelTest(@NonNull Application application) {
        super(application);
    }

    @Override
    protected SocketClient.EventListener returnSocketListener() {
        return null;
    }

    public void login(User loginuser) {
        setRunningLongTaskFlag(true);
        mRepository.login(loginuser, new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                user.postValue(response);
                postRunningLongTaskFlag(false);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                postRunningLongTaskFlag(false);
            }
        });
    }
}
