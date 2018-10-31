package bobby.hobby.hel.hel_project.base.view.recyclerview;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: This class setup click listener for each recyclerview item
 * Works with:
 * {@link BaseAdapterViewHolder}
 * {@link OnAdapterItemClickListener}
 */

public abstract class BaseAdapter<E extends BaseAdapterViewHolder, T extends RecyclerItem> extends RecyclerView.Adapter<E> {
    protected OnAdapterItemClickListener mListener;
    protected List<T> list;

    public void refreshData(List<T> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public void addData(T item) {
        list.add(item);
        notifyDataSetChanged();
    }

    public void removeTopItem() {
        if(!list.isEmpty()) {
            list.remove(0);
            notifyDataSetChanged();
        }
    }

    public BaseAdapter(OnAdapterItemClickListener listener) {
        mListener = listener;
        list = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
