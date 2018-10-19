package bobby.hobby.hel.hel_project;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

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
import okhttp3.ResponseBody;

public class ActivityFragmentViewModel extends BaseViewModel implements SocketClient.EventListener{
    public MutableLiveData<User> currentUser = new MutableLiveData<>();
    public MutableLiveData<List<String>> hobbyList = new MutableLiveData<>();
    public MutableLiveData<EventList> eventList = new MutableLiveData<>();
    public ActivityFragmentViewModel(@NonNull Application application) {
        super(application);
        //This is how to emit event, event cannot be emit in activity or fragment, event emit when some data change in viewmodel
        //mRepository.getSocket().emit()
    }

    public void login (User user) {
        //Success and error handle in viewmodel as well, the data will change and react to the result of the server call
        //Activity and fragment have no data logic, it only observe data and change UI accordingly (data-driven guideline)
        //Activity and fragment only react to user interaction and call proper viewmodel method, in this example, login
        mRepository.login(user, new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                currentUser.postValue(response);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {

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

    public void emit() {
        mRepository.getSocket().emit("new message", "Helo");
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

            }
        });
    }

    public void getEventList(String keyword) {
        mRepository.getEventList(keyword, new BaseClient.Handler<EventList>() {
            @Override
            public void onSuccess(@NonNull EventList response, int code) {
                eventList.postValue(response);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {

            }
        });
    }

    @Override
    protected SocketClient.EventListener returnSocketListener() {
        return this;
    }

    @Override
    public Map<String, Emitter.Listener> returnListeners() {
        //Remember to add event listener here, this is where react to server event
        Map<String, Emitter.Listener> map = new HashMap<>();
        map.put(Socket.EVENT_CONNECT, args -> {
            mRepository.getSocket().emit("add user", "uiop");
        });
        map.put("new message", args -> {
            JSONObject data = (JSONObject) args[0];
            String username;
            String message;
            try {
                username = data.getString("username");
                message = data.getString("message");
                Log.d("Fine", username+message);
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
            }
        });
        return map;
    }
}
