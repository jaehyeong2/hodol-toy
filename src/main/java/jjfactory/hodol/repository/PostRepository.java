package jjfactory.hodol.repository;

import jjfactory.hodol.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
