package aiss.vimeominer.controller;

import aiss.vimeominer.ETL.Transform;
import aiss.vimeominer.exception.MaxCommentException;
import aiss.vimeominer.exception.MaxVideoException;
import aiss.vimeominer.model.VimeoMiner.Caption.Caption;
import aiss.vimeominer.model.VimeoMiner.Channel.Channel;
import aiss.vimeominer.model.VideoMiner.*;
import aiss.vimeominer.model.VimeoMiner.Comment.Comment;
import aiss.vimeominer.model.VimeoMiner.Video.Video;
import aiss.vimeominer.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Tag(name = "Vimeo", description = "Vimeo management API")
@RestController
@RequestMapping("/videominer")
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
    RestTemplate restTemplate;

    final String videoMinerUri = "http://localhost:8080/videominer/channels";

    //GET http://localhost:8082/vimeominer/channels/{id}
    @Operation(
            summary = "Retrieve a Vimeo channel by id",
            description = "Get a Vimeo channel by specifying its id",
            tags = {"get","channels"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = VMChannel.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public VMChannel getChannel(@Parameter(description = "Id's name of the channel") @PathVariable String id,
                                @Parameter(description = "Optional parameter to limit the videos")@RequestParam(required = false,defaultValue = "10") int maxVideos,
                                @Parameter(description = "Optional parameter to limit the comments")@RequestParam(required = false,defaultValue = "10") int maxComments
    ) throws MaxVideoException, MaxCommentException {
        Channel vimeoChannel = channelService.getChannel(id);
        VMChannel channel = Transform.transformChannel(vimeoChannel);
        List<VMVideo> videos = new LinkedList<>();
        List<Video> videosVimeo = videoService.getVideos(channel.getId(),maxVideos);
        for(Video videoVimeo: videosVimeo){
            VMVideo video = Transform.transformVideo(videoVimeo);
            List<VMComment> comments = new ArrayList<>();
            List<VMCaption> captions = new ArrayList<>();

            List<Caption> captionsVimeo = captionService.getCaptions(videoVimeo.getId());
            for(Caption caption:captionsVimeo){
                VMCaption videoCaption = Transform.transformCaption(caption);
                captions.add(videoCaption);
            }
            List<Comment> commentsVimeo = commentService.getComments(videoVimeo.getId(),maxComments);
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
    @Operation(
            summary = "Insert a Vimeo channel in the Video Miner database",
            description = "Add a new Channel to the Video Miner database whose data is passed in the body of the request in JSON format",
            tags = {"channels","post"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = VMChannel.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }) })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel post(@Parameter(description = "Id's name of the channel") @PathVariable String id,
                          @Parameter(description = "Optional parameter to limit the videos")@RequestParam(required = false,defaultValue = "10") int maxVideos,
                          @Parameter(description = "Optional parameter to limit the comments")@RequestParam(required = false,defaultValue = "10") int maxComments) throws MaxVideoException, MaxCommentException {

        VMChannel channel= getChannel(id,maxVideos,maxComments);

        HttpHeaders httpHeaders= new HttpHeaders();
        HttpEntity<VMChannel> request= new HttpEntity<>(channel,httpHeaders);
        ResponseEntity<VMChannel> response= restTemplate.exchange(videoMinerUri, HttpMethod.POST,request, VMChannel.class);
        return response.getBody();
    }

}
