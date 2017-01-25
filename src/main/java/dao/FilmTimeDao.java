package dao;

import entity.FilmTime;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

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
    public void insertOrUpdate(Set<FilmTime> filmTimeList){
        Session session = null;


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
}
