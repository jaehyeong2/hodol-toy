package jjfactory.hodol.req;

import jjfactory.hodol.domain.Student;
import jjfactory.hodol.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeacherCreate {
    private String name;

    public TeacherCreate(Teacher teacher) {
        this.name = teacher.getName();
    }
}
