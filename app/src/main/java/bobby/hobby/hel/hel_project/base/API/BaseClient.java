package bobby.hobby.hel.hel_project.base.API;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public abstract class BaseClient {
    protected OkHttpClient client;
    protected Request.Builder reqBuilder;
    protected FormBody.Builder formBodBuilder;
    protected HttpUrl.Builder urlBuilder;
    protected MediaType mediaType;

    protected abstract String returnBaseUrl();

    protected abstract BaseHeaderInterceptor returnInterceptor();

    protected BaseClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(returnInterceptor());

    }
}
