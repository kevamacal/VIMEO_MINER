package aiss.vimeominer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

public class VimeoUser {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("user_link")
    private String user_link;

    @JsonProperty("picture_link")
    private String picture_link;

    @Override
    public String toString() {
        return "VimeoUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user_link='" + user_link + '\'' +
                ", picture_link='" + picture_link + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_link() {
        return user_link;
    }

    public void setUser_link(String user_link) {
        this.user_link = user_link;
    }

    public String getPicture_link() {
        return picture_link;
    }

    public void setPicture_link(String picture_link) {
        this.picture_link = picture_link;
    }
}
