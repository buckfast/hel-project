package bobby.hobby.hel.hel_project.ui.viewmodel;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bobby.hobby.hel.hel_project.Util;
import bobby.hobby.hel.hel_project.base.API.BaseClient;
import bobby.hobby.hel.hel_project.base.view.recyclerview.chat.ChatText;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;
import bobby.hobby.hel.hel_project.repository.internal.SocketClient;
import bobby.hobby.hel.hel_project.repository.internal.model.ChatLog;
import bobby.hobby.hel.hel_project.repository.internal.model.ChatLogList;
import bobby.hobby.hel.hel_project.repository.internal.model.Hobby;
import bobby.hobby.hel.hel_project.repository.internal.model.HobbyList;
import bobby.hobby.hel.hel_project.repository.internal.model.Message;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.EventList;
import bobby.hobby.hel.hel_project.ui.model.ChatMessage;
import bobby.hobby.hel.hel_project.ui.model.DrawerListItem;
import bobby.hobby.hel.hel_project.ui.model.EventItem;
import okhttp3.ResponseBody;

/**
 * Description: The main communication viewmodel for fragments of the app
 * Features:
 * - Holds main application wide data that is used after authentication
 * - implements connection to socket.io chat server
 * - implements connection to REST
 */
public class FragmentViewModel extends BaseViewModel implements SocketClient.EventListener {
    public MutableLiveData<View> lastView = new MutableLiveData<>();
    //MutableLiveData<Integer> lastPosition = new MutableLiveData<>();
    public MutableLiveData<Integer> listPosition = new MutableLiveData<>();
    public MutableLiveData<List<DrawerListItem>> drawerList = new MutableLiveData<>();
    public List<String> channelList;

    public MutableLiveData<List<EventItem>> eventList = new MutableLiveData<>();
    public MutableLiveData<Boolean> loggedIn = new MutableLiveData<>();
    public MutableLiveData<Boolean> signedup = new MutableLiveData<>();

    public MutableLiveData<List<ChatText>> chatMessageList = new MutableLiveData<>();
    public MutableLiveData<Boolean> chatMessageListUpdated = new MutableLiveData<>();

    public MutableLiveData<EventList> linkedEvents = new MutableLiveData<>();
    public MutableLiveData<List<String>> hobbyList = new MutableLiveData<>();
    public MutableLiveData<List<Hobby>> swipeHobbyList = new MutableLiveData<>();

    public MutableLiveData<Integer> authError = new MutableLiveData<>();

    public MutableLiveData<User> currentUser = new MutableLiveData<>();
    public MutableLiveData<User> currentUserRegister = new MutableLiveData<>();
    public String token = null;

    public CharSequence typedText = "";
    public String lastKeyword = "";

    public MutableLiveData<Boolean> clearTitle = new MutableLiveData<>();

    public MutableLiveData<String> title = new MutableLiveData<>();

    public List<String> signupLikedHobbies = new ArrayList<>();

    public MutableLiveData<EventList> foundLinkedEvents = new MutableLiveData<>();

    public FragmentViewModel(@NonNull Application application) {
        super(application);
        List<ChatText> list = new ArrayList<>();
        chatMessageList.setValue(list);
        channelList = new ArrayList<>();
        channelList.add("#general");
        loggedIn.setValue(false);
        signedup.setValue(false);

        clearTitle.setValue(false);

        authError.setValue(-1);
    }

    /**
     * get hobby name as string from drawer list
     */
    public String getTitle(int pos) {
        if (this.drawerList.getValue() != null && this.drawerList.getValue().size() > 0) {
            return this.drawerList.getValue().get(pos).tv;
        }
        return "";
    }

    /**
     * for disabling or enabling progressbar
     */
    public void longRunningTask(Boolean b) {
        setRunningLongTaskFlag(b);
    }

    /**
     * adds a chat message to local message list
     */
    public void addMessage(ChatMessage msg) {
        List<ChatText> msgList = chatMessageList.getValue();
        msgList.add(msg);
        this.chatMessageList.postValue(msgList);
    }

