package jjfactory.hodol.batch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SampleTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private BatchDao batchDao;
    private List<Sample> samples;

    @BeforeEach
    void setUp(){
        batchDao = new BatchDao(jdbcTemplate);
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS SAMPLE\n" +
                "( id bigint AUTO_INCREMENT NOT NULL,\n" +
                "name VARCHAR(255) NOT NULL, color VARCHAR(255) NOT NULL,\n" +
                "PRIMARY KEY (id))");
        samples = new ArrayList<>();
        for(int i = 0; i<10000; i++ ) {
            samples.add(new Sample((long)i,"name"+i,"color"+i));
        }
    }

    @DisplayName("그냥 insert")
    @Test
    void getName() {
        int result = batchDao.insert(samples);
        assertThat(result).isEqualTo(10000);
    }

    @DisplayName("배치 insert 사용")
    @Test
    void getColor() {
        int[] results = batchDao.batchInsert(samples);
        assertThat(results.length).isEqualTo(10000);

    }
}