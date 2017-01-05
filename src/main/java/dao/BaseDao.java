package dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by mi on 12/22/16.
 */

class BaseDao {
    protected SessionFactory sessionFactory;
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        System.out.println(" ***** **** *** ** * SessionFactory Is Created * ** *** **** *****");
        this.sessionFactory = sessionFactory;
    }

}
