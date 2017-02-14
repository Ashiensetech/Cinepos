package dao.viewDao;

import dao.BaseDao;
import entity.tableview.BoxOfficeSchedulingView;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 2/7/17.
 */
@Repository
public class BoxOfficeSchedulingViewDao extends BaseDao{

    public List<BoxOfficeSchedulingView> getByScheduleId(int scheduleId){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("select distinct boxSchView.film FROM BoxOfficeSchedulingView boxSchView " +
                    " inner join fetch boxSchView.film.filmTimes " +
                    " filmTime where filmTime.filmScheduleId = :scheduleId ")
                    .setParameter("scheduleId",scheduleId)
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<BoxOfficeSchedulingView>();

    }
}
