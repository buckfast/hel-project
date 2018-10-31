package bobby.hobby.hel.hel_project.base.view.recyclerview.swipe;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapter;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapterViewHolder;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.base.view.recyclerview.RecyclerItem;

public abstract class BaseSwipeAdapter<T extends BaseAdapterViewHolder, E extends RecyclerItem> extends BaseAdapter<T, E> {
    protected abstract T returnViewHolderInstance(View v, OnAdapterItemClickListener listener);
    protected abstract int returnViewHolderLayoutId();

    public BaseSwipeAdapter(OnAdapterItemClickListener listener) {
        super(listener);
        list = new ArrayList<>();
    }

    public void refreshData(List<E> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public void addData(E item) {
        list.add(item);
        notifyDataSetChanged();
    }

    public void removeTopItem() {
        if(!list.isEmpty()) {
            list.remove(0);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(returnViewHolderLayoutId(), parent, false);
        return returnViewHolderInstance(v, mListener);
    }
}
