package bobby.hobby.hel.hel_project.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<DrawerListItem> array = new ArrayList<>();
        array.add(new DrawerListItem("asd", "@mipmap/ic_launcher_round"));
        array.add(new DrawerListItem("gddf", "@drawable/ic_launcher_foreground"));
        mFragmentsViewModel.drawerList.setValue(array);

    }

    @Override
    protected BaseAdapter setUpAdapter() {
        return new DrawerLeftAdapter((v, position) -> {
            mFragmentsViewModel.listPosition.setValue(position);
            v.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));
            if (mFragmentsViewModel.lastView.getValue() == null) {
                mFragmentsViewModel.lastView.setValue(v);
            } else {
                mFragmentsViewModel.lastView.getValue().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorAccentDark, null));
                mFragmentsViewModel.lastView.setValue(v);
            }
        }, this);
    }

    private class DrawerLeftChildViewHolder extends BaseAdapterViewHolder {
        public TextView textView;
        public ImageView imageView;

        DrawerLeftChildViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            textView = itemView.findViewById(R.id.item);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    private class DrawerLeftAdapter extends BaseAdapter<DrawerLeftChildViewHolder> {
        private List<DrawerListItem> listData;

        DrawerLeftAdapter(OnAdapterItemClickListener listener, Fragment context) {
            super(listener);
            listData = new ArrayList<>();
            mFragmentsViewModel.drawerList.observe(context, this::refreshData);
        }

        private void refreshData(List<DrawerListItem> data) {
            listData.clear();
            listData.addAll(data);
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
            holder.textView.setText(listData.get(position).tv);
            holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
            //holder.imageButton.setImageResource(mData.get(position).ib);
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }
    }


}
