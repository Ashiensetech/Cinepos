package dao;

import entity.Category;
import entity.ScreenSeat;
import entity.SeatType;
import jdk.nashorn.internal.runtime.options.Option;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by mi on 1/9/17.
 */
@Repository
public class ScreenSeatDao  extends BaseDao {
    @Autowired
    SeatTypeDao seatTypeDao;
    public ScreenSeat getById(Integer id){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (ScreenSeat) session.createQuery("FROM ScreenSeat where id = :id")
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

    public ScreenSeat getBySeatTypeAndScreenIdAndSeatName(Integer screenId,Integer seatTypeId,String name){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (ScreenSeat) session.createQuery("FROM ScreenSeat where screenId = :screenId and seatType = :seatTypeId and name=:name")
                    .setParameter("screen_id", screenId)
                    .setParameter("seat_type_id", seatTypeId)
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

    public List<ScreenSeat> getByScreenId(Integer screenId){
        System.out.println("getByScreenId caled");
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM ScreenSeat where screenId = :screenId")
                    .setParameter("screenId", screenId)
                    .list();
        }
        catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally {

            if(session!=null)session.close();
        }

        return null;
    }

    public void update(ScreenSeat screenSeat){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(screenSeat);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }
    public void insertOrUpdate(List<ScreenSeat> screenSeatList){
        Session session = null;

        List<SeatType> seatTypes = seatTypeDao.getAll();
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            for(int i=0;i<screenSeatList.size();i++){
                ScreenSeat screenSeat = screenSeatList.get(i);


                Optional<SeatType> optionalSeatType = seatTypes.stream().filter(st -> st.getId() == screenSeat.getSeatType().getId()).findFirst();
                if(optionalSeatType.isPresent()){
                    screenSeat.setSeatType(optionalSeatType.get());
                }
                session.saveOrUpdate(screenSeat);
                if(i%15==0){
                    session.flush();
                    session.clear();
                }
            }

            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }
}
