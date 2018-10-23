package bobby.hobby.hel.hel_project.base.view.recyclerview;

import android.view.View;

/**
 * Description: This class make encapsulate viewholder, they bind their own data
 * Works with:
 * {@link BaseChatAdapter}
 * {@link ChatText}
 */

public abstract class BaseChatAdapterViewHolder extends BaseAdapterViewHolder {
    public BaseChatAdapterViewHolder(View itemView, OnAdapterItemClickListener listener) {
        super(itemView, listener);
    }

    public abstract void bindData(ChatText data);
}
