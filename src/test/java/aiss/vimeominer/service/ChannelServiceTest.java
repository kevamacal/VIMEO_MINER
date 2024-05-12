package aiss.vimeominer.service;

import aiss.vimeominer.model.VimeoMiner.Channel.Channel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ChannelServiceTest {

    @Autowired
    ChannelService channelService;

    @Test
    @DisplayName("Get a channel")
    void channelSearch() {
        Channel channel = channelService.getChannel("1234");
        assertFalse(channel==null, "Channel is empty");
        System.out.println(channel);
    }
}