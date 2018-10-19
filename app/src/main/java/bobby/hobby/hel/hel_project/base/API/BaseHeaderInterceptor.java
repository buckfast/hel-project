package bobby.hobby.hel.hel_project.base.API;



import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseHeaderInterceptor implements Interceptor {
    protected abstract Map<String, String> returnHeaderParams();
    @Override
    public Response intercept(Chain chain) {
        Request originalRequest = chain.request();
        Map<String, String> params = returnHeaderParams();
        Request.Builder builder = originalRequest.newBuilder();

        if (params != null) {
            for(Map.Entry<String, String> param: params.entrySet()) {
                builder.addHeader(param.getKey(), param.getValue());
            }
        }

        Request newRequest = builder.build();

        try {
            return chain.proceed(newRequest);
        } catch (IOException e) {
            Log.e("Header", "Error building header with interceptor");
            return null;
        }
    }
}
