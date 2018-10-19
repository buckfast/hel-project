package bobby.hobby.hel.hel_project.repository.internal.model.eventlist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class EventList {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("count", count).append("events", events).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(count).append(events).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EventList)) {
            return false;
        }
        EventList rhs = ((EventList) other);
        return new EqualsBuilder().append(count, rhs.count).append(events, rhs.events).isEquals();
    }

}
