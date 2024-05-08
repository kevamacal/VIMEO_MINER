package aiss.vimeominer.model.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties

public class Pictures {

    @JsonProperty("base_link")
    private String baseLink;

    @JsonProperty("base_link")
    public String getBaseLink() {
        return baseLink;
    }

    @JsonProperty("base_link")
    public void setBaseLink(String baseLink) {
        this.baseLink = baseLink;
    }

}
