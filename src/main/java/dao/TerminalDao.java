package dao;

import entity.Terminal;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class TerminalDao extends BaseDao {

     public int insert(Terminal terminal){
         Session session = null;
         try {
             session = this.sessionFactory.openSession();
             session.beginTransaction();
             int id=(Integer) session.save(terminal);
             session.getTransaction().commit();
             return id;
         }catch (HibernateException hEx){
             // Insert to database exception log
             hEx.printStackTrace();
         }finally {
             if(session!=null)session.close();
         }

         return 0;

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

    public Terminal getById(int id){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (Terminal) session.createQuery("FROM Terminal where id = :id").setParameter("id", id).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;

    }
    public Terminal getByTerminalCode(String terminal_code){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (Terminal) session.createQuery("FROM Terminal where terminal_code = :terminal_code").setParameter("terminal_code", terminal_code).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;

    }

    public Terminal getLastId(){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (Terminal) session.createQuery("from Terminal order by id DESC").setMaxResults(1).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;

    }


}
