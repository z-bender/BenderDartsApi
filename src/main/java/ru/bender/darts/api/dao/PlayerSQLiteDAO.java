package ru.bender.darts.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bender.darts.api.interfaces.Player;

import javax.sql.DataSource;

@Component
public class PlayerSQLiteDAO implements PlayerDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void insert(Player player) {
        String sql = "CREATE TABLE test (" +
                "id    INTEGER       PRIMARY KEY AUTOINCREMENT," +
                "field VARCHAR (100) NOT NULL" +
                ");";
        jdbcTemplate.getJdbcOperations().execute(sql);
    }
}
