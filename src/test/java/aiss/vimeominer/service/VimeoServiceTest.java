package aiss.vimeominer.service;

import aiss.vimeominer.model.VimeoMiner.Channel.Channel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
class VimeoServiceTest {

    @Autowired
    ChannelService service;

    @Test
    @DisplayName("Get channels")
    void getChannels() {
        String token = "04f40734d4fa425fbfc3d75ce3bfba25";
        String id = "1234";
        Channel channel = service.getChannel(id);
        assertNotNull(channel, "The channels are null");
        System.out.println(channel);
    }
/*
    @Test
    @DisplayName("Get videos")
    void getVideos() {
        String token = "1a91f47a52a63df97b35f0694c7bf4cb";
        String videosUri = "https://api.vimeo.com/videos/136262971";
        VimeoVideos videos = service.getVideos(token, videosUri);
        assertNotNull(videos, "The videos are null");
        System.out.println(videos);
    }*/
}
