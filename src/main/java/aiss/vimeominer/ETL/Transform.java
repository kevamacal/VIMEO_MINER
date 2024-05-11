package aiss.vimeominer.ETL;

import aiss.vimeominer.model.VideoMiner.*;
import aiss.vimeominer.model.VimeoMiner.Caption.Caption;
import aiss.vimeominer.model.VimeoMiner.Channel.Channel;
import aiss.vimeominer.model.VimeoMiner.Comment.Comment;
import aiss.vimeominer.model.VimeoMiner.User.User;
import aiss.vimeominer.model.VimeoMiner.Video.Video;

public class Transform {

    public static VMChannel transformChannel(Channel vimeoChannel){
        String id = vimeoChannel.getId();
        String name = vimeoChannel.getName();
        String description = vimeoChannel.getDescription();
        String createdTime = vimeoChannel.getCreatedTime();
        return new VMChannel(id,name,description,createdTime);
    }

    public static VMVideo transformVideo(Video vimeoVideo){
        String id = vimeoVideo.getId();
        String name = vimeoVideo.getName();
        String description = vimeoVideo.getDescription();
        String releaseTime = vimeoVideo.getCreatedTime();
        return new VMVideo(id,name,description,releaseTime);
    }

    public static VMCaption transformCaption(Caption vimeoCaption){
        String id = vimeoCaption.getId();
        String name = vimeoCaption.getName();
        String language = vimeoCaption.getLanguage();
        return new VMCaption(id,name,language);
    }

    public static VMComment transformComment(Comment vimeoComment){
        String id = vimeoComment.getId();
        String text = vimeoComment.getText();
        String createdOn = vimeoComment.getCreated_on();
        VMUser author = transformUser(vimeoComment.getUser());
        return new VMComment(id,text,createdOn,author);
    }

    public static VMUser transformUser(User vimeoUser){
        Long id = Long.valueOf(vimeoUser.getId());
        String name = vimeoUser.getName();
        String userLink = vimeoUser.getLink();
        String pictureLink = vimeoUser.getPicture_link();
        return new VMUser(id,name,userLink,pictureLink);
    }

}
