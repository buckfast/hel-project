package bobby.hobby.hel.hel_project.base.view.recyclerview;

import android.arch.paging.PagedListAdapter;
import android.support.v7.util.DiffUtil;

import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.Event;

public abstract class BaseListAdapter<T extends BaseAdapterViewHolder> extends PagedListAdapter<Event, T> {
    protected OnAdapterItemClickListener mListener;
    private static DiffUtil.ItemCallback<Event> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Event>() {
                @Override
                public boolean areItemsTheSame(Event oldItem, Event newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(Event oldItem, Event newItem) {
                    return oldItem.equals(newItem);
                }
            };
    protected BaseListAdapter(OnAdapterItemClickListener listener) {
        super(DIFF_CALLBACK);
        mListener = listener;
    }
}
