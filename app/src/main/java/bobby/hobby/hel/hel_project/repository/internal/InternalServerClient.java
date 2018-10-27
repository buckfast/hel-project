package bobby.hobby.hel.hel_project.repository.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import bobby.hobby.hel.hel_project.base.API.BaseClient;
import bobby.hobby.hel.hel_project.repository.internal.model.HobbyList;
import bobby.hobby.hel.hel_project.repository.internal.model.Message;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.EventList;
import okhttp3.ResponseBody;

/**
 * Description: This class is a client to communication with internal server
 * Works with:
 * {@link bobby.hobby.hel.hel_project.repository.Repository}
 */

public class InternalServerClient extends BaseClient<InternalServerAPI> {
    private Context mContext;
    private static final String TOKEN_HEADER_NAME = "hobotti-access-token";
    private static final String TOKEN_SHARED_PREFERENCES_NAME = "InternalServerToken";

    public InternalServerClient(Context context) {
        mContext = context;
    }

    @Override
    protected String returnBaseUrl() {
        return "https://hobotti-backend-testing.herokuapp.com/api/";
    }

    @Override
    protected Context returnContext() {
        return mContext;
    }

    @Override
    protected String returnTokenHeaderName() {
        return TOKEN_HEADER_NAME;
    }

    @Override
    protected String returnTokenSharedPreferencesName() {
        return TOKEN_SHARED_PREFERENCES_NAME;
    }

    //User manipulation
    public void login(User user, Handler<User> callback) {
        getAPI(null, InternalServerAPI.class).login(user).enqueue(new BaseResponseHandler<>(new AuthResponseHandler(callback)));
    }

    public void signup(User user, Handler<User> callback) {
        getAPI(null, InternalServerAPI.class).signup(user).enqueue(new BaseResponseHandler<>(new AuthResponseHandler(callback)));
    }

    public void getUser(Handler<User> callback) {
        getAPI(InternalServerAPI.class).getUserInfo().enqueue(new BaseResponseHandler<>(callback));
    }

    public void deleteUser(Handler<Message> callback) {
        getAPI(InternalServerAPI.class).deleteUser().enqueue(new BaseResponseHandler<>(new AuthOutResponseHandler(callback)));
    }

    public void updateUser(User user, Handler<Message> callback) {
        getAPI(InternalServerAPI.class).updateUser(user).enqueue(new BaseResponseHandler<>(callback));
    }

    public void logout() {
        setAccessToken("");
    }
    //----------------------------------------------------

    //Hobby and event
    public void getHobbyList(Handler<HobbyList> callback) {
        getAPI(InternalServerAPI.class).getHobbyList().enqueue(new BaseResponseHandler<>(callback));
    }

    public void getEventList(String searchKeyWord, Handler<EventList> callback) {
        getAPI(InternalServerAPI.class).getEventList(searchKeyWord).enqueue(new BaseResponseHandler<>(callback));
    }
    //----------------------------------------------------

    private class AuthResponseHandler extends BaseHandler<User> {
        private AuthResponseHandler(Handler<User> handler) {
            super(handler);
        }

        @Override
        public void onSuccess(@NonNull User response, int code) {
            setAccessToken(response.getToken());
            handler.onSuccess(response, code);
        }

        @Override
        public void onError(@Nullable ResponseBody body, int code) {
            handler.onError(body, code);
        }
    }

    private class AuthOutResponseHandler extends BaseHandler<Message> {

        private AuthOutResponseHandler(Handler<Message> handler) {
            super(handler);
        }

        @Override
        public void onSuccess(@NonNull Message response, int code) {
            setAccessToken("");
            handler.onSuccess(response, code);
        }

        @Override
        public void onError(@Nullable ResponseBody body, int code) {
            handler.onError(body, code);
        }
    }
}
