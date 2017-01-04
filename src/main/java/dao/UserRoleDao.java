package dao;


import entity.Category;
import entity.UserInf;
import entity.UserRole;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by omar on 8/1/16.
 */
@Repository
public class UserRoleDao extends BaseDao {


    public UserRole getById(int id){
        Session session = null;

        try{
            session = this.sessionFactory.openSession();
            return (UserRole)session.createQuery("FROM UserRole where id = :id").setParameter("id", id).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;
    }

    public List<UserRole> getAll(){
        Session session = null;

        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM UserRole ").list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();
    }

}
