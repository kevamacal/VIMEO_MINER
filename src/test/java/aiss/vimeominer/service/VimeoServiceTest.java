package aiss.vimeominer.service;

import aiss.vimeominer.model.VimeoChannel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class VimeoServiceTest {
    @Autowired
    VimeoService service;

    @Test
    @DisplayName("Get channel")
    void findChannelById(){
        VimeoChannel channel = service.findChannelById("https://api.vimeo.com/channels/1234","04f40734d4fa425fbfc3d75ce3bfba25");
        assertNotEquals(null, channel, "The channel is empty");
        System.out.println(channel);
    }
}
