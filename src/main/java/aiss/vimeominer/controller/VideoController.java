package aiss.vimeominer.controller;

import aiss.vimeominer.model.VimeoVideo;
import aiss.vimeominer.repository.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vimeo/videos")
public class VideoController {

    @Autowired
    private final VideoRepository repository;

    public VideoController(VideoRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<VimeoVideo> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public VimeoVideo findOne(@PathVariable long id){
        Optional<VimeoVideo> video = repository.findById(id);
        return video.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VimeoVideo create(@Valid @RequestBody VimeoVideo video){
        VimeoVideo _video = repository
                .save(new VimeoVideo(video.getName(),
                        video.getDescription(),
                        video.getDescription(),
                        video.getReleaseTime(),
                        video.getComments(),
                        video.getCaptions()
                        ));
        return _video;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody VimeoVideo updatedVideo,@PathVariable long id){
        Optional<VimeoVideo> videoData = repository.findById(id);

        VimeoVideo _video = videoData.get();
        _video.setName(updatedVideo.getName());
        _video.setDescription(updatedVideo.getDescription());
        _video.setReleaseTime(updatedVideo.getReleaseTime());
        _video.setComments(updatedVideo.getComments());
        _video.setCaptions(updatedVideo.getCaptions());
        repository.save(_video);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
    }
}
