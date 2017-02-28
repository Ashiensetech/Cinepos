package dao;

import entity.SeatType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class SeatTypeDao extends BaseDao {


    public void insert(SeatType seatType){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();

            if(seatType.getIsDefault()){
                SeatType defaultSeatType = getDefaultSeatType();
                defaultSeatType.setIsDefault(false);
                update(defaultSeatType);
            }

            session.save(seatType);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public void update(SeatType seatType){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            if(seatType.getIsDefault()){
                SeatType defaultSeatType = getDefaultSeatType();
                if(defaultSeatType!=null && !defaultSeatType.equals(seatType)){
                    defaultSeatType.setIsDefault(false);
                    session.update(defaultSeatType);
                }
            }
            session.update(seatType);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public boolean delete( SeatType seatType){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            if(seatType.getIsDefault()){
                SeatType newDefault = getRandomSeatType();
                newDefault.setIsDefault(true);
                update(newDefault);
            }
            session.delete(seatType);
            session.getTransaction().commit();
            return  true;
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
        return  false;
    }

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

    public SeatType getRandomSeatType(){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (SeatType) session.createQuery("FROM SeatType where isDefault =false ")
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
