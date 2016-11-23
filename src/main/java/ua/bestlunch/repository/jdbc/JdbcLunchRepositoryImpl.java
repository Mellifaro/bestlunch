package ua.bestlunch.repository.jdbc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.Dish;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.repository.LunchRepository;
import ua.bestlunch.model.convertor.TimestampConvertor;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcLunchRepositoryImpl implements LunchRepository{

    private final RowMapper<Lunch> ROW_MAPPER_LUNCH =
            (rs, rowNum) -> new Lunch(rs.getInt("id"), rs.getString("name"),
                            rs.getBigDecimal("price"), rs.getTimestamp("datetime").toLocalDateTime());
    private final BeanPropertyRowMapper<Dish> ROW_MAPPER_DISH = BeanPropertyRowMapper.newInstance(Dish.class);
    private final BeanPropertyRowMapper<Restaurant> ROW_MAPPER_RESTAURANT = BeanPropertyRowMapper.newInstance(Restaurant.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertLunch;

    @Autowired
    public JdbcLunchRepositoryImpl(DataSource dataSource){
        insertLunch = new SimpleJdbcInsert(dataSource)
                            .withTableName("lunches")
                            .usingGeneratedKeyColumns("id");
    }

    private Timestamp toDbValue(LocalDateTime ldt){
        return Timestamp.valueOf(ldt);
    }

    @Override
    @Transactional
    public Lunch save(Lunch lunch) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", lunch.getId())
                .addValue("restaurant_id", lunch.getRestaurant().getId())
                .addValue("name", lunch.getName())
                .addValue("price", lunch.getPrice())
                .addValue("datetime", toDbValue(lunch.getDateTime()));
        if(lunch.isNew()){
            Number newId = insertLunch.executeAndReturnKey(map);
            lunch.setId(newId.intValue());
            insertDishes(lunch);
        }else{
            insertDishes(lunch);
            namedParameterJdbcTemplate.update("UPDATE lunches SET restaurant_id=:restaurant_id, name=:name, price=:price, datetime=:datetime" +
                    " WHERE id=:id", map);
        }
        return lunch;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM lunches WHERE id=?", id) != 0;
    }

    @Override
    public Lunch get(int id) {
        List<Lunch> lunches = jdbcTemplate.query("SELECT * FROM lunches WHERE id=?", ROW_MAPPER_LUNCH, id);
        lunches.forEach(lunch -> insertDishes(lunch));
        return DataAccessUtils.singleResult(lunches);
    }

    @Override
    public List<Lunch> getAll() {
        List<Lunch> lunches = jdbcTemplate.query("SELECT * FROM lunches", ROW_MAPPER_LUNCH);
        lunches.forEach(lunch -> insertDishes(lunch));
        return lunches;
    }

    @Override
    public List<Lunch> getAllByRestaurant(int restaurantId) {
        List<Lunch> lunches = jdbcTemplate.query("SELECT * FROM lunches WHERE restaurant_id=?", ROW_MAPPER_LUNCH, restaurantId);
        lunches.forEach(lunch -> insertDishes(lunch));
        return lunches;
    }

    private void insertDishes(Lunch lunch){
        List<Dish> dishes = jdbcTemplate.query("SELECT * FROM dishes INNER JOIN lunch_dish ON dishes.id=lunch_dish.dish_id WHERE  lunch_dish.lunch_id=?", ROW_MAPPER_DISH, lunch.getId());
        dishes.forEach(dish -> insertRestaurant(dish));
        lunch.setDishes(dishes);
    }

    //How to receive restaurant_id
    private void insertRestaurant(Dish dish){
        //jdbcTemplate.query("SELECT restaurant_id FROM Dishes WHERE dishes.id=?", dish.getId());
        List<Restaurant> restaurants = jdbcTemplate.query("SELECT * FROM restaurants INNER JOIN dishes ON restaurants.id=dishes.restaurant_id WHERE dishes.id=?", ROW_MAPPER_RESTAURANT, dish.getRestaurant());
    }

    @Override
    public Lunch getCurrentLunch(int restaurantId) {
        LocalDate before = LocalDate.now();
        LocalDate after = before.plusDays(1);
        List<Lunch> lunches = jdbcTemplate.query("SELECT * FROM lunches WHERE restaurant_id=? AND datetime BETWEEN ? and ?", ROW_MAPPER_LUNCH, restaurantId, before, after);
        lunches.forEach(lunch -> insertDishes(lunch));
        return DataAccessUtils.singleResult(lunches);
    }
}
