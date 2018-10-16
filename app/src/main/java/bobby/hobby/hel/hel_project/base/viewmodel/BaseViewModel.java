package bobby.hobby.hel.hel_project.base.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import bobby.hobby.hel.hel_project.repository.Repository;

public abstract class BaseViewModel extends AndroidViewModel {
    protected Repository mRepository;
    private boolean mCurrentState = false;
    private MutableLiveData<Boolean> mReaction;
    private int mCurrentPosition;
    public BaseViewModel(@NonNull Application application) {
        super(application);
        mRepository = Repository.getInstance(application);
    }

    public final LiveData<Boolean> getClickReaction() {
        if (mReaction == null) {
            mReaction = new MutableLiveData<>();
            mReaction.setValue(mCurrentState);
            mCurrentPosition = 0;
        }
        return mReaction;
    }

    public final void setClickReaction() {
        mReaction.setValue(!mCurrentState);
    }

    public final int getCurrentPosition() {
        return mCurrentPosition;
    }

    public final void setCurrentPosition(int mCurrentPosition) {
        this.mCurrentPosition = mCurrentPosition;
    }
}
