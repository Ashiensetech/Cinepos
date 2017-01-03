package dao;


import entity.AuthCredential;
import entity.Category;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by omar on 8/1/16.
 */
@Repository
public class AuthCredentialDao extends BaseDao {


    public void insert(AuthCredential authCredential){
        Session session = null;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bCryptPassword = bCryptPasswordEncoder.encode(authCredential.getPassword());

        authCredential.setPassword(bCryptPassword);
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(authCredential);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

}
