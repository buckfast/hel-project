package bobby.hobby.hel.hel_project.base.view.recyclerview;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.util.Log;


import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.Event;

public class EventDataSourceFactory extends DataSource.Factory<Integer, Event> {
    private MutableLiveData<EventDataSource> mDataSource;
    private Application mContext;
    private String keyword;

    public EventDataSourceFactory(Application ctx) {
        this.mContext = ctx;
    }

    @Override
    public DataSource<Integer, Event> create() {
        EventDataSource dataSource = new EventDataSource(mContext, keyword);
        mDataSource = new MutableLiveData<>();
        mDataSource.postValue(dataSource);
        return dataSource;
    }

    public LiveData<EventDataSource> getLiveDataSource() {
        return mDataSource;
    }

    public void getDataWith(String keyword) {
        this.keyword = keyword;
    }
}
