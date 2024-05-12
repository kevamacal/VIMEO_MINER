package aiss.vimeominer.service;

import aiss.vimeominer.authentication.Authentication;
import aiss.vimeominer.exception.MaxVideoException;
import aiss.vimeominer.model.VideoMiner.VMVideo;
import aiss.vimeominer.model.VimeoMiner.Video.Video;
import aiss.vimeominer.model.VimeoMiner.Video.VideoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Service
public class VideoService {
    @Autowired
    RestTemplate restTemplate;

    public List<Video> getVideos(String id,Integer maxVideos) throws MaxVideoException {
        if(maxVideos < 0 || maxVideos==null){
            throw new MaxVideoException();
        } else if (maxVideos >= 0) {
            String uri = "https://api.vimeo.com/channels/" + id
                    + "/videos?per_page="+ maxVideos + "&fields=uri,name,description,release_time";

            ResponseEntity<VideoList> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    Authentication.authentication(),
                    VideoList.class);
            if (response.hasBody()){
                return response.getBody().getData();
            }else {
                return new LinkedList<Video>();
            }
        }else{
            return new LinkedList<Video>();
        }


    }
}
