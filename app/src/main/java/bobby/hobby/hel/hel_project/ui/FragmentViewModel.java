package bobby.hobby.hel.hel_project.ui;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bobby.hobby.hel.hel_project.base.API.BaseClient;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;
import bobby.hobby.hel.hel_project.repository.internal.SocketClient;
import bobby.hobby.hel.hel_project.repository.internal.model.HobbyList;
import bobby.hobby.hel.hel_project.repository.internal.model.Message;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.EventList;
import bobby.hobby.hel.hel_project.ui.model.DrawerListItem;
import bobby.hobby.hel.hel_project.ui.model.EventItem;
import okhttp3.ResponseBody;

public class FragmentViewModel extends BaseViewModel {
    MutableLiveData<View> lastView = new MutableLiveData<>();
    //MutableLiveData<Integer> lastPosition = new MutableLiveData<>();
    MutableLiveData<Integer> listPosition = new MutableLiveData<>();
    MutableLiveData<List<DrawerListItem>> drawerList = new MutableLiveData<>();
    MutableLiveData<List<EventItem>> eventList = new MutableLiveData<>();

    public MutableLiveData<EventList> linkedEvents = new MutableLiveData<>();
    public MutableLiveData<List<String>> hobbyList = new MutableLiveData<>();
    public MutableLiveData<User> currentUser = new MutableLiveData<>();


    public FragmentViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected SocketClient.EventListener returnSocketListener() {
        return null;
    }


    public String getTitle(int pos) {
        return this.drawerList.getValue().get(pos).tv;
    }

    public void login (User user) {

        mRepository.login(user, new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                currentUser.postValue(response);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                Log.d("testi", String.valueOf(code));
            }
        });
    }

    public void signup (User user) {
        //See comment on login
        mRepository.signup(user, new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                currentUser.postValue(response);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {

            }
        });
    }

    public void getUser() {
        //See comment on login
        mRepository.getUser(new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                currentUser.postValue(response);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {

            }
        });
    }

    public void logout() {
        //Will remove the shared preference access token (log user out)
        mRepository.logout();
    }

    public void deleteUser() {
        mRepository.deleteUser(new BaseClient.Handler<Message>() {
            @Override
            public void onSuccess(@NonNull Message response, int code) {
                currentUser.postValue(null);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {

            }
        });
    }

    public void updateUser(User user) {
        mRepository.updateUser(user, new BaseClient.Handler<Message>() {
            @Override
            public void onSuccess(@NonNull Message response, int code) {
                mRepository.getUser(new BaseClient.Handler<User>() {
                    @Override
                    public void onSuccess(@NonNull User response, int code) {
                        currentUser.postValue(response);
                    }

                    @Override
                    public void onError(@Nullable ResponseBody body, int code) {

                    }
                });
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {

            }
        });
    }

    public void getHobbyList() {
        mRepository.getHobbyList(new BaseClient.Handler<HobbyList>() {
            @Override
            public void onSuccess(@NonNull HobbyList response, int code) {
                hobbyList.postValue(response.getHobbies());
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                Log.d("testi", String.valueOf(code));

            }
        });
    }

    public void getEventList(String keyword) {
        mRepository.getEventList(keyword, new BaseClient.Handler<EventList>() {
            @Override
            public void onSuccess(@NonNull EventList response, int code) {
                linkedEvents.postValue(response);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                Log.d("testi", String.valueOf(code));
            }
        });
    }
}
