package bobby.hobby.hel.hel_project.base.API;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseAPI {
    protected Request.Builder reqBuilder;
    protected FormBody.Builder formBodBuilder;
    protected HttpUrl.Builder urlBuilder;
    protected MediaType mediaType;

    protected abstract String returnBaseUrl();

    protected abstract BaseClient client();

    protected void get(String path, Map<String, String> queryParams, Callback callback) {
        Request request = new Request.Builder()
                .url(buildPath(path, queryParams))
                .build();
        if (callback == null) {
            client().getClient().newCall(request).enqueue(new NoResponse());
        } else {
            client().getClient().newCall(request).enqueue(callback);
        }
    }

    protected void get(String path, Callback callback) {
        get(path, null, callback);
    }

    protected void get(String path) {
        get(path, null, null);
    }

    private HttpUrl buildPath(String path, Map<String, String> queryParams) {
        urlBuilder = new HttpUrl.Builder().host(returnBaseUrl());
        if (path != null) {
            if (path.startsWith("/")) {
                urlBuilder.addPathSegments(path.substring(1,path.length()-1));
            } else {
                urlBuilder.addPathSegments(path);
            }
        }
        if (queryParams != null) {
            for(Map.Entry<String, String> param: queryParams.entrySet()) {
                urlBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }
        return urlBuilder.build();
    }

    private HttpUrl buildPath(String path) {
        return buildPath(path, null);
    }

    public class NoResponse implements Callback {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("Reponse error", "Failure on no response of cal: "+ call.toString());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            //Nothing happen
        }
    }
}
