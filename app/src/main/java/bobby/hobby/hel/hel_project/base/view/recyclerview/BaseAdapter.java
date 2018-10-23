package bobby.hobby.hel.hel_project.base.view.recyclerview;

import android.support.v7.widget.RecyclerView;

public abstract class BaseAdapter<E extends BaseAdapterViewHolder> extends RecyclerView.Adapter<E> {
    protected OnAdapterItemClickListener mListener;

    public BaseAdapter(OnAdapterItemClickListener listener) {
        mListener = listener;
    }
}
