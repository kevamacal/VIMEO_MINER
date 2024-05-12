package aiss.vimeominer.service;

import aiss.vimeominer.authentication.Authentication;
import aiss.vimeominer.exception.MaxCommentException;
import aiss.vimeominer.model.VimeoMiner.Comment.Comment;
import aiss.vimeominer.model.VimeoMiner.Comment.CommentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    RestTemplate restTemplate;

    public List<Comment> getComments(String id,Integer maxComments) throws MaxCommentException {

        if (maxComments == null || maxComments < 0){
            throw new MaxCommentException();
        } else if (maxComments >= 0) {
            String uri = "https://api.vimeo.com/videos/" + id
                    + "/comments?per_page" + maxComments + "fields=uri,text,created_on,user";

            ResponseEntity<CommentList> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    Authentication.authentication(),
                    CommentList.class
            );
            if (response.hasBody()){
                return response.getBody().getData();
            }else {
                return new LinkedList<Comment>();
            }
        }else {
            return new LinkedList<Comment>();
        }


    }
}
