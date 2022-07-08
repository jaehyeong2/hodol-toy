package jjfactory.hodol.service;

import jjfactory.hodol.domain.Post;
import jjfactory.hodol.repository.PostRepository;
import jjfactory.hodol.req.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PostService {

    private final PostRepository postRepository;

    public String write(PostCreate dto){
        Post post = new Post(dto.getTitle(), dto.getContent());
        postRepository.save(post);
        return "Y";
    }
}
