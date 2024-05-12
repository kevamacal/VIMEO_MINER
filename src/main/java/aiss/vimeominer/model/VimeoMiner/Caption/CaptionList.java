package aiss.vimeominer.model.VimeoMiner.Caption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)

public class CaptionList {

    @JsonProperty("data")
    private List<Caption> data;
    @JsonProperty("data")
    public List<Caption> getData() { return data;}
    @JsonProperty("data")
    public void setData(List<Caption> data) { this.data = data;}

}