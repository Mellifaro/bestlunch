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
import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;
import ua.bestlunch.repository.VoteRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcVoteRepositoryImpl implements VoteRepository{

    private final RowMapper<Vote> ROW_MAPPER = new RowMapper<Vote>() {
        @Override
        public Vote mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Vote();
        }
    };

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertVote;

    @Autowired
    public JdbcVoteRepositoryImpl(DataSource dataSource){
        insertVote = new SimpleJdbcInsert(dataSource)
                .withTableName("votes")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Vote saveVote(Vote vote) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", vote.getId())
                .addValue("user_id", vote.getUser().getId())
                .addValue("restaurant_id", vote.getRestaurant().getId());
        Number newId = insertVote.executeAndReturnKey(map);
        vote.setId(newId.intValue());
        return vote;                
    }

    @Override
    public boolean deleteVote(User user) {
        LocalDate before = LocalDate.now();
        LocalDate after = before.plusDays(1);
        return jdbcTemplate.update("DELETE FROM votes WHERE user_id=? AND vote_time BETWEEN ? and ?",
                                                    user.getId(), before.atStartOfDay(), after.atStartOfDay()) != 0;
    }

    @Override
    public List<Vote> getAllByUser(User user) {
        List<Vote> votes = jdbcTemplate.query("SELECT * FROM votes WHERE user_id = ?", ROW_MAPPER, user.getId());
        return votes;
    }

    @Override
    public int getVotesForRestaurant(int restaurantId) {
        LocalDate before = LocalDate.now();
        LocalDate after = before.plusDays(1);
        List<Vote> votes = jdbcTemplate.query("SELECT FROM votes WHERE user_id=? AND vote_time BETWEEN ? and ?",
                                ROW_MAPPER, restaurantId, before.atStartOfDay(), after.atStartOfDay());
        return votes.size();
    }

    @Override
    public Vote getCurrentVote(User user) {
        LocalDate before = LocalDate.now();
        LocalDate after = before.plusDays(1);
        List<Vote> votes = jdbcTemplate.query("SELECT FROM votes WHERE user_id=? AND vote_time BETWEEN ? and ?",
                                                ROW_MAPPER, user.getId(), before.atStartOfDay(), after.atStartOfDay());
        return DataAccessUtils.singleResult(votes);
    }
}
