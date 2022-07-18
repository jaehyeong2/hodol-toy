package jjfactory.hodol.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Teacher {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Builder
    public Teacher(String name) {
        this.name = name;
    }
}
