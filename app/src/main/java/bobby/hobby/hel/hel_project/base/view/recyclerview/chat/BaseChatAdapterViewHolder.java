package bobby.hobby.hel.hel_project.base.view.recyclerview.chat;

import android.view.View;

import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapterViewHolder;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.base.view.recyclerview.ViewHolderBind;

/**
 * Description: This class defy data type and selfbinding behaviour
 * Works with:
 * {@link BaseChatAdapter}
 * {@link ChatText}
 */

public abstract class BaseChatAdapterViewHolder<T extends ChatText> extends BaseAdapterViewHolder implements ViewHolderBind<T> {
    public BaseChatAdapterViewHolder(View itemView, OnAdapterItemClickListener listener) {
        super(itemView, listener);
    }
}
