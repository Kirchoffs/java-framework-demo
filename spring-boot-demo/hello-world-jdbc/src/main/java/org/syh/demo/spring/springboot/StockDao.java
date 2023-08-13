package org.syh.demo.spring.springboot;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class StockDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    public void insertData() {
        Arrays.asList(
            new Stock(2L, 22, 222), 
            new Stock(5L, 55, 555), 
            new Stock(8L, 88, 888)
        ).forEach(stock -> {
            jdbcTemplate.update("INSERT INTO STOCK VALUES (?, ?, ?)", 
            stock.getId(), stock.getVolume(), stock.getMarketCap());
        });

        Map<String, Object> row = new HashMap<>();
        row.put("VOLUME", 33);
        row.put("MARKET_CAP", 333);
        Number id = simpleJdbcInsert.executeAndReturnKey(row);
        log.info("Inserted id: {}", id);
    }

    public void listData() throws SQLException {
        log.info(
            "Count: {}", 
            jdbcTemplate.queryForObject("SELECT COUNT(*) FROM STOCK", Integer.class)
        );

        List<Long> idList = jdbcTemplate.queryForList("SELECT ID FROM STOCK", Long.class);
        idList.forEach(id -> log.info("Id: {}", id));

        List<Stock> stockList = jdbcTemplate.query("SELECT * FROM STOCK", new RowMapper<Stock>() {
            @Override
            public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Stock.builder()
                    .id(rs.getLong("ID"))
                    .volume(rs.getInt("VOLUME"))
                    .marketCap(rs.getInt("MARKET_CAP"))
                    .build();
            }
        });
        stockList.forEach(stock -> log.info("Stock: {}", stock));
    }
}
