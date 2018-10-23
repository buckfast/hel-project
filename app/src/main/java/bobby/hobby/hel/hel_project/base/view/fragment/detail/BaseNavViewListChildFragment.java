package bobby.hobby.hel.hel_project.base.view.fragment.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseChildFragment;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapter;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseNavViewListChildFragment<T extends BaseViewModel> extends BaseChildFragment<T>{

    protected abstract BaseAdapter setUpAdapter();
    protected abstract int returnRecyclerViewId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (returnRecyclerViewId() != -1) {
            RecyclerView recyclerView = view.findViewById(returnRecyclerViewId());
            if (recyclerView != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);
                if (setUpAdapter() != null) {
                    recyclerView.setAdapter(setUpAdapter());
                }
            }
        }
    }
}
