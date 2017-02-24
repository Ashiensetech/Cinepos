package dao;

import entity.Film;
import entity.FilmTrailer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sunno on 1/11/17.
 */
@Repository
public class FilmTrailerDao extends BaseDao {

    public FilmTrailer insert(FilmTrailer FilmTrailer){
        return (FilmTrailer) super.insert(FilmTrailer);
    }
    public void update(FilmTrailer FilmTrailer){
        super.update(FilmTrailer);
    }

    public boolean delete(FilmTrailer FilmTrailer){
        return super.delete(FilmTrailer);
    }
    public void insertBatch(List<FilmTrailer> filmTrailers){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            for(int i=0;i<filmTrailers.size();i++){
                FilmTrailer filmTrailer = filmTrailers.get(i);

                session.saveOrUpdate(filmTrailer);
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
