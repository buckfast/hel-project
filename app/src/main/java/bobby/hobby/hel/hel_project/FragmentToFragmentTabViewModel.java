package bobby.hobby.hel.hel_project;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import bobby.hobby.hel.hel_project.base.API.BaseClient;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;
import bobby.hobby.hel.hel_project.repository.internal.InternalServerClient;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import okhttp3.ResponseBody;

public class FragmentToFragmentTabViewModel extends BaseViewModel {
    public MutableLiveData<String> tabData = new MutableLiveData<>();
    public MutableLiveData<String> dataAccross = new MutableLiveData<>();
    public MutableLiveData<User> user = new MutableLiveData<>();
    public FragmentToFragmentTabViewModel(@NonNull Application application) {
        super(application);
        tabData.setValue("Hello");
    }

    public void login(User newUser) {
        mRepository.login(newUser, new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                user.postValue(response);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {

            }
        });
    }

    public void signup(User newUser) {
        mRepository.signup(newUser, new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                user.postValue(response);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {

            }
        });
    }

    public void getUser() {
        mRepository.getUser(new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                user.postValue(response);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {

            }
        });
    }
}
