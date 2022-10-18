package jjfactory.hodol.depth;

import com.querydsl.core.annotations.QueryInit;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Writer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int writerType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @Builder
    public Writer(String name, int writerType, Company company) {
        this.name = name;
        this.writerType = writerType;
        this.company = company;
    }
}
