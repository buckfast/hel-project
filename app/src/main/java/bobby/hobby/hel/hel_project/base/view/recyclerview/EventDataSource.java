package bobby.hobby.hel.hel_project.base.view.recyclerview;

import android.app.Application;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import bobby.hobby.hel.hel_project.base.API.BaseClient;
import bobby.hobby.hel.hel_project.repository.Repository;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.Event;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.EventList;
import okhttp3.ResponseBody;

public class EventDataSource extends PageKeyedDataSource<Integer, Event> {
    private Repository mRepository;
    private String keyword;

    public EventDataSource(Application ctx, String key) {
        mRepository = Repository.getInstance(ctx);
        keyword = key;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Event> callback) {
        mRepository.getEventList(keyword, 1, new BaseClient.Handler<EventList>() {
            @Override
            public void onSuccess(@NonNull EventList response, int code) {
                callback.onResult(response.getEvents(), null, 2);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Event> callback) {
        //nothing to load before
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Event> callback) {
        mRepository.getEventList(keyword, params.key, new BaseClient.Handler<EventList>() {
            @Override
            public void onSuccess(@NonNull EventList response, int code) {
                callback.onResult(response.getEvents(), params.key+1);
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {

            }
        });
    }
}
