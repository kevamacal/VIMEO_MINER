package aiss.vimeominer.service;

import aiss.vimeominer.authentication.Authentication;
import aiss.vimeominer.model.VideoMiner.VMVideo;
import aiss.vimeominer.model.VimeoMiner.Video.Video;
import aiss.vimeominer.model.VimeoMiner.Video.VideoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VideoService {
    @Autowired
    RestTemplate restTemplate;

    public List<Video> getVideos(String id){
            String uri = "https://api.vimeo.com/channels/" + id
                    + "/videos?fields=uri,name,description,release_time";

        ResponseEntity<VideoList> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                Authentication.authentication(),
                VideoList.class);

        return response.getBody().getData();
    }
}
