package aiss.vimeominer.model.VimeoMiner.Comment;

import aiss.vimeominer.model.VimeoMiner.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Comment {

    @JsonProperty("user")
    private User user;

    public User getUser() { return user; }

    public void setUser(User user) {this.user = user;}

    @JsonProperty("uri")
    private String uri;
    private String id;

    @JsonProperty("text")
    private String text;

    @JsonProperty("created_time")
    private String created_on;

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("created_on")
    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getId() {
        List<String> aux = List.of(uri.split("/"));
        return aux.get(aux.size()-1);
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("created_on")
    public String getCreated_on() {
        return created_on;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", created_on='" + created_on + '\'' +
                '}';
    }


}