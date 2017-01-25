package dao;

import entity.Circuit;
import entity.FilmSchedule;
import entity.Screen;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.Parameter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 1/25/17.
 */
@Repository
public class FilmScheduleDao   extends BaseDao {
    public boolean insert(FilmSchedule filmSchedule){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(filmSchedule);
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

    public List<FilmSchedule> getByDateRange(Integer screenId,Date sDate, Date eData){
        Session session=this.sessionFactory.openSession();

        try{
            return session.createQuery("select  distinct filmSchedule FROM FilmSchedule filmSchedule left" +
                    " join fetch filmSchedule.filmTimes  filmTimes " +
                    " where filmSchedule.screen.id =:screenId " +
                    " and filmSchedule.date >= :sDate " +
                    " and filmSchedule.date <= :eData " +
                    " and filmSchedule.status = true " +
                    " order by filmSchedule.date asc ")
                    .setParameter("screenId", screenId)
                    .setParameter("sDate", sDate)
                    .setParameter("eData", eData)
                    .list();

        }catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();
    }

}
