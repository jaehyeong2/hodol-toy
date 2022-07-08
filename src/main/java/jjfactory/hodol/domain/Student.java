package jjfactory.hodol.domain;

import jjfactory.hodol.req.StudentCreate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;


    public Student(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public static Student create(StudentCreate dto){
        return new Student(dto.getName(), dto.getUsername());
    }
}
