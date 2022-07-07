package jjfactory.hodol.req;

import jjfactory.hodol.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserCreate {
    private String name;
    private String username;

    public UserCreate(User user) {
        this.name = user.getName();
        this.username = user.getUsername();
    }
}
