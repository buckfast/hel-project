package bobby.hobby.hel.hel_project.base.view.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: This class setup logic for having multiple layout/viewholder inside one recyclerview
 * Works with:
 * {@link BaseChatAdapterViewHolder}
 * {@link ChatText}
 */

public abstract class BaseChatAdapter extends BaseAdapter {
    private List<ChatText> chatDataList;
    private static final int LEFT_MESSAGE = 2;
    private static final int RIGHT_MESSAGE = 1;
    private static final int NULL_LIST = 0;

    public abstract int returnCurrentUserMessageLayoutId();
    public abstract int returnOtherUserMessageLayoutId();
    public abstract BaseChatAdapterViewHolder returnCurrentUserMessageViewHolderInstance(View view, OnAdapterItemClickListener listener);
    public abstract BaseChatAdapterViewHolder returnOtherUserMessageViewHolderInstance(View view, OnAdapterItemClickListener listener);


    public void refreshData(List<ChatText> list) {
        chatDataList.clear();
        chatDataList.addAll(list);
        notifyDataSetChanged();
    }

    public BaseChatAdapter(OnAdapterItemClickListener listener) {
        super(listener);
        chatDataList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatDataList != null) {
            ChatText data = chatDataList.get(position);
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public int getItemCount() {
        return chatDataList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((BaseChatAdapterViewHolder) holder).bindData(chatDataList.get(position));
    }
}
