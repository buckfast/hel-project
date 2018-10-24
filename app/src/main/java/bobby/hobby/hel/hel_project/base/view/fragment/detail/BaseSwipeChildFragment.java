package bobby.hobby.hel.hel_project.base.view.fragment.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseChildFragment;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseSwipeChildFragment<T extends BaseViewModel> extends BaseChildFragment<T> {
    private static final String POSITION = "Position";
    protected int position;

    public static <T extends BaseSwipeChildFragment>T newInstance(int position, Class<T> clazz) {
        T f = null;
        try {
            f = clazz.newInstance();
        } catch (java.lang.InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        if (f != null) {
            f.setArguments(args);
        }

        return f;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        position = getArguments() != null ? getArguments().getInt(POSITION) : 0;
    }
}
