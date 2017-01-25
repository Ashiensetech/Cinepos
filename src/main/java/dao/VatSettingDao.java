package dao;

import entity.VatSetting;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarwar on 1/11/2017.
 */
@Repository
public class VatSettingDao extends BaseDao{
    public void create(VatSetting vatSetting){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(vatSetting);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public void update(VatSetting vatSetting){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(vatSetting);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public VatSetting getVatSetting(){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (VatSetting) session.createQuery("from VatSetting ORDER  BY id DESC").setMaxResults(1).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;
    }

    public VatSetting getById(int id){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (VatSetting) session.createQuery("FROM VatSetting where id = :id").setParameter("id", id).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;

    }


    public List<VatSetting> getAll(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM VatSetting")
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<VatSetting>();
    }


}
