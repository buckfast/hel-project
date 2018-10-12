package bobby.hobby.hel.hel_project.repository;

import android.app.Application;

public class Repository {
    private static Repository instance;
    private Application mApplication;

    private Repository(Application application){
        mApplication = application;
    };

    public static synchronized Repository getInstance(Application application) {
        if (instance == null) {
            instance = new Repository(application);
        }
        return instance;
    }


}
