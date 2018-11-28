package bobby.hobby.hel.hel_project.base.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Objects;

import bobby.hobby.hel.hel_project.base.view.recyclerview.EventDataSourceFactory;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.Event;

public abstract class BaseListViewModel extends AndroidViewModel {
    protected LiveData<PagedList<Event>> listData;
    protected EventDataSourceFactory factory;

    public BaseListViewModel(@NonNull Application application) {
        super(application);
        factory = new EventDataSourceFactory(application);
        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(40)
                .setPageSize(30).build();

        listData = new LivePagedListBuilder<>(factory,config).build();
    }

    public LiveData<PagedList<Event>> getLiveList() {
        if (listData != null) {
            return listData;
        }
        return null;
    }

    public void populateData(String keyword) {
        factory.getDataWith(keyword);
        if (listData.getValue() != null) {
            Objects.requireNonNull(listData.getValue()).getDataSource().invalidate();
        }
    }

    public void refreshData() {
        if (factory != null) {
            Objects.requireNonNull(factory.getLiveDataSource().getValue()).invalidate();
        }
    }
}
