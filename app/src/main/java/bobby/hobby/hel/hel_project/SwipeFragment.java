package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseSwipeFragment;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapterViewHolder;
import bobby.hobby.hel.hel_project.base.view.recyclerview.RecyclerItem;
import bobby.hobby.hel.hel_project.base.view.recyclerview.swipe.BaseSwipeAdapter;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;
import swipeable.com.layoutmanager.SwipeableLayoutManager;

public class SwipeFragment extends BaseSwipeFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_swipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public int returnRecyclerViewId() {
        return R.id.recycler;
    }

    @Override
    public SwipeCallback returnOnItemSwipeCallback() {
        return new SwipeCallback() {
            @Override
            public void onItemSwiped() {
                super.onItemSwiped();
                Log.d("Swipe", "Swiped");
            }

            @Override
            public void onItemSwipedLeft() {
                Log.d("Swipe", "Left");
            }

            @Override
            public void onItemSwipedRight() {
                Log.d("Swipe", "Right");
            }

            @Override
            public void onItemSwipedUp() {
                Log.d("Swipe", "Up");
            }

            @Override
            public void onItemSwipedDown() {
                Log.d("Swipe", "Down");
            }
        };
    }

    @Override
    public SwipeableLayoutManager returnSwipeableLayoutManager() {
        return new SwipeableLayoutManager()
                .setAngle(10)
                .setAnimationDuratuion(450)
                .setMaxShowCount(3)
                .setScaleGap(0.1f)
                .setTransYGap(0);
    }

    @Override
    public BaseSwipeAdapter returnAdapter() {
        Adapter adapter = new Adapter((v, position) -> {
            //Possition is always 0, since when swipe, the view is removed
            TextView text = v.findViewById(R.id.text);
            Toast.makeText(getContext(), text.getText(), Toast.LENGTH_SHORT).show();
        });
        List<Item> list = new ArrayList<>();
        list.add(new Item("1"));
        list.add(new Item("2"));
        list.add(new Item("3"));
        list.add(new Item("4"));
        list.add(new Item("5"));
        list.add(new Item("6"));
        adapter.refreshData(list);
        return adapter;
    }

    @Override
    protected Class returnViewModel() {
        return null;
    }

    private class Holder extends BaseAdapterViewHolder {
        public TextView text;

        public Holder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            text = itemView.findViewById(R.id.text);
        }
    }

    private class Adapter extends BaseSwipeAdapter<Holder, Item> {

        @Override
        protected Holder returnViewHolderInstance(View v, OnAdapterItemClickListener listener) {
            return new Holder(v, listener);
        }

        @Override
        protected int returnViewHolderLayoutId() {
            return R.layout.item_view;
        }

        public Adapter(OnAdapterItemClickListener listener) {
            super(listener);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            Item item = list.get(position);
            holder.text.setText(item.text);
        }
    }
}
