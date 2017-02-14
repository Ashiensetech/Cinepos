package dao;

import entity.SeatType;
import entity.Ticket;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by sunno on 1/25/17.
 */
@Repository
public class TicketDao extends BaseDao {

    @Autowired
    SeatTypeDao seatTypeDao;

    public Ticket insert(Ticket ticket){
        return (Ticket) super.insert(ticket);
    }

    public boolean insertOrUpdate(Ticket ticket){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(ticket);
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
    public void update(Ticket ticket){
        super.update(ticket);
    }

    public boolean delete(Ticket ticket){
        return super.delete(ticket);
    }


    public List<Ticket> getAll(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM Ticket")
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<Ticket>();
    }

    public Ticket getById(Long id){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (Ticket) session.createQuery("FROM Ticket where id = :id")
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


    public Ticket getBySeatAndDates(Integer seatTypeId, Date startDate, Date endDate){
        Session session = null;
        SeatType seatType = seatTypeDao.getById(seatTypeId);
        try{
            session = this.sessionFactory.openSession();
            return (Ticket) session.createQuery("FROM Ticket where seatType =:seatType and " +
                    "((startDate BETWEEN :startDate AND :endDate) or (endDate BETWEEN :startDate AND :endDate) " +
                    "or (:startDate BETWEEN startDate AND endDate) or (:endDate BETWEEN startDate AND endDate))")
                    .setParameter("seatType", seatType)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
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
    public List<Ticket> getByFilmTimeId(Integer filmTimeId){
        Session session = null;

        try{
            session = this.sessionFactory.openSession();
            return  session.createQuery("FROM Ticket where filmTime.id =:filmTimeId")
                    .setParameter("filmTimeId", filmTimeId)
                    .list();
        }
        catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }

        return null;
    }
    public Ticket getByFilmTimeAndSeatId(int filmTimeId,int seatId){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (Ticket) session.createQuery("FROM Ticket where filmTime.id = :filmTimeId and screenSeat.id = :seatId")
                    .setParameter("filmTimeId", filmTimeId)
                    .setParameter("seatId", seatId)
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

    public Ticket getBySeatId(int seatId){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (Ticket) session.createQuery("FROM Ticket where screenSeat.id = :seatId")
                    .setParameter("seatId", seatId)
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

    public boolean updateSet(Set<Ticket> ticketSet){

        Session session = null;

        if(ticketSet.size()==0){
            return false;
        }
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            int i=0;
            for(Ticket ticket:ticketSet){
                session.saveOrUpdate(ticket);
                if(i%15==0){
                    session.flush();
                    session.clear();
                }
                i++;
            }
            session.getTransaction().commit();
            return true;
        }catch (HibernateException hEx){
            // Insert to database exception log
            return false;
        }finally {
            if(session!=null)session.close();
        }
    }

}
