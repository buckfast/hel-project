package bobby.hobby.hel.hel_project.ui.fragment;

import bobby.hobby.hel.hel_project.R;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseSwipeFragment;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapter;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapterViewHolder;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.repository.internal.model.Hobby;
import bobby.hobby.hel.hel_project.ui.GlideApp;
import bobby.hobby.hel.hel_project.ui.model.SwipeItem;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;
import swipeable.com.layoutmanager.OnItemSwiped;
import swipeable.com.layoutmanager.SwipeableLayoutManager;
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper;

public class SwipeFragment extends BaseSwipeFragment<FragmentViewModel> implements BaseFragment.LongRunningTaskBehaviour{

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
                adapter.removeTopItem();
                pos++;
                checkIfEndOfList();
            }

            @Override
            public void onItemSwipedRight() {
                mViewModel.signupLikedHobbies.add(mViewModel.getSwipeHobbyList().get(pos).getName());
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
        setHasOptionsMenu(false);

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

        final Observer<List<Hobby>> hobbyListObserver = new Observer<List<Hobby>>() {
            @Override
            public void onChanged(@Nullable List<Hobby> hobbies) {
                List<SwipeItem> list = new ArrayList<>();
                for (Hobby s : hobbies) {
                    list.add(new SwipeItem(s.getName(), s.getUrl()));
                }
                adapter.refreshData(list);
            }
        };
        mViewModel.swipeHobbyList.observe(this, hobbyListObserver);
        mViewModel.fetchHobbies(5);

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

    @Override
    public int returnProgressBarContainer() {
        return 0;
    }

    @Override
    public Fragment returnProgressBarFragment() {
        return null;
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
            GlideApp.with(getContext()).load("https://"+list.get(position).getImageUrl()).into(holder.item_image);
        }
    }

    private class SwipeHolder extends BaseAdapterViewHolder {
        public TextView item_title;
        public ImageView item_image;

        public SwipeHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            item_title = itemView.findViewById(R.id.swipe_item_title);
            item_image = itemView.findViewById(R.id.swipe_item_image);
        }
    }
}
