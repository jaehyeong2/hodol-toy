package jjfactory.hodol.res;

import jjfactory.hodol.domain.Assignment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AssignmentRes {
    private String title;
    private String content;

    public AssignmentRes(Assignment assignment) {
        this.title = assignment.getTitle();
        this.content = assignment.getContent();
    }
}
