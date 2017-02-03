package dao;

import entity.FilmScreenType;
import entity.SellsChannel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by mi on 2/3/17.
 */
@Repository
public class SellsChannelDao extends BaseDao  {
    public SellsChannel getById(Integer id){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (SellsChannel) session.createQuery("FROM SellsChannel where id = :id")
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .uniqueResult();
        }
        catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }

        return null;
    }
    public SellsChannel getWeb(){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (SellsChannel) session.createQuery("FROM SellsChannel where id = 1")
                    .uniqueResult();
        }
        catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }

        return null;
    }
    public SellsChannel getPos(){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (SellsChannel) session.createQuery("FROM SellsChannel where id = 2")
                    .uniqueResult();
        }
        catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }

        return null;
    }
    public SellsChannel getKiosk(){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (SellsChannel) session.createQuery("FROM SellsChannel where id = 3")
                    .uniqueResult();
        }
        catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }

        return null;
    }

}
