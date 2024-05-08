package aiss.vimeominer.model;

import aiss.vimeominer.model.VimeoCaption;
import aiss.vimeominer.model.VimeoComment;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class VimeoVideo {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    @NotEmpty(message = "Video name cannot be empty")
    private String name;

    @JsonProperty("description")
    @Column(columnDefinition="TEXT")
    private String description;

    @JsonProperty("releaseTime")
    @NotEmpty(message = "Video release time cannot be empty")
    private String releaseTime;

    @JsonProperty("comments")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "videoId")
    @NotNull(message = "Video comments cannot be null")
    private List<VimeoComment> vimeoComments;

    @JsonProperty("captions")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "videoId")
    @NotNull(message = "Video captions cannot be null")
    private List<VimeoCaption> vimeoCatpions;

    public VimeoVideo(String id, String name, String description, String releaseTime, List<VimeoComment> vimeoComments, List<VimeoCaption> vimeoCatpions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseTime = releaseTime;
        this.vimeoComments = vimeoComments;
        this.vimeoCatpions = vimeoCatpions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public List<VimeoComment> getComments() {
        return vimeoComments;
    }

    public void setComments(List<VimeoComment> VimeoComments) {
        this.vimeoComments = vimeoComments;
    }

    public List<VimeoCaption> getCaptions() {
        return vimeoCatpions;
    }

    public void setCaptions(List<VimeoCaption> VimeoCatpions) {
        this.vimeoCatpions = vimeoCatpions;
    }

    @Override
    public String toString() {
        return "VimeoVideo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", comments=" + vimeoComments +
                ", captions=" + vimeoCatpions +
                '}';
    }
}
