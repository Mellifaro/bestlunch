package ua.bestlunch.repository.dataJpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.User;
import ua.bestlunch.model.Vote;

@Transactional(readOnly = true)
public interface ProxyVoteRepository extends JpaRepository<Vote, Integer>{

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.user=:user")
    int delete(@Param("user") User user);
}
