package ua.bestlunch.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Виктор on 20.11.2016.
 */

public class JpaUtil {

    @PersistenceContext
    private EntityManager em;

    public void clear2ndLevelHibernateCache(){
        Session session = (Session) em.getDelegate();
        SessionFactory sf = session.getSessionFactory();
        sf.getCache().evictQueryRegions();
        sf.getCache().evictDefaultQueryRegion();
        sf.getCache().evictCollectionRegions();
        sf.getCache().evictEntityRegions();
    }
}
