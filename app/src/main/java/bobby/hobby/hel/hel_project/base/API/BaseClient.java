package bobby.hobby.hel.hel_project.base.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import bobby.hobby.hel.hel_project.repository.internal.InternalServerClient;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseClient {
    private static final String ACCESS_TOKEN_FILE_NAME = "AccessToken";
    protected abstract String returnBaseUrl();
    protected abstract Context returnContext();
    protected abstract String returnTokenHeaderName();
    protected abstract String returnTokenSharedPreferencesName();

    private SharedPreferences getSharedPreference() {
        return returnContext().getSharedPreferences(ACCESS_TOKEN_FILE_NAME, Context.MODE_PRIVATE);
    }

    protected String getAccessToken() {
        return getSharedPreference().getString(returnTokenSharedPreferencesName(), "");
    }

    protected void setAccessToken(String token) {
        getSharedPreference().edit().putString(returnTokenSharedPreferencesName(), token).apply();
    }

    protected <T>T getAPI(BaseHeaderInterceptor interceptor, final Class<T> api) {
        OkHttpClient client = null;
        if (interceptor != null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(returnBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson));

        if (client != null) {
            builder.client(client);
        }
        return builder.build().create(api);
    }

    protected <T>T getAPI(final Class<T> api) {
        if (getAccessToken().isEmpty()) {
            return getAPI(null, api);
        } else {
            return getAPI(new BaseHeaderInterceptor() {
                @Override
                protected Map<String, String> returnHeaderParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put(returnTokenHeaderName(), getAccessToken());
                    return params;
                }
            }, api);
        }
    }

    public interface Handler<T> {
        void onSuccess(@NonNull final T response, int code);
        void onError(@Nullable ResponseBody body, int code);
    }

    public static abstract class BaseHandler<T> implements Handler<T> {
        protected Handler<T> handler;
        protected BaseHandler(Handler<T> handler) {
            this.handler = handler;
        }
    }

    public class BaseResponseHandler<T> implements Callback<T> {
        private Handler<T> handler;
        public BaseResponseHandler(Handler<T> handler) {
            this.handler = handler;
        }
        @Override
        public final void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
            if (response.isSuccessful()) {
                handler.onSuccess(Objects.requireNonNull(response.body()), response.code());
            } else {
                handler.onError(response.errorBody(), response.code());
            }
        }

        @Override
        public final void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
            Log.e("API error", call.toString());
            handler.onError(null, -1);
        }
    }
}
