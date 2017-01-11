package dao;

import entity.Circuit;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarwar on 1/10/2017.
 */

@Repository
public class CircuitDao extends BaseDao{

    public int insert(Circuit circuit){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            int circuitId=(Integer) session.save(circuit);
            session.getTransaction().commit();
            return  circuitId;
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
        return 0;
    }

    public void update(Circuit circuit){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(circuit);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public List<Circuit> getAll(){
        Session session=this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM Circuit").list();

        }catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();
    }

    public Circuit getCircuit(){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (Circuit) session.createQuery("from Circuit ORDER  BY id DESC").setMaxResults(1).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;
    }

    public Circuit getById(int id){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (Circuit) session.createQuery("FROM Circuit where id = :id").setParameter("id", id).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;

    }



}
