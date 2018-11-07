package bobby.hobby.hel.hel_project.base.view.recyclerview;

/**
 * Description: This interface defy the self-bind behaviour of viewholder
 * Works with:
 * {@link bobby.hobby.hel.hel_project.base.view.recyclerview.chat.BaseChatAdapterViewHolder}
 * {@link bobby.hobby.hel.hel_project.base.view.recyclerview.chat.BaseChatAdapter}
 */

@FunctionalInterface
public interface ViewHolderBind<T extends RecyclerItem> {
    void bindData(T data);
}
