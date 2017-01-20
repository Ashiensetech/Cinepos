package dao;

import entity.SeatPriceShift;
import entity.SeatType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunno on 1/16/17.
 */
@Repository
public class SeatPriceShiftDao extends BaseDao{

    @Autowired
    SeatTypeDao seatTypeDao;

    public SeatPriceShift insert(SeatPriceShift seatPriceShift){
        return (SeatPriceShift) super.insert(seatPriceShift);
    }
    public void update(SeatPriceShift seatPriceShift){
        super.update(seatPriceShift);
    }

    public boolean delete(SeatPriceShift seatPriceShift){
        return super.delete(seatPriceShift);
    }

    public List<SeatPriceShift> getAll(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM SeatPriceShift")
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<SeatPriceShift>();
    }

    public SeatPriceShift getById(Integer id){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (SeatPriceShift) session.createQuery("FROM SeatPriceShift where id = :id")
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


    public SeatPriceShift getBySeatAndDates(Integer seatTypeId,Date startDate, Date endDate){
        Session session = null;
        SeatType seatType = seatTypeDao.getById(seatTypeId);
        try{
            session = this.sessionFactory.openSession();
            return (SeatPriceShift) session.createQuery("FROM SeatPriceShift where seatType =:seatType and " +
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
