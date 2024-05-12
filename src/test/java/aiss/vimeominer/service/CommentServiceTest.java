package aiss.vimeominer.service;

import aiss.vimeominer.exception.MaxCommentException;
import aiss.vimeominer.model.VimeoMiner.Comment.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Get a comment")
    void commentsSearch() throws MaxCommentException {
        List<Comment> comments = commentService.getComments("14987941",10);
        assertFalse(comments==null, "Comments list is empty");
        System.out.println(comments);
    }
}