    public String getHobbyByPosition(int pos) {
        if (hobbyList.getValue().size() > 0) {
            if (hobbyList.getValue().get(pos) != null) {
                return hobbyList.getValue().get(pos);
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * get hobbies for swipe cards
     */
    public List<Hobby> getSwipeHobbyList() {
        return this.swipeHobbyList.getValue();
    }

    @Override
    protected SocketClient.EventListener returnSocketListener() {
        return this;
    }

    /**
     * returns all needed socket event listeners to base
     */
    @Override
    public Map<String, Emitter.Listener> returnListeners() {
        Map<String, Emitter.Listener> map = new HashMap<>();
        map.put(Socket.EVENT_CONNECT, args -> {
            Log.d("asd", "add user " + currentUser.getValue().getName());

            mRepository.getSocket().emit("add user", currentUser.getValue().getName()); // TODO: fix name bug

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
                // Log.d("asd", username+message);
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
        if (mRepository.getSocket() != null) {
            mRepository.getSocket().emit("join room", room);
        } else {
            //Log.d("asd", "error: socket null");
        }
    }

    public void emitAddUser(String name) {
        mRepository.getSocket().emit("add user", name);
    }

    public void emitDisconnect() {
        mRepository.getSocket().emit("disconnect");
    }

    public void signup(User user) {
        Log.d("asd", String.valueOf(user));
        mRepository.signup(user, new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                //fillHobbyList(signupLikedHobbies);
                //currentUser.postValue(response);
                //getUser();
                currentUserRegister.setValue(user);
                signedup.setValue(true);
                User user = response;
                response.setHobbies(signupLikedHobbies);
                token = response.getToken();
                //Log.d("asd", "signed up ");

                //getUser(); 
                // TODO: 14.11.2018 hiigaegijafd 
                //emitAddUser(response.getName());
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                //Log.d("asd", "signup error: "+code);
                postRunningLongTaskFlag(false);
            }
        });
    }

    public void login(User user) {
        mRepository.login(user, new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                //currentUser.postValue(response);
                //fetchHobbies();
                //Log.d("asd", "logged in user: "+response);
                token = response.getToken();
                getUser();
                //Log.d("asd", "loggedin");

                //fillHobbyList(response.getHobbies());
                //emitAddUser(response.getName());
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                // Log.d("asd", String.valueOf(code));
                postRunningLongTaskFlag(false);
                authError.setValue(1);
            }
        });
    }

    public void logout() {
        signedup.setValue(false);
        loggedIn.setValue(false);
        //mRepository.logout();
        emitDisconnect();
        linkedEvents.setValue(null);
        typedText = "";
        lastKeyword = "";
        //currentUser.setValue(null);

        listPosition.setValue(0);
    }

    /**
     * search and populate events
     */
    public void searchLinkedEvents(String keyword) {
        //Log.d("asd", "serdhclined keyworkd "+keyword);
        mRepository.getEventList(keyword, new BaseClient.Handler<EventList>() {
            @Override
            public void onSuccess(@NonNull EventList response, int code) {
                //Log.d("asd", "search linked events: code: "+String.valueOf(code));
                //Log.d("asd", "search linked events: events count: "+response.getCount());
                //Log.d("asd", "search linked events: eventscount"+response.getEvents().size());
                linkedEvents.postValue(response);
                postRunningLongTaskFlag(false);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                //Log.d("asd", "linked events error: resp: "+String.valueOf(body));
                //Log.d("asd", "linked events error: code: "+String.valueOf(code));
                linkedEvents.postValue(null);
                postRunningLongTaskFlag(false);
            }
        });
    }

    /**
     * for searching hobbies through search page (not in use)
     */
    public void checkLinkedEvents(String keyword) {
        mRepository.getEventList(keyword, new BaseClient.Handler<EventList>() {
            @Override
            public void onSuccess(@NonNull EventList response, int code) {
                foundLinkedEvents.postValue(response);
                postRunningLongTaskFlag(false);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                foundLinkedEvents.postValue(null);
                postRunningLongTaskFlag(false);
            }
        });
    }

    /**
     * fetch current user data
     */
    public void getUser() {
        mRepository.getUser(new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                //Log.d("asd", "logged in user: "+response);
                currentUser.postValue(response);
                fillHobbyList(response.getHobbies());
                loggedIn.setValue(true);
                signedup.setValue(true);

            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                Log.d("asd", "no");
            }
        });
    }


    /**
     * fetch hobbies (name and image) used in swipe cards
     */
    public void fetchHobbies(int amount) {
        mRepository.getHobbyList(new BaseClient.Handler<HobbyList>() {
            @Override
            public void onSuccess(@NonNull HobbyList response, int code) {
                List<Hobby> hobbies = new ArrayList<>();
                for (int i = 0; i < amount; i++) {
                    hobbies.add(response.getHobbies().get(i));
                }
                swipeHobbyList.postValue(hobbies);
                postRunningLongTaskFlag(false);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                //Log.d("asd", "hobbulist: "+code);
                postRunningLongTaskFlag(false);

            }
        });
    }

    public void fillHobbyList(List<String> list) {
        hobbyList.setValue(list);
        //Log.d("asd", "fillhobbylist: "+list);
    }

    public void getChatLog(String roomName) {
        mRepository.getChatLog(roomName, new BaseClient.Handler<ChatLogList>() {
            @Override
            public void onSuccess(@NonNull ChatLogList response, int code) {
                //Log.d("asd", "sukkes chat log "+roomName);
                List<ChatLog> log = response.getChatLogs();
                List<ChatText> chatMessages = new ArrayList<>();
                ChatMessage chatMessage;
                for (int i = log.size() - 1; i >= 0; i--) {
                    ChatLog chatLog = log.get(i);
                    chatMessage = new ChatMessage(
                            chatLog.getMessage(),
                            !currentUser.getValue().getName().equals(chatLog.getUser()),
                            Util.formatTime(chatLog.getDate(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "dd.MM. HH:mm"),
                            chatLog.getUser());
                    chatMessages.add(chatMessage);
                }
                chatMessageList.setValue(chatMessages);
                chatMessageListUpdated.setValue(true);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                chatMessageList.setValue(new ArrayList<>());
            }
        });
    }

    /**
     * update user locally and server side when new hobby is subscribed
     */
    public void addUserHobby(String hobby) {
        Log.d("asd", "hobbu to add: " + hobby);
        List newHobbies = currentUser.getValue().getHobbies();
        newHobbies.add(hobby);
        currentUser.getValue().setHobbies(newHobbies);
        mRepository.updateUser(currentUser.getValue(), new BaseClient.Handler<Message>() {
            @Override
            public void onSuccess(@NonNull Message response, int code) {

                hobbyList.setValue(newHobbies);
                Log.d("asd", "newhobbylist: " + hobbyList.getValue());

            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                Log.d("asd", "error add hobbies code: " + code);
            }
        });
    }

}
