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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @JoinColumn(name = "writer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Writer writer;

    @QueryInit("writer.company")
    @JoinColumn(name = "article_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    @Builder
    public Comment(String content, Writer writer, Article article) {
        this.content = content;
        this.writer = writer;
        this.article = article;
    }
}
