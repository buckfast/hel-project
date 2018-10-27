package bobby.hobby.hel.hel_project.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bobby.hobby.hel.hel_project.base.view.recyclerview.ChatText;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;
import bobby.hobby.hel.hel_project.repository.internal.SocketClient;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.EventList;
import bobby.hobby.hel.hel_project.ui.model.ChatMessage;
import bobby.hobby.hel.hel_project.ui.model.DrawerListItem;
import bobby.hobby.hel.hel_project.ui.model.EventItem;

public class FragmentViewModel extends BaseViewModel implements SocketClient.EventListener {
    public MutableLiveData<View> lastView = new MutableLiveData<>();
    //MutableLiveData<Integer> lastPosition = new MutableLiveData<>();
    public MutableLiveData<Integer> listPosition = new MutableLiveData<>();
    public MutableLiveData<List<DrawerListItem>> drawerList = new MutableLiveData<>();
    public MutableLiveData<List<EventItem>> eventList = new MutableLiveData<>();

    public  MutableLiveData<List<ChatText>> chatMessageList = new MutableLiveData<>();

    public MutableLiveData<EventList> linkedEvents = new MutableLiveData<>();
    public MutableLiveData<List<String>> hobbyList = new MutableLiveData<>();
    public MutableLiveData<User> currentUser = new MutableLiveData<>();


    public FragmentViewModel(@NonNull Application application) {
        super(application);
        List<ChatText> list = new ArrayList<>();
        chatMessageList.setValue(list);
    }

    public String getTitle(int pos) {
        return this.drawerList.getValue().get(pos).tv;
    }
    public void addMessage(ChatMessage msg) {
        List<ChatText> msgList = chatMessageList.getValue();
        msgList.add(msg);
        this.chatMessageList.setValue(msgList);
    }

    @Override
    protected SocketClient.EventListener returnSocketListener() {
        return this;
    }

    @Override
    public Map<String, Emitter.Listener> returnListeners() {
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

    public void emitTest() {
        mRepository.getSocket().emit("new message", "aaaaasd");
    }
}
