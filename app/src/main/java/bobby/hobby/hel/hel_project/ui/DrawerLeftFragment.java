package bobby.hobby.hel.hel_project.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseNavViewListChildFragment;

public class DrawerLeftFragment extends BaseNavViewListChildFragment<FragmentViewModel> {

    @Override
    protected Class<FragmentViewModel> returnFragmentsViewModel() {
        return FragmentViewModel.class;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drawer_child_left, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> example = new ArrayList<>();
        example.add("hello");
        example.add("moi");
        mFragmentsViewModel.drawerList.setValue(example);
    }

    @Override
    protected BaseAdapter setUpAdapter() {
        return new DrawerLeftAdapter((v, position) -> {
            mFragmentsViewModel.listPosition.setValue(position);
        }, this);
    }


    private class DrawerLeftAdapter extends BaseAdapter<DrawerLeftChildViewHolder> {
        private List<String> mData;

        DrawerLeftAdapter(OnAdapterItemClickListener listener, Fragment context) {
            super(listener);
            mData = new ArrayList<>();
            mFragmentsViewModel.drawerList.observe(context, this::refreshData);
        }

        private void refreshData(List<String> data) {
            mData.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public DrawerLeftChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_drawer_list_item, parent, false);
            return new DrawerLeftChildViewHolder(v, mListener);
        }

        @Override
        public void onBindViewHolder(@NonNull DrawerLeftChildViewHolder holder, int position) {
            holder.textView.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }


    private class DrawerLeftChildViewHolder extends BaseAdapterViewHolder {
        public TextView textView;
        DrawerLeftChildViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            textView = itemView.findViewById(R.id.item);
        }
    }


}
