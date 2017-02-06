package dao;

import entity.Combo;
import entity.Sells;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

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

        System.out.print(sells);
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
}
