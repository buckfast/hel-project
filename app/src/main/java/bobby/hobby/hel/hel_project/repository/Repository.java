package bobby.hobby.hel.hel_project.repository;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;

import com.github.nkzawa.socketio.client.Socket;

import bobby.hobby.hel.hel_project.base.API.BaseClient.Handler;
import bobby.hobby.hel.hel_project.repository.internal.InternalServerClient;
import bobby.hobby.hel.hel_project.repository.internal.SocketClient;
import bobby.hobby.hel.hel_project.repository.internal.model.ChatLogList;
import bobby.hobby.hel.hel_project.repository.internal.model.HobbyList;
import bobby.hobby.hel.hel_project.repository.internal.model.Message;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.EventList;

/**
 * Description: Single source of truth, know how to get data and where
 * Works with:
 * {@link bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel}
 * {@link SocketClient}
 * {@link InternalServerClient}
 */

public class Repository {
    private static Repository instance;
    private InternalServerClient mInternalServerClient;
    private SocketClient mSocketClient;
    private Application mApplication;

    private Repository(Application application){
        this.mApplication = application;
        initializeInternalServiceClient();
    }

    public static synchronized Repository getInstance(Application application) {
        if (instance == null) {
            instance = new Repository(application);
        }
        return instance;
    }

    public void initializeSocketClient(SocketClient.EventListener listener, LifecycleOwner owner) {
        if (listener != null && owner != null) {
            mSocketClient = new SocketClient(mApplication, listener);
            owner.getLifecycle().addObserver(mSocketClient);
        }
    }

    private void initializeInternalServiceClient() {
        mInternalServerClient = new InternalServerClient(mApplication);
    }

    private void resetSocket() {
        if (mSocketClient != null) {
            mSocketClient.disconnect();
            mSocketClient.connect();
        }
    }

    public Socket getSocket () {
        if (mSocketClient != null) {
            return mSocketClient.getSocket();
        }
        return null;
    }

    public void login(User user, Handler<User> handler) {
        if (mSocketClient != null) {
            resetSocket();
        }
        mInternalServerClient.login(user, handler);
    }

    public void signup(User user, Handler<User> handler) {
        if (mSocketClient != null) {
            resetSocket();
        }
        mInternalServerClient.signup(user, handler);
    }

    public void getUser(Handler<User> callback) {
        mInternalServerClient.getUser(callback);
    }

    public void logout() {
        if (mSocketClient != null) {
            mSocketClient.disconnect();
        }
        mInternalServerClient.logout();
    }

    public void deleteUser(Handler<Message> callback) {
        mInternalServerClient.deleteUser(callback);
    }

    public void updateUser(User user, Handler<Message> callback) {
        mInternalServerClient.updateUser(user, callback);
    }

    public void getHobbyList(Handler<HobbyList> handler) {mInternalServerClient.getHobbyList(handler);}

    public void getEventList(String keyword, Handler<EventList> handler) {
        mInternalServerClient.getEventList(keyword, handler);
    }

    public void getEventList(String keyword, Integer page, Handler<EventList> handler) {
        mInternalServerClient.getEventList(keyword, page, handler);
    }

    public void getChatLog(String chatroom, Handler<ChatLogList> handler) {
        mInternalServerClient.getChatLog(chatroom, handler);
    }
}
