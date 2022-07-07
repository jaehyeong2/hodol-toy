package jjfactory.hodol.service;

import jjfactory.hodol.domain.User;
import jjfactory.hodol.repository.UserRepository;
import jjfactory.hodol.req.UserCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public void userCreate(UserCreate dto){
        User user = User.create(dto);
        userRepository.save(user);
    }

}
