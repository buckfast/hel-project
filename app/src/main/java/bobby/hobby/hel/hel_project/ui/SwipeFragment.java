package bobby.hobby.hel.hel_project.ui;

import bobby.hobby.hel.hel_project.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseSwipeFragment;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapter;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapterViewHolder;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.ui.model.SwipeItem;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;
import swipeable.com.layoutmanager.SwipeableLayoutManager;

public class SwipeFragment extends BaseSwipeFragment<FragmentViewModel>{

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_swipe,container,false);
        return v;
    }

    @Override
    public int returnRecyclerViewId() {
        return R.id.swipe_recycler;
    }

    @Override
    public SwipeCallback returnOnItemSwipeCallback() {
        SwipeCallback swipeCallback = new SwipeCallback() {
            @Override
            public void onItemSwipedLeft() {
                Log.d("asd", "swiped left");
            }

            @Override
            public void onItemSwipedRight() {
                Log.d("asd", "swiped right");
            }

            @Override
            public void onItemSwipedUp() {

            }

            @Override
            public void onItemSwipedDown() {

            }

            @Override
            public void onItemSwiped() {
                super.onItemSwiped();
            }
        };

        return swipeCallback;
    }

    @Override
    public SwipeableLayoutManager returnSwipeableLayoutManager() {
        SwipeableLayoutManager layoutManager = new SwipeableLayoutManager();
        layoutManager.setAngle(0)
                     .setAnimationDuratuion(100)
                     .setMaxShowCount(3)
                     .setScaleGap(0.1f)
                     .setTransYGap(1);

        return layoutManager;
    }

    @Override
    public BaseAdapter returnAdapter() {
        SwipeAdapter adapter = new SwipeAdapter(new OnAdapterItemClickListener() {
            @Override
            public void onClick(View v, int position) {

            }
        });

        List<SwipeItem> list = new ArrayList<>();
        list.add(new SwipeItem("1"));
        list.add(new SwipeItem("2"));
        list.add(new SwipeItem("3"));
        list.add(new SwipeItem("4"));
        list.add(new SwipeItem("5"));
        list.add(new SwipeItem("6"));
        adapter.refreshData(list);

        return adapter;
    }

    @Override
    public int returnAllowSwipeDirectionFlags() {
        return 0;
    }

    @Override
    public int returnAllowDirectionFlags() {
        return 0;
    }

    @Override
    protected Class returnViewModel() {
        return FragmentViewModel.class;
    }


    private class SwipeAdapter extends BaseAdapter<SwipeHolder, SwipeItem> {
        SwipeAdapter(OnAdapterItemClickListener listener) {
            super(listener);
        }

        @NonNull
        @Override
        public SwipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SwipeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_swipe_item, parent,false), mListener);
        }

        @Override
        public void onBindViewHolder(@NonNull SwipeHolder holder, int position) {
            holder.text.setText(list.get(position).getText());
        }
    }

    private class SwipeHolder extends BaseAdapterViewHolder {
        public TextView text;

        public SwipeHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            text = itemView.findViewById(R.id.text);
        }
    }
}
