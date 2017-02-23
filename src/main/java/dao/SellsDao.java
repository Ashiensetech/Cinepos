package dao;

import entity.Combo;
import entity.Sells;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarwar on 2/2/2017.
 */
@Repository
public class SellsDao extends  BaseDao{

    public void insert(Sells sells){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(sells);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public void update(Sells sells){

        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(sells);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public Sells getById(int id){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (Sells) session.createQuery("FROM Sells where id = :id").setParameter("id", id).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;
    }
    public List<Sells> getAll(){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return  session.createQuery("FROM Sells").list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();
    }
    public List<Sells> getByDateRange(Timestamp sDate,Timestamp eDate){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return  session.createQuery("FROM Sells " +
                    " where createdAt between :sDate and :eDate")
                    .setParameter("sDate", sDate)
                    .setParameter("eDate", eDate)
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();
    }

    public boolean delete(Sells Sells){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.delete(Sells);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
            return false;
        }finally {
            if(session!=null)session.close();
        }
    }
}
