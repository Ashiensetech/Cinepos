package dao;

import entity.Category;
import entity.ConcessionProduct;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
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


    public List<ConcessionProduct> getAllToPriceShift(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM ConcessionProduct where isPriceShift = :isPriceShift AND status=:status").setParameter("isPriceShift",1).setParameter("status",1).list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<ConcessionProduct>();

    }

    public List<ConcessionProduct> getAllToIsCombo(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM ConcessionProduct where is_combo = :is_combo AND status=:status").setParameter("is_combo",1).setParameter("status",1).list();
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

    public  List<ConcessionProduct> getProductQuantity(){

        Session session = null;
        session = this.sessionFactory.openSession();

        Criteria cr = session.createCriteria(ConcessionProduct.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("unit"), "unit"))
                .setResultTransformer(Transformers.aliasToBean(ConcessionProduct.class));

        List<ConcessionProduct> list = cr.list();
        return list;
    }



}
