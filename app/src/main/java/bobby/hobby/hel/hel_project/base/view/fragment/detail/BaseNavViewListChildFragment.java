package bobby.hobby.hel.hel_project.base.view.fragment.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.Util;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseChildFragment;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseNavViewListChildFragment<T extends BaseViewModel> extends BaseChildFragment<T>{

    protected abstract BaseAdapter setUpAdapter();
    protected abstract int returnRecyclerViewId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (returnRecyclerViewId() != -1) {
            RecyclerView recyclerView = view.findViewById(returnRecyclerViewId());
            if (recyclerView != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);
                if (setUpAdapter() != null) {
                    recyclerView.setAdapter(setUpAdapter());

                    //the only working solution to keep selected item selected graphically after orientation change
                    //*programmatically click the item*
                    recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            if (recyclerView.findViewHolderForAdapterPosition(mFragmentsViewModel.getCurrentPositionDrawer()) != null) {
                                recyclerView.findViewHolderForAdapterPosition(mFragmentsViewModel.getCurrentPositionDrawer()).itemView.performClick();
                                recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);

                            }
                            return true;
                        }
                    });
                }

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
