package aiss.vimeominer.service;

import aiss.vimeominer.exception.MaxVideoException;
import aiss.vimeominer.model.VimeoMiner.Video.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class VideoServiceTest {

    @Autowired
    VideoService videoService;

    @Test
    @DisplayName("Get a video")
    void videoSearch() throws MaxVideoException {
        List<Video> videos = videoService.getVideos("201084",10);
        assertFalse(videos==null, "Channel is empty");
        System.out.println(videos);
    }
}