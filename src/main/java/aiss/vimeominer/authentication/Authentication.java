package aiss.vimeominer.authentication;

import aiss.vimeominer.model.VideoMiner.VMChannel;
import aiss.vimeominer.model.VimeoMiner.Channel.Channel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class Authentication {

    public static HttpEntity<Channel> authentication(){
        String token = "04f40734d4fa425fbfc3d75ce3bfba25";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+ token);
        return new HttpEntity<>(null, headers);
    }

}
