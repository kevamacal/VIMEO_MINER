package aiss.vimeominer.service;

import aiss.vimeominer.authentication.Authentication;
import aiss.vimeominer.model.VimeoMiner.Caption.Caption;
import aiss.vimeominer.model.VimeoMiner.Caption.CaptionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.util.List;

@Service
public class CaptionService {

    @Autowired
    RestTemplate restTemplate;

    public List<Caption> getCaptions(String id){
        String uri = "https://api.vimeo.com/videos/" + id +
                "/texttracks?fields=id,language,name";

        ResponseEntity<CaptionList> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                Authentication.authentication(),
                CaptionList.class);

        return response.getBody().getData();
    }

}
