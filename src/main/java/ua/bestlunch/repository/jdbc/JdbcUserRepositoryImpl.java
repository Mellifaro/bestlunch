package ua.bestlunch.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.Role;
import ua.bestlunch.model.User;
import ua.bestlunch.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
@Transactional(readOnly = true)
//Add set roles (methods are not finished)
public class JdbcUserRepositoryImpl implements UserRepository{

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);
    private static final RowMapper<Role> ROLE_ROW_MAPPER = (rs, rowNum) -> Role.valueOf(rs.getString("role"));

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertRestaurant;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource){
        this.insertRestaurant = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    @Transactional
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("registered", user.getRegistered());

        if(user.isNew()){
            Number newId = insertRestaurant.executeAndReturnKey(map);
            user.setId(newId.intValue());
            insertRoles(user);
        }else {
            deleteRoles(user);
            insertRoles(user);
            namedParameterJdbcTemplate.update(
                    "UPDATE users SET name=:name, email=:email, password=:password, " +
                            "registered=:registered WHERE id=:id", map);
        }
        return user;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        users.forEach(this::setRoles);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        class UserRole{
            final private Role role;
            final private int userId;

            UserRole(Role role, int userId){
                this.role = role;
                this.userId = userId;
            }

            public Role getRole() {
                return role;
            }

            public int getUserId() {
                return userId;
            }
        }

        Map<Integer, List<Role>> userRolesMap = jdbcTemplate.query("SELECT role, user_id FROM user_roles",
                (rs, rowNum) -> new UserRole(Role.valueOf(rs.getString("role")), rs.getInt("user_id"))).stream()
                .collect(
                        Collectors.groupingBy(UserRole::getUserId, Collectors.mapping(UserRole::getRole, Collectors.toList()))
                );

        List<User> users = jdbcTemplate.query("SELECT * FROM users ORDER BY name", ROW_MAPPER);
        users.forEach(user -> user.setRoles(userRolesMap.get(user.getId())));
        return users;
    }

    private User setRoles (User u){
        if(u != null){
            List<Role> roles = jdbcTemplate.query("SELECT role FROM user_roles WHERE user_id=?", ROLE_ROW_MAPPER, u.getId());
            u.setRoles(roles);
        }
        return u;
    }

    private void deleteRoles(User u){
        jdbcTemplate.update("DELETE FROM user_roles WHERE user_id=?", u.getId());
    }

    private void insertRoles(User u){
        Set<Role> roles = u.getRoles();
        Iterator<Role> iterator = roles.iterator();

        jdbcTemplate.batchUpdate("INSERT INTO user_roles VALUES (?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, u.getId());
                ps.setString(2, iterator.next().name());
            }

            @Override
            public int getBatchSize() {
                return roles.size();
            }
        });
    }
}
