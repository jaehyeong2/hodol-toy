package jjfactory.hodol.service;

import jjfactory.hodol.repository.PostRepository;
import jjfactory.hodol.req.PostCreate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Test
    void write() {

        PostCreate dto = new PostCreate("제목", "내용");
        PostCreate dto2 = new PostCreate("제목", "내용");

        postService.write(dto);
        postService.write(dto2);

        Assertions.assertThat(postRepository.count()).isEqualTo(2L);
    }
}