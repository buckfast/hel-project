package bobby.hobby.hel.hel_project.chat;




public class Message {

    public enum TYPE {
        MESSAGE, INFO, TYPING, MESSAGE_SELF
    }

    private Message.TYPE type;
    private String message;
    private String username;

    private Message() {}

    public TYPE getType() {
        return type;
    };


    public String getMessage() {
        return message;
    };

    public String getUsername() {
        return username;
    };


    public static class Builder {
        private final TYPE type;
        private String username;
        private String message;

        public Builder(TYPE t) {
            this.type = t;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }


        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Message build() {
            Message message = new Message();
            message.type = this.type;
            message.username = this.username;
            message.message = this.message;
            return message;
        }
    }
}
