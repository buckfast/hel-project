package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseChatFragment;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.base.view.recyclerview.chat.BaseChatAdapter;
import bobby.hobby.hel.hel_project.base.view.recyclerview.chat.BaseChatAdapterViewHolder;
import bobby.hobby.hel.hel_project.base.view.recyclerview.chat.ChatText;

public class ChatFragmentTest extends BaseChatFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<ChatText> list = new ArrayList<>();
        list.add(new Chat("Hello", "nONE"));
        list.add(new Chat("one to three four five six seven eight nine ten eleven twelve thirteen fourteen", "YOYOYO"));
        list.add(new Chat("Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Duis at risus tellus. " +
                "Vestibulum a purus eget tellus interdum dictum. " +
                "Ut dictum quis arcu convallis placerat. Aliquam a bibendum nibh." +
                " Maecenas tempus, elit non faucibus condimentum, quam massa lacinia " +
                "lacus, vel tempor nunc mi id odio. Integer elementum nisl et orci aliquet, " +
                "ornare maximus dolor vehicula. Nulla facilisi. Ut vehicula, libero non tempor" +
                " tristique, justo tortor tristique arcu, eu porttitor mi diam eu enim. Quisque " +
                "eu ligula nec arcu aliquam efficitur at sed eros. Integer vulputate arcu at " +
                "faucibus cursus. Phasellus eget nibh nec tortor vestibulum pharetra. Fusce " +
                "lacinia quis neque sed sagittis. Suspendisse et lacus at tellus euismod " +
                "efficitur et quis ipsum. Vestibulum ante ipsum primis in faucibus orci luctus " +
                "et ultrices posuere cubilia Curae; Suspendisse ornare ligula vitae faucibus varius.", "No"));
        list.add(new Chat("Simo", "OSS"));
        list.add(new Chat("Somi", "LORA IZKU"));
        list.add(new Chat("Miso", "KARISALO"));
        list.add(new Chat("Mosi", "HEEE"));
        list.add(new Chat("Mois", "TEHEE"));

        adapter.refreshData(list);
    }

    @Override
    protected int returnRecyclerViewId() {
        return R.id.recycler;
    }

    @Override
    protected BaseChatAdapter returnAdapter() {
        return new ChatAdapter((v, position) -> {
            Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected Class returnViewModel() {
        return null;
    }

    private class ChatAdapter extends BaseChatAdapter {

        public ChatAdapter(OnAdapterItemClickListener listener) {
            super(listener);
        }

        @Override
        public int returnCurrentUserMessageLayoutId() {
            return R.layout.chattext_left;
        }

        @Override
        public int returnOtherUserMessageLayoutId() {
            return R.layout.chattext_right;
        }

        @Override
        public BaseChatAdapterViewHolder returnCurrentUserMessageViewHolderInstance(View view, OnAdapterItemClickListener listener) {
            return new LeftChatViewHolder(view, listener);
        }

        @Override
        public BaseChatAdapterViewHolder returnOtherUserMessageViewHolderInstance(View view, OnAdapterItemClickListener listener) {
            return new RightChatViewHolder(view, listener);
        }
    }

    private class LeftChatViewHolder extends BaseChatAdapterViewHolder<Chat> {
        private TextView chat, user;

        public LeftChatViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            chat = itemView.findViewById(R.id.chattextleft);
            user = itemView.findViewById(R.id.usertextleft);
        }

        @Override
        public void bindData(Chat data) {
            chat.setText(data.getChat());
            user.setText(data.getUser());
        }
    }

    private class RightChatViewHolder extends BaseChatAdapterViewHolder<Chat> {
        private TextView chat;

        public RightChatViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            chat = itemView.findViewById(R.id.chattextright);
        }

        @Override
        public void bindData(Chat data) {
            chat.setText(data.getChat());
        }
    }
}
