package dao;

import entity.FilmSchedule;
import entity.FilmTime;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.Set;

/**
 * Created by mi on 1/25/17.
 */
@Repository
public class FilmTimeDao extends BaseDao {
    public boolean insert(FilmTime filmTime){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(filmTime);
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
    public boolean update(FilmTime filmTime){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(filmTime);
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
    public boolean delete(FilmTime filmTime){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.delete(filmTime);
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
    public void insertOrUpdate(Set<FilmTime> filmTimeList){
        Session session = null;

        if(filmTimeList.size()==0){
            return;
        }
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            int i=0;
            for(FilmTime filmTime:filmTimeList){
                session.saveOrUpdate(filmTime);
                if(i%15==0){
                    session.flush();
                    session.clear();
                }
                i++;
            }

            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }
    public FilmTime getById(int id){
        Session session=this.sessionFactory.openSession();

        try{
            return (FilmTime)session.createQuery("FROM FilmTime filmSchedule" +
                    " where id =:id")
                    .setParameter("id",id)
                    .uniqueResult();

        }catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;
    }

    public List<FilmTime> getByInBetweenTime(int id,int filmScheduleId,Time startTime,Time endTime){


        Session session=this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM FilmTime filmTime " +
                    " where :startTime between filmTime.startTime and filmTime.endTime " +
                    " or :endTime between filmTime.startTime and filmTime.endTime " +
                    " and filmTime.filmScheduleId = :filmScheduleId" +
                    " and filmTime.id !=:id")
                    .setParameter("id",id)
                    .setParameter("filmScheduleId", filmScheduleId)
                    .setParameter("startTime",endTime )
                    .setParameter("endTime",startTime)
                    .list();

        }catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;
    }
}
