package bobby.hobby.hel.hel_project.repository.internal.model.eventlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Image {

    @SerializedName("name")
    @Expose
    private Name_ name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("cropping")
    @Expose
    private String cropping;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("photographer")
    @Expose
    private String photographer;
    @SerializedName("source")
    @Expose
    private String source;

    public Name_ getName() {
        return name;
    }

    public void setName(Name_ name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCropping() {
        return cropping;
    }

    public void setCropping(String cropping) {
        this.cropping = cropping;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("url", url).append("cropping", cropping).append("license", license).append("photographer", photographer).append("source", source).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(source).append(cropping).append(name).append(photographer).append(license).append(url).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Image) == false) {
            return false;
        }
        Image rhs = ((Image) other);
        return new EqualsBuilder().append(source, rhs.source).append(cropping, rhs.cropping).append(name, rhs.name).append(photographer, rhs.photographer).append(license, rhs.license).append(url, rhs.url).isEquals();
    }

}