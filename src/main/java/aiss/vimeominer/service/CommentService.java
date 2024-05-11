package aiss.vimeominer.service;

import aiss.vimeominer.authentication.Authentication;
import aiss.vimeominer.model.VimeoMiner.Comment.Comment;
import aiss.vimeominer.model.VimeoMiner.Comment.CommentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    RestTemplate restTemplate;

    public List<Comment> getComments(String id){
        String uri = "https://api.vimeo.com/videos/" + id
                + "/comments?fields=uri,text,created_on,user";

        ResponseEntity<CommentList> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                Authentication.authentication(),
                CommentList.class
        );

        return response.getBody().getData();
    }
}
