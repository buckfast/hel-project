package bobby.hobby.hel.hel_project.repository;

import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;

import com.github.nkzawa.socketio.client.Socket;

import bobby.hobby.hel.hel_project.base.API.BaseClient.Handler;
import bobby.hobby.hel.hel_project.repository.internal.InternalServerClient;
import bobby.hobby.hel.hel_project.repository.internal.SocketClient;
import bobby.hobby.hel.hel_project.repository.internal.model.User;

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

    public Socket getSocket () {
        if (mSocketClient != null) {
            return mSocketClient.getSocket();
        }
        return null;
    }

    public void login(User user, Handler<User> handler) {
        mInternalServerClient.login(user, handler);
    }

    public void signup(User user, Handler<User> handler) {
        mInternalServerClient.signup(user, handler);
    }

    public void getUser(Handler<User> callback) {
        mInternalServerClient.getUser(callback);
    }

    public void logout() {mInternalServerClient.logout();}
}
