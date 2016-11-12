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
import ua.bestlunch.model.Dish;
import ua.bestlunch.model.Lunch;
import ua.bestlunch.model.Restaurant;
import ua.bestlunch.repository.DishRepository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcDishRepositoryImpl implements DishRepository{

    private final BeanPropertyRowMapper<Lunch> ROW_MAPPER_LUNCH = BeanPropertyRowMapper.newInstance(Lunch.class);
    private final BeanPropertyRowMapper<Dish> ROW_MAPPER_DISH = BeanPropertyRowMapper.newInstance(Dish.class);
    private final BeanPropertyRowMapper<Restaurant> ROW_MAPPER_RESTAURANT = BeanPropertyRowMapper.newInstance(Restaurant.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertDish;

    @Autowired
    public JdbcDishRepositoryImpl(DataSource dataSource){
        insertDish = new SimpleJdbcInsert(dataSource)
                .withTableName("dishes")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    @Transactional
    public Dish save(Dish dish) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", dish.getId())
                .addValue("restaurant_id", dish.getRestaurant().getId())
                .addValue("name", dish.getName())
                .addValue("price", dish.getPrice());
        if(dish.isNew()){
            Number newId = insertDish.executeAndReturnKey(map);
            dish.setId(newId.intValue());
            insertLunches(dish);
        }else{
            insertLunches(dish);
            namedParameterJdbcTemplate.update("UPDATE dishes SET restaurant_id=:restaurant_id, name=:name, price=:price" +
                    " WHERE id=:id", map);
        }
        return dish;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM dishes WHERE id=?", id) != 0;
    }

    @Override
    public Dish find(int id) {
        List<Dish> dishes = jdbcTemplate.query("SELECT * FROM dishes WHERE id=?", ROW_MAPPER_DISH, id);
        dishes.forEach(dish -> insertLunches(dish));
        return DataAccessUtils.singleResult(dishes);
    }

    @Override
    public List<Dish> findAllByLunch(int lunchId) {
        List<Dish> dishes = jdbcTemplate.query("SELECT * FROM dishes INNER JOIN lunch_dish ON dishes.id = lunch_dish.dish_id WHERE lunch_dish.lunch_id=?",ROW_MAPPER_DISH, lunchId);
        dishes.forEach(dish -> insertLunches(dish));
        return dishes;
    }

    @Override
    public List<Dish> findAllByRestaurant(int restaurantId) {
        List<Dish> dishes = jdbcTemplate.query("SELECT * FROM dishes WHERE restaurant_id=?", ROW_MAPPER_DISH, restaurantId);
        dishes.forEach(dish -> insertLunches(dish));
        return dishes;
    }

    private void insertLunches(Dish dish){
        List<Lunch> lunches = jdbcTemplate.query("SELECT * FROM lunches INNER JOIN lunch_dish ON lunches.id=lunch_dish.lunch_id WHERE  lunch_dish.dish_id=?", ROW_MAPPER_LUNCH, dish.getId());
        dish.setLunches(lunches);
    }
}
