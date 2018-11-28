package bobby.hobby.hel.hel_project;

import android.app.Application;
import android.support.annotation.NonNull;

import bobby.hobby.hel.hel_project.base.viewmodel.BaseListViewModel;

public class ListViewModel extends BaseListViewModel {
    public ListViewModel(@NonNull Application application) {
        super(application);
        populateData("tanssi");
    }
}
