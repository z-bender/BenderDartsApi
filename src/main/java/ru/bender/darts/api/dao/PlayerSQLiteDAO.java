package ru.bender.darts.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;
import ru.bender.darts.api.impl.PlayerImpl;
import ru.bender.darts.api.interfaces.Player;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PlayerSQLiteDAO implements PlayerDAO {

    public static final String TABLE_NAME = "player";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int insert(Player player) {
        String sql = "INSERT INTO " + TABLE_NAME +
                " (name, best_result_of_step, best_result_of_count_shots) " +
                "VALUES (:name, :best_result_of_step, :best_result_of_count_shots);";
        KeyHolder idHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, getMapSqlParameterSource(player), idHolder);
        return idHolder.getKey().intValue();
    }

    @Override
    public void update(Player player) {
        String sql = "UPDATE player " +
                "SET " +
                "   name = :name, " +
                "   best_result_of_step = :best_result_of_step, " +
                "   best_result_of_count_shots = :best_result_of_count_shots " +
                "WHERE id = :id;";
        jdbcTemplate.update(sql, getMapSqlParameterSource(player));
    }

    @Override
    public Player getPlayer(int ID) {
        String sql = "SELECT id, name, best_result_of_step, best_result_of_count_shots" +
                "  FROM player" +
                "  WHERE id = :id;";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", ID);
        return (Player) jdbcTemplate.queryForObject(sql, params, new PlayerMapper());
    }

    @Override
    public List<Player> getPlayerList() {
        return null;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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

    private MapSqlParameterSource getMapSqlParameterSource(Player player) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", player.getID());
        params.addValue("name", player.getName());
        params.addValue("best_result_of_step", player.getBestResultOfStep());
        params.addValue("best_result_of_count_shots", player.getBestResultOfCountShots());
        return params;
    }

    private static final class PlayerMapper implements RowMapper {
        @Override
        public Player mapRow(ResultSet resultSet, int i) throws SQLException {
            //TODO: заменить реализацию на интерфейс (видимо надо инжектить через spring)
            Player player = new PlayerImpl();
            player.setID(resultSet.getInt("id"));
            player.setName(resultSet.getString("name"));
            player.setBestResultOfStep(resultSet.getShort("best_result_of_step"));
            player.setBestResultOfCountShots(resultSet.getShort("best_result_of_count_shots"));
            return player;
        }
    }

}
