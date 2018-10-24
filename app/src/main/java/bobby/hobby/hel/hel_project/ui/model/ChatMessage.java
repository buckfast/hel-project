package bobby.hobby.hel.hel_project.ui.model;

public class ChatMessage {
    private String message;
    private String user;
    private String time;

    public ChatMessage(String message, String user, String time) {
        this.message = message;
        this.user = user;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
