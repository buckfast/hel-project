package bobby.hobby.hel.hel_project.repository;

import android.app.Application;

import bobby.hobby.hel.hel_project.base.API.BaseClient.Handler;
import bobby.hobby.hel.hel_project.repository.internal.InternalServerClient;
import bobby.hobby.hel.hel_project.repository.internal.model.User;

public class Repository {
    private static Repository instance;
    private InternalServerClient mInternalServerClient;

    private Repository(Application application){
        mInternalServerClient = new InternalServerClient(application);
    }

    public static synchronized Repository getInstance(Application application) {
        if (instance == null) {
            instance = new Repository(application);
        }
        return instance;
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
}
