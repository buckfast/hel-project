package bobby.hobby.hel.hel_project.repository.internal.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ChatLogList {


        @SerializedName("chatLogs")
        @Expose
        private List<ChatLog> chatLogs = null;

        public List<ChatLog> getChatLogs() {
            return chatLogs;
        }

        public void setChatLogs(List<ChatLog> chatLogs) {
            this.chatLogs = chatLogs;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("chatLogs", chatLogs).toString();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(chatLogs).toHashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof ChatLogList)) {
                return false;
            }
            ChatLogList rhs = ((ChatLogList) other);
            return new EqualsBuilder().append(chatLogs, rhs.chatLogs).isEquals();
        }


}
