package ru.bender.darts.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.bender.darts.api.interfaces.Player;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
public class PlayerSQLiteDAO implements PlayerDAO {

    public static final String TABLE_NAME = "player";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int insert(Player player) {
        String sql = "INSERT INTO " + TABLE_NAME +
                " (name, best_result_of_step, best_result_of_count_shots) " +
                "VALUES (:name, :best_result_of_step, :best_result_of_count_shots);";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", player.getName());
        params.addValue("best_result_of_step", player.getBestResultOfStep());
        params.addValue("best_result_of_count_shots", player.getBestResultOfCountShots());
        KeyHolder idHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, idHolder);
        return idHolder.getKey().intValue();
    }

    @PostConstruct
    public void init() {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "    id                         INTEGER      PRIMARY KEY AUTOINCREMENT" +
                "                                            NOT NULL" +
                "                                            UNIQUE," +
                "    name                       VARCHAR (20) NOT NULL" +
                "                                            UNIQUE," +
                "    best_result_of_step        INTEGER      DEFAULT ( 0)," +
                "    best_result_of_count_shots              DEFAULT ( 0)" +
                ");";
        jdbcTemplate.getJdbcOperations().execute(sql);
    }
}
