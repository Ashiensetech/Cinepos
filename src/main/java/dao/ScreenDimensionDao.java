package dao;

import entity.AuthCredential;
import entity.Category;
import entity.ScreenDimension;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 1/5/17.
 */
@Repository
public class ScreenDimensionDao extends BaseDao {

    public void insert(ScreenDimension screenDimension){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(screenDimension);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }
    public ScreenDimension getById(int id){
        Session session = null;

        try{
            session = this.sessionFactory.openSession();
            return (ScreenDimension)session.createQuery("FROM ScreenDimension where id = :id").setParameter("id", id).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;
    }
    public List<ScreenDimension> getAll(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM ScreenDimension")
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<ScreenDimension>();

    }
}
