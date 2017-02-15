package dao;

import entity.Film;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunno on 1/11/17.
 */
@Repository
public class FilmDao extends BaseDao {

    public Film insert(Film film){
        return (Film) super.insert(film);
    }
    public void update(Film film){
        super.update(film);
    }

    public boolean delete(Film film){
        return super.delete(film);
    }


    public Film getById(Integer id){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (Film) session.createQuery(" FROM Film fetch all properties where id = :id")
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

    public List<Film> getAll(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM Film order by id desc ")
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();

    }

    public List<Film> getAllActive(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM Film  where status = true order by id desc ")
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();

    }

    public List<Film> getAllActive(int limit,int offset){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM Film  where status = true order by id desc ")
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();

    }
}
