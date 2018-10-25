package bobby.hobby.hel.hel_project.base.view.recyclerview;

/**
 * Description: This interface decide the method on how to identify which viewholder to use
 * Works with:
 * {@link BaseChatAdapter}
 * {@link BaseChatAdapterViewHolder}
 */

public interface ChatText {
    boolean isCurrentUser();
}
