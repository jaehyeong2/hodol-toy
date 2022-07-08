package jjfactory.hodol.req;

import jjfactory.hodol.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudentCreate {
    private String name;
    private String username;

    public StudentCreate(Student student) {
        this.name = student.getName();
        this.username = student.getUsername();
    }
}
