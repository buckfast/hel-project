package bobby.hobby.hel.hel_project.base.view.fragment.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.Util;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseChildFragment;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseNavViewListChildFragment<T extends BaseViewModel> extends BaseChildFragment<T>{

    protected abstract BaseAdapter setUpAdapter();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        //RecyclerView recyclerView = null;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            BaseAdapter adapter = setUpAdapter();
            if (adapter != null) {
                recyclerView.setAdapter(adapter);
                //recyclerView.scrollToPosition(mFragmentsViewModel.getCurrentPositionDrawer());
            }
        }
    }

    public interface OnAdapterItemClickListener {
        void onClick(View v, int position);
    }

    public abstract class BaseAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnAdapterItemClickListener mListener;

        public BaseAdapterViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onClick(view, getAdapterPosition());
            }
        }
    }

    public abstract class BaseAdapter<E extends BaseAdapterViewHolder> extends RecyclerView.Adapter<E> {
        protected OnAdapterItemClickListener mListener;

        public BaseAdapter(OnAdapterItemClickListener listener) {
            mListener = listener;
        }
    }
}
