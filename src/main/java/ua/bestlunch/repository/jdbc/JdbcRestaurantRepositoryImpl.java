package ua.bestlunch.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.repository.RestaurantRepository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Виктор on 23.08.2016.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcRestaurantRepositoryImpl implements RestaurantRepository {

    private final BeanPropertyRowMapper<Restaurant> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Restaurant.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertRestaurant;

    @Autowired
    public JdbcRestaurantRepositoryImpl(DataSource dataSource){
        this.insertRestaurant = new SimpleJdbcInsert(dataSource)
                                        .withTableName("restaurants")
                                        .usingGeneratedKeyColumns("id");
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", restaurant.getId())
                .addValue("name", restaurant.getName())
                .addValue("address", restaurant.getAddress())
                .addValue("phone", restaurant.getPhone());

        if(restaurant.isNew()){
            Number newId = insertRestaurant.executeAndReturnKey(map);
            restaurant.setId(newId.intValue());
        }else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE restaurants " +
                            "SET name=:name, address=:address, phone=:phone " +
                            "WHERE id=:id"
                    , map) == 0) {
                return null;
            }
        }
        return restaurant;
    }

    @Override
    @Transactional
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM restaurants WHERE id = ?", id);
    }

    @Override
    public Restaurant find(int id) {
        List<Restaurant> restaurants = jdbcTemplate.query("SELECT * FROM restaurants WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(restaurants);
    }

    @Override
    public List<Restaurant> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM restaurants ORDER BY name ASC ", ROW_MAPPER);
    }
}
