package bobby.hobby.hel.hel_project.ui;

import bobby.hobby.hel.hel_project.R;

import android.arch.lifecycle.Observer;
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
import java.util.Objects;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseSwipeFragment;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapter;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapterViewHolder;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.ui.model.SwipeItem;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;
import swipeable.com.layoutmanager.OnItemSwiped;
import swipeable.com.layoutmanager.SwipeableLayoutManager;
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper;

public class SwipeFragment extends BaseSwipeFragment<FragmentViewModel>{

    private int pos = 0;
    private List<String> likedHobbies;

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
    public OnItemSwiped returnOnItemSwipeCallback() {
        OnItemSwiped swipeCallback = new OnItemSwiped() {
            @Override
            public void onItemSwipedLeft() {
                mViewModel.signupLikedHobbies.add(mViewModel.getSwipeHobbyList().get(pos));
                adapter.removeTopItem();
                pos++;
                checkIfEndOfList();
            }

            @Override
            public void onItemSwipedRight() {
                adapter.removeTopItem();
                pos++;
                checkIfEndOfList();
            }

            @Override
            public void onItemSwipedUp() {

            }

            @Override
            public void onItemSwipedDown() {

            }

            @Override
            public void onItemSwiped() {
                //super.onItemSwiped();
            }
        };

        return swipeCallback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        likedHobbies = new ArrayList<>();
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

    private void checkIfEndOfList() {
        if (this.pos == mViewModel.getSwipeHobbyList().size()) {
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container, new RegisterFragment()).commit();
        }
    }

    @Override
    public BaseAdapter returnAdapter() {
        SwipeAdapter adapter = new SwipeAdapter(new OnAdapterItemClickListener() {
            @Override
            public void onClick(View v, int position) {
            Log.d("asd", "lkkikked");
            }
        });

        final Observer<List<String>> hobbyListObserver = new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                List<SwipeItem> list = new ArrayList<>();
                for (String s : strings) {
                    list.add(new SwipeItem(s));
                }
                adapter.refreshData(list);
            }
        };
        mViewModel.swipeHobbyList.observe(this, hobbyListObserver);
        mViewModel.fetchHobbies();

        return adapter;
    }

    @Override
    public int returnAllowSwipeDirectionFlags() {
        return ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
    }

    @Override
    public int returnAllowDirectionFlags() {
        return ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT ;
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
            holder.item_title.setText(list.get(position).getText());
        }
    }

    private class SwipeHolder extends BaseAdapterViewHolder {
        public TextView item_title;

        public SwipeHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            item_title = itemView.findViewById(R.id.swipe_item_title);
        }
    }
}
