package jjfactory.hodol.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class BatchDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private BatchDao batchDao;
    private List<Sample> samples;

    @BeforeEach
    void setUp(){
        batchDao = new BatchDao(jdbcTemplate,namedParameterJdbcTemplate);
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS SAMPLE\n" +
                "( id bigint AUTO_INCREMENT NOT NULL,\n" +
                "name VARCHAR(255) NOT NULL, color VARCHAR(255) NOT NULL,\n" +
                "PRIMARY KEY (id))");
        samples = new ArrayList<>();
        for(int i = 0; i<1000000; i++ ) {
            samples.add(new Sample((long)i,"name","color"+i));
        }
    }

    @DisplayName("그냥 insert")
    @Test
    void getName() {
        int result = batchDao.insert(samples);
        assertThat(result).isEqualTo(1000000);
    }

    @DisplayName("배치 insert 사용")
    @Test
    void getColor() {
        int[] results = batchDao.batchInsert(samples);
        assertThat(results.length).isEqualTo(1000000);

    }

    @DisplayName("배치 insert 사용 오버라이딩 없이")
    @Test
    void batchInsertV2() {
        int[] results = batchDao.batchInsertV2(samples);
        assertThat(results.length).isEqualTo(1000000);
    }

    @DisplayName("네임드 파라미터 jdbc 템플릿 이용")
    @Test
    void batchInsertV3() {
        int[] results = batchDao.batchInsertV3(samples);
        assertThat(results.length).isEqualTo(1000000);
    }

    @DisplayName("배치로 딜리트")
    @Test
    void batchDelete(){
        batchDao.batchDelete(samples);
    }
}