package jjfactory.hodol.batch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BatchDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int insert(List<Sample> samples){
        String sql = "INSERT INTO SAMPLE(name,color) " +
                "VALUES (?,?)";

        int result = 0;
        for (int i = 0; i < samples.size() ; i++) {
            Sample sample = samples.get(i);
            result += jdbcTemplate.update(sql, sample.getName(), sample.getColor());
        }
        return result;
    }

    public int[] batchInsert(List<Sample> samples){
        String sql = "INSERT INTO SAMPLE(name,color) VALUES(?,?)";

        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Sample sample = samples.get(i);
                ps.setString(1,sample.getName());
                ps.setString(2,sample.getColor());
            }

            @Override
            public int getBatchSize() {
                return samples.size();
            }
        });
    }

    public int[] batchInsertV2(List<Sample> samples){
        String sql = "INSERT INTO SAMPLE(name,color) VALUES(?,?)";
        List<Object[]> batches = new ArrayList<>();
        for(Sample s : samples){
            Object[] values = new Object[]{s.getName(),s.getColor()};
            batches.add(values);
        }

        return jdbcTemplate.batchUpdate(sql,batches);
    }

    public int[] batchInsertV3(List<Sample> samples) {
        String sql = "INSERT INTO SAMPLE(name,color) VALUES(:name,:color)";
        return namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(samples));
    }

    public int[] batchDelete(List<Sample> samples){
        String sql = "DELETE FROM SAMPLE WHERE SAMPLE.name = (?)";
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Sample sample = samples.get(i);
                ps.setString(1,sample.getName());
            }

            @Override
            public int getBatchSize() {
                return samples.size();
            }
        });
    }
}

@AllArgsConstructor
@Getter
class Sample{
    private Long id;
    private String name;
    private String color;
}
