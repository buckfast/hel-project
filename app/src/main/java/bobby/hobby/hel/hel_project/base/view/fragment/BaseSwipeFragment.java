package bobby.hobby.hel.hel_project.base.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapter;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;
import swipeable.com.layoutmanager.OnItemSwiped;
import swipeable.com.layoutmanager.SwipeableLayoutManager;
import swipeable.com.layoutmanager.SwipeableTouchHelperCallback;
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper;

public abstract class BaseSwipeFragment<T extends BaseViewModel> extends BaseFragment<T> {
    protected RecyclerView recyclerView;
    protected BaseAdapter adapter;

    public abstract int returnRecyclerViewId();
    public abstract SwipeCallback returnOnItemSwipeCallback();
    public abstract SwipeableLayoutManager returnSwipeableLayoutManager();
    public abstract BaseAdapter returnAdapter();
    public abstract int returnAllowSwipeDirectionFlags();
    public abstract int returnAllowDirectionFlags();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (returnRecyclerViewId() != -1) {
            recyclerView = view.findViewById(returnRecyclerViewId());
            adapter = returnAdapter();

            SwipeableTouchHelperCallback callback = new SwipeableTouchHelperCallback(returnOnItemSwipeCallback()) {
                @Override
                public int getAllowedSwipeDirectionsMovementFlags() {
                    return returnAllowSwipeDirectionFlags();
                }

                @Override
                public int getAllowedDirectionsMovementFlags() {
                    return returnAllowDirectionFlags();
                }
            };

            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(recyclerView);

            recyclerView.setLayoutManager(returnSwipeableLayoutManager());

            recyclerView.setAdapter(adapter);
        }
    }

    public abstract class SwipeCallback implements OnItemSwiped {
        @Override
        public void onItemSwiped() {
            adapter.removeTopItem();
        }
    }
}
