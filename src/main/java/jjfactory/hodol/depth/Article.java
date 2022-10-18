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
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @QueryInit("company.name")
    @JoinColumn(name = "writer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Writer writer;

    @Builder
    public Article(String name, Writer writer) {
        this.name = name;
        this.writer = writer;
    }
}
