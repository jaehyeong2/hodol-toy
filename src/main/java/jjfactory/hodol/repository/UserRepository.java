package jjfactory.hodol.repository;

import jjfactory.hodol.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
