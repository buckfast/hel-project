package bobby.hobby.hel.hel_project.ui.model;

import bobby.hobby.hel.hel_project.base.view.recyclerview.ChatText;

public class ChatMessage implements ChatText {
    private String message;
    private Boolean received;
    private String time;

    public ChatMessage(String message, Boolean received, String time) {
        this.message = message;
        this.received = received;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean isCurrentUser() {
        return received;
    }
}
