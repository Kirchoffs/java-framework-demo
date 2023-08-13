package org.syh.demo.spring.springboot;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

@Repository
public class StockBatchDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void batchInsert() {
        jdbcTemplate.batchUpdate(
            "INSERT INTO STOCK VALUES (?, ?, ?)", 
            new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setLong(1, i + 50);
                    ps.setInt(2, (i + 50) * 10);
                    ps.setInt(3, (i + 50) * 100);
                }

                @Override
                public int getBatchSize() {
                    return 2;
                }
            }
        );

        List<Stock> list = new ArrayList<>();
        list.add(Stock.builder().id(101L).volume(1001).marketCap(10001).build());
        list.add(Stock.builder().id(102L).volume(1002).marketCap(10002).build());
        namedParameterJdbcTemplate.batchUpdate(
            "INSERT INTO STOCK VALUES (:id, :volume, :marketCap)",
            SqlParameterSourceUtils.createBatch(list)
        );
    }
}
