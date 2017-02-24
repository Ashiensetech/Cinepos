package dao;

import entity.Film;
import entity.FilmImage;
import entity.ScreenSeat;
import entity.SeatType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by sunno on 1/11/17.
 */
@Repository
public class FilmImageDao extends BaseDao {

    public FilmImage insert(FilmImage filmImage){
        return (FilmImage) super.insert(filmImage);
    }
    public void update(FilmImage filmImage){
        super.update(filmImage);
    }

    public boolean delete(FilmImage filmImage){
        return super.delete(filmImage);
    }
    public void insertBatch(List<FilmImage> filmImages){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            for(int i=0;i<filmImages.size();i++){
                FilmImage screenSeat = filmImages.get(i);

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
