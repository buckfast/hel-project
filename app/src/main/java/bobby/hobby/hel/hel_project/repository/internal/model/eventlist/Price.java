package bobby.hobby.hel.hel_project.repository.internal.model.eventlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Price {

    @SerializedName("sv")
    @Expose
    private String sv;
    @SerializedName("en")
    @Expose
    private String en;
    @SerializedName("fi")
    @Expose
    private String fi;

    public String getSv() {
        return sv;
    }

    public void setSv(String sv) {
        this.sv = sv;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getFi() {
        return fi;
    }

    public void setFi(String fi) {
        this.fi = fi;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("sv", sv).append("en", en).append("fi", fi).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(fi).append(sv).append(en).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Price)) {
            return false;
        }
        Price rhs = ((Price) other);
        return new EqualsBuilder().append(fi, rhs.fi).append(sv, rhs.sv).append(en, rhs.en).isEquals();
    }

}