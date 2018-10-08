package bobby.hobby.hel.hel_project.base.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public abstract class BaseViewModel extends AndroidViewModel {
    private boolean mCurrentState = false;
    private MutableLiveData<Boolean> mReaction;
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public final LiveData<Boolean> getClickReaction() {
        if (mReaction == null) {
            mReaction = new MutableLiveData<>();
            mReaction.setValue(mCurrentState);
        }
        return mReaction;
    }

    public final void setClickReaction() {
        mReaction.setValue(!mCurrentState);
    }
}
