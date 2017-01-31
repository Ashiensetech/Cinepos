package dao;

import entity.Category;
import entity.ConcessionProduct;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ConcessionProductDao extends BaseDao{

    public List<ConcessionProduct> getAll(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM ConcessionProduct").list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<ConcessionProduct>();

    }

    public void insert(ConcessionProduct concessionProduct){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(concessionProduct);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public void update(ConcessionProduct concessionProduct){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(concessionProduct);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public ConcessionProduct getById(int id){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (ConcessionProduct) session.createQuery("FROM ConcessionProduct where id = :id").setParameter("id", id).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;

    }

    public  List<ConcessionProduct> getByCategoryId(int categoryId){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (List<ConcessionProduct>) session.createQuery("FROM ConcessionProduct where category_id = :category_id").setParameter("category_id", categoryId).list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<ConcessionProduct>();
    }



}
