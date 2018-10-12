package bobby.hobby.hel.hel_project.base.API;

import java.util.Map;
import java.util.Objects;

import okhttp3.FormBody;
import okhttp3.HttpUrl;

public final class APIUtil {
    private APIUtil (){};

    public static String addQueryParams(String baseUrl, Map<String, String> params) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(baseUrl)).newBuilder();
        if (params != null) {
            for(Map.Entry<String, String> param : params.entrySet()) {
                urlBuilder.addQueryParameter(param.getKey(),param.getValue());
            }
        }
        return urlBuilder.build().toString();
    }

    public static FormBody addFormBody(Map<String, String> params) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        if (params != null) {
            for(Map.Entry<String, String> param : params.entrySet()) {
                formBodyBuilder.add(param.getKey(),param.getValue());
            }
        }

        return formBodyBuilder.build();
    }


}
