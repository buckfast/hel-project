package bobby.hobby.hel.hel_project;

import java.util.Random;

import bobby.hobby.hel.hel_project.base.view.recyclerview.chat.ChatText;

public class Chat implements ChatText {
    private String chat;
    private String user;

    public Chat(String chat, String user) {
        this.chat = chat;
        this.user = user;
    }

    @Override
    public boolean isCurrentUser() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public String getChat() {
        return chat;
    }

    public String getUser() {
        return user;
    }
}
