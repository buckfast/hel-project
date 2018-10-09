package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseNavViewListChildFragment;

public class DrawerChildOneFragment extends BaseNavViewListChildFragment<FragmentToFragmentViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navview_list_child, container, false);
    }

    @Override
    protected Class<FragmentToFragmentViewModel> returnFragmentsViewModel() {
        return FragmentToFragmentViewModel.class;
    }

    @Override
    protected BaseAdapter setUpAdapter() {
        return new DrawerChildOneAdapter((v, position) -> {
            mFragmentsViewModel.data.observe(this, data->{
                Toast.makeText(getContext(), Objects.requireNonNull(data).get(position), Toast.LENGTH_SHORT).show();
                mFragmentsViewModel.position.setValue(position);
//                No need for seperate isCloseable() anymore, if you want to close the drawer, just call this on the view listener, if not,
//                clicking on views would not close the drawer by default
//                mFragmentsViewModel.setClickReaction();
            });
        }, this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> example = new ArrayList<>();
        example.add("hello");
        example.add("moi");
        example.add("xin chao");
        example.add("nihao");
        example.add("ciao");
        example.add("howdy");
        example.add("hello");
        example.add("moi");
        example.add("xin chao");
        example.add("nihao");
        example.add("ciao");
        example.add("howdy");
        example.add("hello");
        example.add("moi");
        example.add("xin chao");
        example.add("nihao");
        example.add("ciao");
        example.add("howdy");
        example.add("hello");
        example.add("moi");
        example.add("xin chao");
        example.add("nihao");
        example.add("ciao");
        example.add("howdy");
        example.add("hello");
        example.add("moi");
        example.add("xin chao");
        example.add("nihao");
        example.add("ciao");
        example.add("howdy");
        example.add("hello");
        example.add("moi");
        example.add("xin chao");
        example.add("nihao");
        example.add("ciao");
        example.add("howdy");
        example.add("hello");
        example.add("moi");
        example.add("xin chao");
        example.add("nihao");
        example.add("ciao");
        example.add("howdy");
        example.add("hello");
        example.add("moi");
        example.add("xin chao");
        example.add("nihao");
        example.add("ciao");
        example.add("howdy");
        example.add("hello");
        example.add("moi");
        example.add("xin chao");
        example.add("nihao");
        example.add("ciao");
        example.add("howdy");
        mFragmentsViewModel.data.setValue(example);
    }

    private class DrawerChildOneViewHolder extends BaseAdapterViewHolder {
        public TextView mExampleText;

        DrawerChildOneViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            mExampleText = itemView.findViewById(R.id.just_example);
        }
    }

    private class DrawerChildOneAdapter extends BaseAdapter<DrawerChildOneViewHolder> {
        private List<String> mData;

        DrawerChildOneAdapter(OnAdapterItemClickListener listener, Fragment context) {
            super(listener);
            mData = new ArrayList<>();
            mFragmentsViewModel.data.observe(context, this::refreshData);
        }

        private void refreshData(List<String> data) {
            mData.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public DrawerChildOneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_navview_list_item, parent, false);
            return new DrawerChildOneViewHolder(v, mListener);
        }

        @Override
        public void onBindViewHolder(@NonNull DrawerChildOneViewHolder holder, int position) {
            holder.mExampleText.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
