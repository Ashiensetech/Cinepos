package dao;

import entity.Sells;
import entity.SellsDetails;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by Sarwar on 2/6/2017.
 */
@Repository
public class SellDetailsDao extends BaseDao{

    public void insert(SellsDetails sellsDetails){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(sellsDetails);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }





    public void update(SellsDetails sellsDetails){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(sellsDetails);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }
}
