package bobby.hobby.hel.hel_project.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseNavViewListChildFragment;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

/**
 * Description: Right side child fragment in host drawer fragment
 * Features:
 * - holds layout for list of chat channels (currently just one)
 */
public class DrawerRightFragment extends BaseNavViewListChildFragment<FragmentViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drawer_child_right, container, false);
    }

    @Override
    protected Class<FragmentViewModel> returnFragmentsViewModel() {
        return FragmentViewModel.class;
    }

    @Override
    protected BaseAdapter setUpAdapter() {
        return new DrawerRightAdapter(new OnAdapterItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                mFragmentsViewModel.setClickReaction();

            }
        }, this);
    }

    @Override
    protected int returnRecyclerViewId() {
        return R.id.right_child_recyclerview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.right_child_drawer).setOnClickListener(v -> {
            mFragmentsViewModel.setClickReaction();
        });

       /* mFragmentsViewModel.listPosition.observe(this, position -> {
            DrawerListItem data = Objects.requireNonNull(mFragmentsViewModel.drawerList.getValue().get(position));
            textView.setText(data.tv);
        });*/
    }


    private class DrawerRightChildViewHolder extends BaseAdapterViewHolder {
        private TextView text;

        DrawerRightChildViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            text = itemView.findViewById(R.id.channel_name);
        }
    }

    private class DrawerRightAdapter extends BaseAdapter<DrawerRightFragment.DrawerRightChildViewHolder> {
        private List<String> channelList;

        DrawerRightAdapter(OnAdapterItemClickListener listener, Fragment context) {
            super(listener);
            channelList = mFragmentsViewModel.channelList;
        }

        @NonNull
        @Override
        public DrawerRightFragment.DrawerRightChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_right_list_item, parent, false);
            return new DrawerRightFragment.DrawerRightChildViewHolder(v, mListener);
        }

        @Override
        public void onBindViewHolder(@NonNull DrawerRightFragment.DrawerRightChildViewHolder holder, int position) {
            holder.text.setText(channelList.get(position));
        }

        @Override
        public int getItemCount() {
            return channelList.size();
        }
    }
}