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

    public Ticket getById(Integer id){
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

}
