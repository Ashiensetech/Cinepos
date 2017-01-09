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
    public void insert(ScreenSeat screenSeat){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(screenSeat);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }
//    public void insert(List<ScreenSeat> screenSeatList){
//        Session session = null;
//
//        List<SeatType> seatType = seatTypeDao.getAll();
//        try {
//            session = this.sessionFactory.openSession();
//            session.beginTransaction();
//            for(ScreenSeat screenSeat: screenSeatList)
//            Optional<SeatType> seatTypes = seatType.stream().filter(st -> st.getId() == screenSeat.getId()).findFirst();
//
//            session.save(screenSeat);
//            session.getTransaction().commit();
//        }catch (HibernateException hEx){
//            // Insert to database exception log
//            hEx.printStackTrace();
//        }finally {
//            if(session!=null)session.close();
//        }
//    }
}
