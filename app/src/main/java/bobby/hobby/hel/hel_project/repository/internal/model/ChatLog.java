package bobby.hobby.hel.hel_project.repository.internal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ChatLog {

    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("date")
    @Expose
    private String date;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("user", user).append("message", message).append("room", room).append("date", date).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(date).append(user).append(room).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ChatLog)) {
            return false;
        }
        ChatLog rhs = ((ChatLog) other);
        return new EqualsBuilder().append(message, rhs.message).append(date, rhs.date).append(user, rhs.user).append(room, rhs.room).isEquals();
    }

}
