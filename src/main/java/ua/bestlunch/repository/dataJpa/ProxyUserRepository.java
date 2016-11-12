package ua.bestlunch.repository.dataJpa;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.bestlunch.model.User;

@Transactional(readOnly = true)
public interface ProxyUserRepository extends JpaRepository<User,Integer>{

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @EntityGraph(value = User.GRAPH_WITH_ROLES)
    User getByEmail(String email);
}
