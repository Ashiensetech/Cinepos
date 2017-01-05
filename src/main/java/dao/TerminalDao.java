package dao;

import entity.Terminal;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarwar on 1/5/2017.
 */
@Repository
public class TerminalDao extends BaseDao {

     public void insert(Terminal terminal){
         Session session = null;
         try {
             session = this.sessionFactory.openSession();
             session.beginTransaction();
             session.save(terminal);
             session.getTransaction().commit();
         }catch (HibernateException hEx){
             // Insert to database exception log
             hEx.printStackTrace();
         }finally {
             if(session!=null)session.close();
         }

     }
     public void update(Terminal terminal){
         Session session = null;
         try {
             session = this.sessionFactory.openSession();
             session.beginTransaction();
             session.update(terminal);
             session.getTransaction().commit();
         }catch (HibernateException hEx){
             // update to database exception log
             hEx.printStackTrace();
         }finally {
             if(session!=null)session.close();
         }
     }

    public List<Terminal> getAll(){
        Session session=this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM Terminal").list();

        }catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<Terminal>();
    }
}
