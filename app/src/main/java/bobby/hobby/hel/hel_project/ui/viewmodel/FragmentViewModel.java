package bobby.hobby.hel.hel_project.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bobby.hobby.hel.hel_project.Util;
import bobby.hobby.hel.hel_project.base.API.BaseClient;
import bobby.hobby.hel.hel_project.base.view.recyclerview.ChatText;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;
import bobby.hobby.hel.hel_project.repository.internal.SocketClient;
import bobby.hobby.hel.hel_project.repository.internal.model.HobbyList;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.Event;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.EventList;
import bobby.hobby.hel.hel_project.ui.model.ChatMessage;
import bobby.hobby.hel.hel_project.ui.model.DrawerListItem;
import bobby.hobby.hel.hel_project.ui.model.EventItem;
import okhttp3.ResponseBody;

public class FragmentViewModel extends BaseViewModel implements SocketClient.EventListener {
    public MutableLiveData<View> lastView = new MutableLiveData<>();
    //MutableLiveData<Integer> lastPosition = new MutableLiveData<>();
    public MutableLiveData<Integer> listPosition = new MutableLiveData<>();
    public MutableLiveData<List<DrawerListItem>> drawerList = new MutableLiveData<>();
    public List<String> channelList;

    public MutableLiveData<List<EventItem>> eventList = new MutableLiveData<>();

    public  MutableLiveData<List<ChatText>> chatMessageList = new MutableLiveData<>();

    public MutableLiveData<EventList> linkedEvents = new MutableLiveData<>();
    public MutableLiveData<List<String>> hobbyList = new MutableLiveData<>();
    public MutableLiveData<User> currentUser = new MutableLiveData<>();
    public CharSequence typedText = "";
    public String lastKeyword = "";


    public FragmentViewModel(@NonNull Application application) {
        super(application);
        List<ChatText> list = new ArrayList<>();
        chatMessageList.setValue(list);
        channelList = new ArrayList<>();
        channelList.add("#general");
    }

    public String getTitle(int pos) {
        return this.drawerList.getValue().get(pos).tv;
    }
    public void addMessage(ChatMessage msg) {
        List<ChatText> msgList = chatMessageList.getValue();
        msgList.add(msg);
        this.chatMessageList.postValue(msgList);
    }

    public String getHobbyByPosition(int pos) {
        return hobbyList.getValue().get(pos);
    }


    @Override
    protected SocketClient.EventListener returnSocketListener() {
        return this;
    }

    @Override
    public Map<String, Emitter.Listener> returnListeners() {
        Map<String, Emitter.Listener> map = new HashMap<>();
        map.put(Socket.EVENT_CONNECT, args -> {
            mRepository.getSocket().emit("add user", "hobo");
        });
        map.put("new message", args -> {
            JSONObject data = (JSONObject) args[0];
            String username;
            String message;
            try {
                username = data.getString("username");
                message = data.getString("message");
                ChatMessage msg = new ChatMessage(message, true, Util.getTime(), username);
                addMessage(msg);
                Log.d("asd", username+message);
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
            }
        });
        return map;
    }

    public void emitMessage(String text) {
        mRepository.getSocket().emit("new message", text);
    }
    public void emitJoinRoom(String room) {
        mRepository.getSocket().emit("join room", room);
    }

    public void login (User user) {
        mRepository.login(user, new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                currentUser.postValue(response);
                Log.d("asd", String.valueOf(response.getHobbies()));
                fetchHobbies();

            }
            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                Log.d("asd", String.valueOf(code));
            }
        });
    }

    public void logout() {
        mRepository.logout();
    }

    public void searchLinkedEvents(String keyword) {
        mRepository.getEventList(keyword, new BaseClient.Handler<EventList>() {
            @Override
            public void onSuccess(@NonNull EventList response, int code) {
                //Log.d("asd", "search linked events: code: "+String.valueOf(code));
                Log.d("asd", "search linked events: events count: "+response.getCount());
                EventList e = response;
                e.getEvents().add(response.getEvents().get(0));
                e.getEvents().add(response.getEvents().get(0));
                e.getEvents().add(response.getEvents().get(0));
                linkedEvents.postValue(e);
            }
            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                //Log.d("asd", "linked events error: resp: "+String.valueOf(body));
                Log.d("asd", "linked events error: code: "+String.valueOf(code));
                linkedEvents.setValue(null);
            }
        });
    }

    public void getUser() {
        mRepository.getUser(new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                Log.d("asd", "jees user");
            }
            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                Log.d("asd", "no");
            }
        });
    }

    public void fetchHobbies() {
        mRepository.getHobbyList(new BaseClient.Handler<HobbyList>() {
            @Override
            public void onSuccess(@NonNull HobbyList response, int code) {
                //hobbyList.postValue(response.getHobbies());
                List<String> l = new ArrayList<>();
                l.add("tanssi");
                l.add("fifa");
                l.add("perulainen joulubasaari");
                l.add("jalkapallo");
                l.add("kirahvi");
                hobbyList.setValue(l);
            }
            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                Log.d("asd", "hobbulist: "+code);
            }
        });
    }

}
