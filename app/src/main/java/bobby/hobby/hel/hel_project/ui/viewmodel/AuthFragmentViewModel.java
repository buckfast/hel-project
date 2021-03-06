package bobby.hobby.hel.hel_project.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.base.API.BaseClient;
import bobby.hobby.hel.hel_project.base.view.recyclerview.chat.ChatText;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;
import bobby.hobby.hel.hel_project.repository.internal.SocketClient;
import bobby.hobby.hel.hel_project.repository.internal.model.ChatLog;
import bobby.hobby.hel.hel_project.repository.internal.model.ChatLogList;
import bobby.hobby.hel.hel_project.repository.internal.model.Hobby;
import bobby.hobby.hel.hel_project.repository.internal.model.HobbyList;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.EventList;
import bobby.hobby.hel.hel_project.ui.model.ChatMessage;
import bobby.hobby.hel.hel_project.ui.model.DrawerListItem;
import bobby.hobby.hel.hel_project.ui.model.EventItem;
import okhttp3.ResponseBody;

/**
 * copypasted for future use
 */
public class AuthFragmentViewModel extends BaseViewModel {
    public MutableLiveData<View> lastView = new MutableLiveData<>();
    //MutableLiveData<Integer> lastPosition = new MutableLiveData<>();
    public MutableLiveData<Integer> listPosition = new MutableLiveData<>();
    public MutableLiveData<List<DrawerListItem>> drawerList = new MutableLiveData<>();
    public List<String> channelList;

    public MutableLiveData<List<EventItem>> eventList = new MutableLiveData<>();
    public MutableLiveData<Boolean> loggedIn = new MutableLiveData<>();
    public MutableLiveData<Boolean> signedup = new MutableLiveData<>();

    public MutableLiveData<List<ChatText>> chatMessageList = new MutableLiveData<>();

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

    public AuthFragmentViewModel(@NonNull Application application) {
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

    @Override
    protected SocketClient.EventListener returnSocketListener() {
        return null;
    }

    public String getTitle(int pos) {
        if (this.drawerList.getValue() != null && this.drawerList.getValue().size() > 0) {
            return this.drawerList.getValue().get(pos).tv;
        }
        return "";
    }

    public void longRunningTask(Boolean b) {
        setRunningLongTaskFlag(b);
    }


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

    public List<Hobby> getSwipeHobbyList() {
        return this.swipeHobbyList.getValue();
    }


    public void emitMessage(String text) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("data", text);
            obj.put("username", currentUser.getValue().getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                signedup.setValue(true);
                User user = response;
                response.setHobbies(signupLikedHobbies);
                token = response.getToken();
                currentUserRegister.postValue(user);
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

    public void fetchHobbies(int amount) {
        mRepository.getHobbyList(new BaseClient.Handler<HobbyList>() {
            @Override
            public void onSuccess(@NonNull HobbyList response, int code) {
                List<Hobby> hobbies = new ArrayList<>();
                for (int i = 0; i < amount; i++) {
                    hobbies.add(response.getHobbies().get(i));
                }
                swipeHobbyList.postValue(hobbies);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                //Log.d("asd", "hobbulist: "+code);
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
                for (int i = 0; i < log.size(); i++) {
                    ChatLog chatLog = log.get(i);
                    chatMessage = new ChatMessage(
                            chatLog.getMessage(),
                            !currentUser.getValue().getName().equals(chatLog.getUser()),
                            chatLog.getDate(),
                            chatLog.getUser());
                    chatMessages.add(0, chatMessage);
                }
                chatMessageList.postValue(chatMessages);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {
                chatMessageList.setValue(new ArrayList<>());
            }
        });
    }

}
