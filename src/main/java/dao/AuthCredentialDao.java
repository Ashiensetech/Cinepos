package dao;


import entity.AuthCredential;
import entity.Category;
import entity.UserRole;
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
    public void update(AuthCredential authCredential){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(authCredential);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }
    public AuthCredential getById(int id){
        Session session = null;

        try{
            session = this.sessionFactory.openSession();
            return (AuthCredential)session.createQuery("FROM AuthCredential where id = :id").setParameter("id", id).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;
    }
    public AuthCredential getByUserName(String userName){
        Session session = null;

        try{
            session = this.sessionFactory.openSession();
            return (AuthCredential)session.createQuery("FROM AuthCredential where userName = :userName")
                    .setParameter("userName", userName)
                    .setMaxResults(1)
                    .uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;
    }
    public boolean isUserNameUsedByOthers(int id,String userName){
        Session session = null;

        try{
            session = this.sessionFactory.openSession();
            AuthCredential authCredential =  (AuthCredential)session.createQuery("FROM AuthCredential where userName = :userName and id != :id")
                    .setParameter("userName", userName)
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .uniqueResult();
            return (authCredential!=null);
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return false;
    }
    public boolean isEmailUsedByOthers(int id,String email){
        Session session = null;

        try{
            session = this.sessionFactory.openSession();
            AuthCredential authCredential =  (AuthCredential)session.createQuery("FROM AuthCredential where userInf.email = :email and id != :id")
                    .setParameter("email", email)
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .uniqueResult();
            return (authCredential!=null);
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return false;
    }
    public List<AuthCredential> getAllAdminUser(){
        Session session = null;

        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM AuthCredential where isAdmin = true order by id desc ").list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();
    }

}
