package dao;


import entity.Distributor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DistributorDao extends BaseDao{


    public void insert(Distributor distributor){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(distributor);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public void update(Distributor distributor){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(distributor);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public Distributor getById(int id){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (Distributor) session.createQuery("FROM Distributor where id = :id").setParameter("id", id).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;

    }

    public List<Distributor> getAll(){
        Session session=this.sessionFactory.openSession();

         try{
            return session.createQuery("FROM Distributor").list();

         }catch (HibernateException hEx){
             hEx.printStackTrace();
         }finally{
             if(session!=null)session.close();
         }
         return new ArrayList<Distributor>();
    }



}
