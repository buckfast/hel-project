package bobby.hobby.hel.hel_project.base.view.recyclerview.chat;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapter;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;

/**
 * Description: This class setup logic for having multiple layout/viewholder inside one recyclerview
 * Works with:
 * {@link BaseChatAdapterViewHolder}
 * {@link ChatText}
 */

public abstract class BaseChatAdapter<T extends ChatText> extends BaseAdapter<BaseChatAdapterViewHolder<T>, T> {
    private static final int LEFT_MESSAGE = 2;
    private static final int RIGHT_MESSAGE = 1;
    private static final int NULL_LIST = 0;

    public abstract int returnCurrentUserMessageLayoutId();
    public abstract int returnOtherUserMessageLayoutId();
    public abstract BaseChatAdapterViewHolder<T> returnCurrentUserMessageViewHolderInstance(View view, OnAdapterItemClickListener listener);
    public abstract BaseChatAdapterViewHolder<T> returnOtherUserMessageViewHolderInstance(View view, OnAdapterItemClickListener listener);

    public BaseChatAdapter(OnAdapterItemClickListener listener) {
        super(listener);
        list = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (list != null) {
            ChatText data = list.get(position);
            if (data.isCurrentUser()) {
                return RIGHT_MESSAGE;
            } else {
                return LEFT_MESSAGE;
            }
        }
        return NULL_LIST;
    }

    @NonNull
    @Override
    public BaseChatAdapterViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case LEFT_MESSAGE:
                v = LayoutInflater.from(parent.getContext()).inflate(returnCurrentUserMessageLayoutId(), parent, false);
                return returnCurrentUserMessageViewHolderInstance(v, mListener);
            case RIGHT_MESSAGE:
                v = LayoutInflater.from(parent.getContext()).inflate(returnOtherUserMessageLayoutId(), parent, false);
                return returnOtherUserMessageViewHolderInstance(v, mListener);
            case NULL_LIST:
                //TODO: better handle this in the future (this is a note for Oskari)
                v = LayoutInflater.from(parent.getContext()).inflate(returnOtherUserMessageLayoutId(), parent, false);
                return returnOtherUserMessageViewHolderInstance(v, mListener);
        }
        //TODO: better handle this in the future (this is a note for Oskari)
        v = LayoutInflater.from(parent.getContext()).inflate(returnOtherUserMessageLayoutId(), parent, false);
        return returnOtherUserMessageViewHolderInstance(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseChatAdapterViewHolder<T> holder, int position) {
        holder.bindData(list.get(position));
    }
}
