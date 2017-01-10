package dao;

import entity.SeatType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 1/6/17.
 */
@Repository
public class SeatTypeDao extends BaseDao {


    public List<SeatType> getAll(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM SeatType")
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<SeatType>();

    }

    public boolean isNameUsedByOthers(int id,String name){
        Session session = null;

        try{
            session = this.sessionFactory.openSession();
            SeatType seatType =  (SeatType) session.createQuery("FROM SeatType where name = :name and id != :id")
                    .setParameter("name", name)
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .uniqueResult();
            return (seatType!=null);
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return false;
    }

    public SeatType getByName(String name){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (SeatType) session.createQuery("FROM SeatType where name = :name")
                    .setParameter("name", name)
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

    public SeatType getDefaultSeatType(){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (SeatType) session.createQuery("FROM SeatType where isDefault = true ")
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

    public SeatType getById(Integer id){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (SeatType) session.createQuery("FROM SeatType where id = :id")
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

    public boolean deleteSeatTpeById(Integer id){
        SeatType seatType = getById(id);
        return  delete(seatType);
    }

}
