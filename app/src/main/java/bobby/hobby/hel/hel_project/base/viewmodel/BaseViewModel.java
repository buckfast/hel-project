package bobby.hobby.hel.hel_project.base.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import bobby.hobby.hel.hel_project.repository.Repository;
import bobby.hobby.hel.hel_project.repository.internal.SocketClient;

/**
 * Description: This class defy default behaviour for a viewmodel
 * Features:
 * - Fetch Repository instance
 * - Set open/close drawer
 * - Remember tab position (to survive configuration change
 * - Set up Socket
 * Works with:
 * {@link Repository}
 * {@link bobby.hobby.hel.hel_project.base.view.activity.BaseDrawerActivity}
 * {@link bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseNavViewListChildFragment}
 * {@link bobby.hobby.hel.hel_project.base.view.fragment.master.BaseNavViewListHostFragment}
 * {@link bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseTabChildFragment}
 * {@link bobby.hobby.hel.hel_project.base.view.fragment.master.BaseTabHostFragment}
 * {@link SocketClient}
 */

public abstract class BaseViewModel extends AndroidViewModel {
    protected Repository mRepository;
    private MutableLiveData<Boolean> isRunningLongTask;
    private boolean mCurrentState = false;
    private MutableLiveData<Boolean> mReaction;
    private int mCurrentPosition;
    public BaseViewModel(@NonNull Application application) {
        super(application);
        mRepository = Repository.getInstance(application);
    }

    public final LiveData<Boolean> getRunningTaskFlag() {
        if (isRunningLongTask == null) {
            isRunningLongTask = new MutableLiveData<>();
            isRunningLongTask.setValue(false);
        }
        return isRunningLongTask;
    }

    protected final void setRunningLongTaskFlag(boolean value) {
        getRunningTaskFlag();
        isRunningLongTask.setValue(value);
    }

    protected final void postRunningLongTaskFlag(boolean value) {
        getRunningTaskFlag();
        isRunningLongTask.postValue(value);
    }

    public final LiveData<Boolean> getClickReaction() {
        if (mReaction == null) {
            mReaction = new MutableLiveData<>();
            mReaction.setValue(mCurrentState);
            mCurrentPosition = 0;
        }
        return mReaction;
    }

    protected abstract SocketClient.EventListener returnSocketListener();

    public final void setClickReaction() {
        mReaction.setValue(!mCurrentState);
    }

    public final int getCurrentPosition() {
        return mCurrentPosition;
    }

    public final void setCurrentPosition(int mCurrentPosition) {
        this.mCurrentPosition = mCurrentPosition;
    }

    public final void attachSocketClientTo(LifecycleOwner owner) {
        if (returnSocketListener() != null && owner != null) {
            mRepository.initializeSocketClient(returnSocketListener(), owner);
        }
    }
}
