package bobby.hobby.hel.hel_project.repository.internal.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("hobbies")
    @Expose
    private List<String> hobbies = null;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("create_date")
    @Expose
    private String createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("email", email).append("password", password).append("hobbies", hobbies).append("token", token).append("message", message).append("createDate", createDate).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(message).append(token).append(email).append(name).append(hobbies).append(createDate).append(password).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User rhs = ((User) other);
        return new EqualsBuilder().append(message, rhs.message).append(token, rhs.token).append(email, rhs.email).append(name, rhs.name).append(hobbies, rhs.hobbies).append(createDate, rhs.createDate).append(password, rhs.password).isEquals();
    }

}
