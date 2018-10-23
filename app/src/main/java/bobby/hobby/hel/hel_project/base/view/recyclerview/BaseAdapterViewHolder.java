package bobby.hobby.hel.hel_project.base.view.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Description: This class setup click listener for each recyclerview item
 * Works with:
 * {@link BaseAdapter}
 * {@link OnAdapterItemClickListener}
 */

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
