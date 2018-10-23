package bobby.hobby.hel.hel_project.base.view.recyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * Description: This class setup click listener for each recyclerview item
 * Works with:
 * {@link BaseAdapterViewHolder}
 * {@link OnAdapterItemClickListener}
 */

public abstract class BaseAdapter<E extends BaseAdapterViewHolder> extends RecyclerView.Adapter<E> {
    protected OnAdapterItemClickListener mListener;

    public BaseAdapter(OnAdapterItemClickListener listener) {
        mListener = listener;
    }
}
