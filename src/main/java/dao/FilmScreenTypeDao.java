package dao;

import entity.Film;
import entity.FilmScreenType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 1/16/17.
 */
@Repository
public class FilmScreenTypeDao extends BaseDao {

    public FilmScreenType getById(Integer id){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (FilmScreenType) session.createQuery("FROM FilmScreenType where id = :id")
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
    public List<FilmScreenType> getByFilmId(Integer filmId){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM FilmScreenType where filmId = :filmId")
                    .setParameter("filmId", filmId)
                    .list();
        }
        catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }

        return new ArrayList<>();
    }
}
