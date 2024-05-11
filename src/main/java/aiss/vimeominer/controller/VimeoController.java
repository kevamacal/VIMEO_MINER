package aiss.vimeominer.controller;

import aiss.vimeominer.ETL.Transform;
import aiss.vimeominer.model.VimeoMiner.Caption.Caption;
import aiss.vimeominer.model.VimeoMiner.Channel.Channel;
import aiss.vimeominer.model.VideoMiner.*;
import aiss.vimeominer.model.VimeoMiner.Comment.Comment;
import aiss.vimeominer.model.VimeoMiner.User.User;
import aiss.vimeominer.model.VimeoMiner.Video.Video;
import aiss.vimeominer.model.VimeoMiner.Video.VideoList;
import aiss.vimeominer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/vimeominer")
public class VimeoController {

    @Autowired
    ChannelService channelService;
    @Autowired
    VideoService videoService;
    @Autowired
    CaptionService captionService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    RestTemplate restTemplate;

    final String videoMinerUri = "http://localhost:8080/videominer/channels";

    //GET http://localhost:8082/vimeominer/channels
    /*@GetMapping("/channels")
    public List<Channel> getAllChannels(){
        return service.getAllChannels();
    }*/

    //GET http://localhost:8082/vimeominer/channels/{id}
    @GetMapping("/channels/{id}")
    public VMChannel getChannel(@PathVariable String id){
        Channel vimeoChannel = channelService.getChannel(id);
        VMChannel channel = Transform.transformChannel(vimeoChannel);
        List<VMVideo> videos = new LinkedList<>();
        List<Video> videosVimeo = videoService.getVideos(channel.getId());
        for(Video videoVimeo: videosVimeo){
            VMVideo video = Transform.transformVideo(videoVimeo);
            List<VMComment> comments = new ArrayList<>();
            List<VMCaption> captions = new ArrayList<>();

            List<Caption> captionsVimeo = captionService.getCaptions(videoVimeo.getId());
            for(Caption caption:captionsVimeo){
                VMCaption videoCaption = Transform.transformCaption(caption);
                captions.add(videoCaption);
            }
            List<Comment> commentsVimeo = commentService.getComments(videoVimeo.getId());
            for(Comment comment:commentsVimeo){
                VMComment videoComment = Transform.transformComment(comment);
                comments.add(videoComment);
            }
            video.setCaptions(captions);
            video.setComments(comments);
            videos.add(video);
        }
        channel.setVideos(videos);

        return channel;
    }

    //POST http://localhost:8082/vimeominer/channels/{id}
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/channels/{id}")
    public VMChannel post(@PathVariable String id){

        Channel canal = channelService.getChannel(id);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Channel> request = new HttpEntity<>(canal);
        ResponseEntity<VMChannel> response = restTemplate.exchange(
            videoMinerUri, HttpMethod.POST,request,VMChannel.class
        );
        return response.getBody();
    }

}
