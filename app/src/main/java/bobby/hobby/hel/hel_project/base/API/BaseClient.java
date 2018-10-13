package bobby.hobby.hel.hel_project.base.API;

import okhttp3.OkHttpClient;

public abstract class BaseClient {
    private static OkHttpClient client;

    protected abstract BaseHeaderInterceptor returnInterceptor();

    public OkHttpClient getClient() {
        if (client == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (returnInterceptor() != null) {
                builder.addInterceptor(returnInterceptor());
            }
            client = builder.build();
        }
        return client;
    }

    private BaseClient() {}
}
