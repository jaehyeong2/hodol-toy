package jjfactory.hodol.domain;

import jjfactory.hodol.req.UserCreate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;

    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public static User create(UserCreate dto){
        return new User(dto.getName(), dto.getUsername());
    }
}
