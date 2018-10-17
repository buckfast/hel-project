package bobby.hobby.hel.hel_project.repository.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import bobby.hobby.hel.hel_project.base.API.BaseClient;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import okhttp3.ResponseBody;

public class InternalServerClient extends BaseClient {
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

    public void login(User user, Handler<User> callback) {
        getAPI(InternalServerAPI.class).login(user).enqueue(new BaseResponseHandler<>(new AuthResponseHandler(callback)));
    }

    public void signup(User user, Handler<User> callback) {
        getAPI(InternalServerAPI.class).signup(user).enqueue(new BaseResponseHandler<>(new AuthResponseHandler(callback)));
    }

    public void getUser(Handler<User> callback) {
        getAPI(InternalServerAPI.class).getUserInfo().enqueue(new BaseResponseHandler<>(callback));
    }

    public void logout() {
        setAccessToken("");
    }

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
}
