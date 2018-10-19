package bobby.hobby.hel.hel_project.repository.internal.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class HobbyList {

    @SerializedName("hobbies")
    @Expose
    private List<String> hobbies = null;

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("hobbies", hobbies).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(hobbies).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof HobbyList) == false) {
            return false;
        }
        HobbyList rhs = ((HobbyList) other);
        return new EqualsBuilder().append(hobbies, rhs.hobbies).isEquals();
    }

}
