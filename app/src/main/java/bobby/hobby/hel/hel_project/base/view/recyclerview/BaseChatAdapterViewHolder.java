package bobby.hobby.hel.hel_project.base.view.recyclerview;

import android.view.View;

public abstract class BaseChatAdapterViewHolder extends BaseAdapterViewHolder {
    public BaseChatAdapterViewHolder(View itemView, OnAdapterItemClickListener listener) {
        super(itemView, listener);
    }

    public abstract void bindData(ChatText data);
}
