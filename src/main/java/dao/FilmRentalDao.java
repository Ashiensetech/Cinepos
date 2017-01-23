package dao;

import entity.Film;
import entity.FilmRental;
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
public class FilmRentalDao extends BaseDao{

    @Autowired
    FilmDao filmDao;



    public FilmRental insert(FilmRental filmRental){
        return (FilmRental) super.insert(filmRental);
    }
    public void update(FilmRental filmRental){
        super.update(filmRental);
    }

    public boolean delete(FilmRental filmRental){
        return super.delete(filmRental);
    }

    public List<FilmRental> getAll(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM FilmRental")
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<FilmRental>();
    }

    public FilmRental getById(Integer id){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (FilmRental) session.createQuery("FROM FilmRental where id = :id")
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


    public FilmRental getByFilmAndDates(Integer filmId,Date startDate, Date endDate){
        Session session = null;
        Film film = filmDao.getById(filmId);
        try{
            session = this.sessionFactory.openSession();
            return (FilmRental) session.createQuery("FROM FilmRental where film =:film and " +
                    "((startDate BETWEEN :startDate AND :endDate) or (endDate BETWEEN :startDate AND :endDate) " +
                    "or (:startDate BETWEEN startDate AND endDate) or (:endDate BETWEEN startDate AND endDate))")
                    .setParameter("film", film)
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
