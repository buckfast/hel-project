package bobby.hobby.hel.hel_project.base.view.recyclerview.chat;

import android.view.View;

import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapterViewHolder;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.base.view.recyclerview.ViewHolderBind;

/**
 * Description: This class make encapsulate viewholder, they bind their own data
 * Works with:
 * {@link BaseChatAdapter}
 * {@link ChatText}
 */

public abstract class BaseChatAdapterViewHolder extends BaseAdapterViewHolder implements ViewHolderBind<ChatText> {
    public BaseChatAdapterViewHolder(View itemView, OnAdapterItemClickListener listener) {
        super(itemView, listener);
    }
}
