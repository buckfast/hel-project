package bobby.hobby.hel.hel_project;

import java.util.Random;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseChatFragment;

public class Chat implements BaseChatFragment.ChatData {
    private String chat;
    private String user;

    public Chat (String chat, String user) {
        this.chat = chat;
        this.user = user;
    }

    @Override
    public boolean isCurrentUser() {
        Random random = new Random();
        return random.nextBoolean();
    }

    @Override
    public String getChat() {
        return chat;
    }

    @Override
    public String getUser() {
        return user;
    }
}
