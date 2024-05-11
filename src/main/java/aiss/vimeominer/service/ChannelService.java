package aiss.vimeominer.service;

import aiss.vimeominer.authentication.Authentication;
import aiss.vimeominer.model.VideoMiner.VMChannel;
import aiss.vimeominer.model.VimeoMiner.Channel.Channel;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

@Service
public class ChannelService {
    @Autowired
    RestTemplate restTemplate;

    public Channel getChannel(String id){
        String uri = "https://api.vimeo.com/channels/" + id
                + "?fields=uri,name,description,created_time";

        ResponseEntity<Channel> response= restTemplate.exchange(
                uri,
                HttpMethod.GET,
                Authentication.authentication(),
                Channel.class);

        return response.getBody();
    }
}
