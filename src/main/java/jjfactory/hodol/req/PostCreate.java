package jjfactory.hodol.req;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostCreate {

    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "콘텐츠를 입력해주세요")
    private String content;
}
