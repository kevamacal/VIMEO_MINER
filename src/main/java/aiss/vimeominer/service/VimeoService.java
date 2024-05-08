package aiss.vimeominer.service;

import aiss.vimeominer.model.VimeoChannel;
import aiss.vimeominer.model.VimeoVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VimeoService {

    @Autowired
    RestTemplate restTemplate;

    public VimeoChannel findChannelById(String uri, String token){
        // Create request
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer" + token);
        HttpEntity<VimeoChannel> request = new HttpEntity<>(null,headers);

        // Send request
        ResponseEntity<VimeoChannel> response = restTemplate.exchange(uri, HttpMethod.GET,request,VimeoChannel.class);
        return response.getBody();
    }

}
