package jjfactory.hodol.depth;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import static jjfactory.hodol.depth.QArticle.*;
import static jjfactory.hodol.depth.QComment.*;
import static jjfactory.hodol.depth.QCompany.*;
import static jjfactory.hodol.depth.QWriter.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class DepthQueryTest {
    @Autowired
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    @BeforeEach
    void init(){
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    @DisplayName("depth-2 테스트")
    void depthTest(){
        //given
        Writer lee = Writer.builder().name("lee").build();
        em.persist(lee);
        Writer kim = Writer.builder().name("kim").build();
        em.persist(kim);

        Article articleA = Article.builder().writer(lee)
                .name("articleA")
                .build();
        em.persist(articleA);

        Comment saveComment = Comment.builder()
                .article(articleA)
                .writer(kim)
                .content("죽여주네요!").build();
        em.persist(saveComment);

        //when
        DepthRes res = queryFactory.select(Projections.constructor(DepthRes.class, comment))
                .from(comment)
                .where(comment.article.writer.name.eq("lee"))
                .fetchOne();

        //then
        assertThat(res.getWriterName()).isEqualTo("lee");
        assertThat(res.getCommenterName()).isEqualTo("kim");
    }

    @Test
    @DisplayName("depth-3 테스트")
    void depthTest2(){
        //given
        Company company = new Company("company");
        em.persist(company);

        Writer lee = Writer.builder().name("lee").company(company).build();
        em.persist(lee);
        Writer kim = Writer.builder().name("kim").company(company).build();
        em.persist(kim);

        Article articleA = Article.builder().writer(lee)
                .name("articleA")
                .build();
        em.persist(articleA);

        Comment saveComment = Comment.builder()
                .article(articleA)
                .writer(kim)
                .content("죽여주네요!").build();
        em.persist(saveComment);

        //when
        DepthRes res = queryFactory.select(Projections.constructor(DepthRes.class, comment))
                .from(comment)
                .where(comment.article.writer.company.name.eq("company"))
                .fetchOne();

        //then
        assertThat(res.getWriterName()).isEqualTo("lee");
        assertThat(res.getCommenterName()).isEqualTo("kim");
    }

    @Test
    @DisplayName("직접 조인")
    void depthTest3(){
        //given
        Company company = new Company("company");
        em.persist(company);

        Writer lee = Writer.builder().name("lee").company(company).build();
        em.persist(lee);
        Writer kim = Writer.builder().name("kim").company(company).build();
        em.persist(kim);

        Article articleA = Article.builder().writer(lee)
                .name("articleA")
                .build();
        em.persist(articleA);

        Comment saveComment = Comment.builder()
                .article(articleA)
                .writer(kim)
                .content("죽여주네요!").build();
        em.persist(saveComment);

        //when
        DepthRes res = queryFactory.select(Projections.constructor(DepthRes.class, comment))
                .from(comment)
                .where(comment.article.writer.company.name.eq("company"))
                .innerJoin(comment.article, article)
                .innerJoin(article.writer, writer)
                .innerJoin(writer.company,QCompany.company)
                .fetchOne();

        //then
        assertThat(res.getWriterName()).isEqualTo("lee");
        assertThat(res.getCommenterName()).isEqualTo("kim");
    }

    @Test
    @DisplayName("직접 조인 + 페치")
    void depthTest4(){
        //given
        Company company = new Company("company");
        em.persist(company);

        Writer lee = Writer.builder().name("lee").company(company).build();
        em.persist(lee);
        Writer kim = Writer.builder().name("kim").company(company).build();
        em.persist(kim);

        Article articleA = Article.builder().writer(lee)
                .name("articleA")
                .build();
        em.persist(articleA);

        Comment saveComment = Comment.builder()
                .article(articleA)
                .writer(kim)
                .content("죽여주네요!").build();
        em.persist(saveComment);

        //when
        DepthRes res = queryFactory.select(Projections.constructor(DepthRes.class, comment))
                .from(comment)
                .where(comment.article.writer.company.name.eq("company"))
                .innerJoin(comment.article, article).fetchJoin()
                .innerJoin(article.writer, writer).fetchJoin()
                .innerJoin(writer.company,QCompany.company).fetchJoin()
                .fetchOne();

        //then
        assertThat(res.getWriterName()).isEqualTo("lee");
        assertThat(res.getCommenterName()).isEqualTo("kim");
    }
}
