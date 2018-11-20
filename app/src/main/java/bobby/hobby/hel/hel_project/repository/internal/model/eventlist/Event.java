package bobby.hobby.hel.hel_project.repository.internal.model.eventlist;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Event {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("provider")
    @Expose
    private Provider provider;
    @SerializedName("s_desc")
    @Expose
    private SDesc sDesc;
    @SerializedName("desc")
    @Expose
    private Desc desc;
    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("price")
    @Expose
    private Price price = null;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public SDesc getSDesc() {
        return sDesc;
    }

    public void setSDesc(SDesc sDesc) {
        this.sDesc = sDesc;
    }

    public Desc getDesc() {
        return desc;
    }

    public void setDesc(Desc desc) {
        this.desc = desc;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("provider", provider).append("sDesc", sDesc).append("desc", desc).append("info", info).append("startTime", startTime).append("endTime", endTime).append("price", price).append("images", images).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(price).append(desc).append(sDesc).append(name).append(images).append(provider).append(info).append(startTime).append(endTime).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Event)) {
            return false;
        }
        Event rhs = ((Event) other);
        return new EqualsBuilder().append(id, rhs.id).append(price, rhs.price).append(desc, rhs.desc).append(sDesc, rhs.sDesc).append(name, rhs.name).append(images, rhs.images).append(provider, rhs.provider).append(info, rhs.info).append(startTime, rhs.startTime).append(endTime, rhs.endTime).isEquals();
    }

}