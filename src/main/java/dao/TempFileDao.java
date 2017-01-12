package dao;

import entity.TempFile;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omar on 8/2/16.
 */
@Repository
public class TempFileDao extends BaseDao {

    public void insert(TempFile tempFile){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(tempFile);
        session.getTransaction().commit();
        session.close();
    }

    public TempFile getByToken(int token){
        Session session = this.sessionFactory.openSession();

        try {
            return (TempFile)session.createQuery("FROM TempFile  WHERE token=:token")
                    .setParameter("token",token)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }
    public List<TempFile> getByToken(List<Integer> tokenList){
        Session session = this.sessionFactory.openSession();
        if(tokenList==null || tokenList.size()==0){
            return new ArrayList<>();
        }
        try {
            return session.createQuery("FROM TempFile  WHERE token in :tokenList ")
                    .setParameter("tokenList",tokenList)
                    .list();
        }finally {
            session.close();
        }
    }
    public void delete(TempFile tempFile){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(tempFile);
        session.getTransaction().commit();
        session.close();
    }
}
