package bobby.hobby.hel.hel_project.base.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapter;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapterViewHolder;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseChatFragment<T extends BaseViewModel> extends BaseFragment<T> {
    protected RecyclerView recyclerView;
    protected abstract int returnRecyclerViewId();
    protected abstract BaseChatAdapter returnAdapter();
    protected BaseChatAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(returnRecyclerViewId());
        adapter = returnAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    protected abstract class BaseChatViewHolder extends BaseAdapterViewHolder {
        public BaseChatViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
        }

        public abstract void bindData(ChatData data);
    }

    //TODO: fix this interface when the data model is more clear (this is a note for SIMO)
    public interface ChatData{
        boolean isCurrentUser();
        String getChat();
        String getUser();
    }

    protected abstract class BaseChatAdapter extends BaseAdapter {
        private List<ChatData> chatDataList;
        private static final int LEFT_MESSAGE = 2;
        private static final int RIGHT_MESSAGE = 1;
        private static final int NULL_LIST = 0;

        public abstract int returnLeftMessageViewHolderLayoutId();
        public abstract int returnRightMessageViewHolderLayoutId();
        public abstract BaseChatViewHolder returnLeftMessageViewHolderInstance(View view, OnAdapterItemClickListener listener);
        public abstract BaseChatViewHolder returnRightMessageViewHolderInstance(View view, OnAdapterItemClickListener listener);


        public void refreshData(List<ChatData> list) {
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
                ChatData data = chatDataList.get(position);
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
                    v = LayoutInflater.from(parent.getContext()).inflate(returnLeftMessageViewHolderLayoutId(), parent, false);
                    return returnLeftMessageViewHolderInstance(v, mListener);
                case RIGHT_MESSAGE:
                    v = LayoutInflater.from(parent.getContext()).inflate(returnRightMessageViewHolderLayoutId(), parent, false);
                    return returnRightMessageViewHolderInstance(v, mListener);
                case NULL_LIST:
                    //TODO: better handle this in the future (this is a note for Oskari)
                    v = LayoutInflater.from(parent.getContext()).inflate(returnRightMessageViewHolderLayoutId(), parent, false);
                    return returnRightMessageViewHolderInstance(v, mListener);
            }
            //TODO: better handle this in the future (this is a note for Oskari)
            v = LayoutInflater.from(parent.getContext()).inflate(returnRightMessageViewHolderLayoutId(), parent, false);
            return returnRightMessageViewHolderInstance(v, mListener);
        }

        @Override
        public int getItemCount() {
            return chatDataList.size();
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((BaseChatViewHolder) holder).bindData(chatDataList.get(position));
        }
    }
}
