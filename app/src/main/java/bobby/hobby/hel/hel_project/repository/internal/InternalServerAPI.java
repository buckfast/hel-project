package bobby.hobby.hel.hel_project.repository.internal;

import bobby.hobby.hel.hel_project.repository.internal.model.HobbyList;
import bobby.hobby.hel.hel_project.repository.internal.model.Message;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.EventList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Description: This interface define all possible endpoint for internal server
 * Works with:
 * {@link InternalServerClient}
 */

public interface InternalServerAPI {
    @POST("users/")
    Call<User> signup(@Body User user);

    @POST("users/login/")
    Call<User> login(@Body User user);

    @GET("users/me/")
    Call<User> getUserInfo();

    @DELETE("users/me/")
    Call<Message> deleteUser();

    @PUT("users/me")
    Call<Message> updateUser(@Body User user);

    @GET("hobby/")
    Call<HobbyList> getHobbyList();

    @GET("events/{search}/")
    Call<EventList> getEventList(@Path("search") String keyword);
}
