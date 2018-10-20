package bobby.hobby.hel.hel_project.base.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import bobby.hobby.hel.hel_project.repository.Repository;
import bobby.hobby.hel.hel_project.repository.internal.SocketClient;

public abstract class BaseViewModel extends AndroidViewModel {
    protected Repository mRepository;
    private boolean mCurrentState = false;
    private MutableLiveData<Boolean> mReaction;
    private MutableLiveData<Integer> mCurrentPosition = new MutableLiveData<>();
    private int mCurrentPositionDrawer;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        mRepository = Repository.getInstance(application);
    }

    public final LiveData<Boolean> getClickReaction() {
        if (mReaction == null) {
            mReaction = new MutableLiveData<>();
            mReaction.setValue(mCurrentState);
            mCurrentPosition.setValue(0);
            mCurrentPositionDrawer = 0;
        }
        return mReaction;
    }

    protected abstract SocketClient.EventListener returnSocketListener();

    public final void setClickReaction() {
        mReaction.setValue(!mCurrentState);
    }

    public final MutableLiveData<Integer> getCurrentPosition() {
        return mCurrentPosition;
    }

    public final void setCurrentPosition(int mCurrentPosition) {
        this.mCurrentPosition.setValue(mCurrentPosition);
    }


    public final int getCurrentPositionDrawer() {
        return mCurrentPositionDrawer;
    }

    public final void setCurrentPositionDrawer(int mCurrentPosition) {
        this.mCurrentPositionDrawer = mCurrentPosition;
    }
    public final void attachSocketClientTo (LifecycleOwner owner) {
        if (returnSocketListener() != null && owner != null) {
            mRepository.initializeSocketClient(returnSocketListener(), owner);
        }
    }
}