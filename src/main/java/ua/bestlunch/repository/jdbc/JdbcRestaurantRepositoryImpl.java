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
    public List<Restaurant> getAll() {
        List<Restaurant> restaurants = jdbcTemplate.query("SELECT * FROM restaurants ORDER BY name", ROW_MAPPER);
        return restaurants;
    }

    @Override
    public Restaurant get(int id) {
        List<Restaurant> restaurants = jdbcTemplate.query("SELECT * FROM restaurants WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(restaurants);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        MapSqlParameterSource source = new MapSqlParameterSource()
                                            .addValue("id", restaurant.getId())
                                            .addValue("name", restaurant.getName())
                                            .addValue("address", restaurant.getAddress());
        if(restaurant.isNew()){
            Number id = insertRestaurant.executeAndReturnKey(source);
            restaurant.setId(id.intValue());
        }else{
            namedParameterJdbcTemplate.update("UPDATE restaurants SET name=:name, address=:address WHERE id=;id", source);
        }
        return restaurant;
    }


    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM restaurants WHERE id=?", id);
    }

}
