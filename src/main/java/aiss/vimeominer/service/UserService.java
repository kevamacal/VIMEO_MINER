package aiss.vimeominer.service;

import aiss.vimeominer.authentication.Authentication;
import aiss.vimeominer.model.VimeoMiner.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    RestTemplate restTemplate;

    public User getAuthor(String id){
        String uri = "https://api.vimeo.com/users/" + id + "?fields=uri,name,link,pictures";

        ResponseEntity<User> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                Authentication.authentication(),
                User.class
        );

        return response.getBody();
    }

}
