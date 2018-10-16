package bobby.hobby.hel.hel_project.repository.internal;

import bobby.hobby.hel.hel_project.repository.internal.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InternalServerAPI {
    @POST("users/")
    Call<User> signup(@Body User user);

    @POST("users/login/")
    Call<User> login(@Body User user);

    @GET("users/me/")
    Call<User> getUserInfo();
}
