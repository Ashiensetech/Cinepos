package dao;

import entity.ComboDetails;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by Sarwar on 1/23/2017.
 */

@Repository
public class ComboDetailDao extends BaseDao{


    public void insert(ComboDetails comboProduct){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(comboProduct);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public void update(ComboDetails comboProduct){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.update(comboProduct);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }

    public ComboDetails getById(int id){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (ComboDetails) session.createQuery("FROM ComboDetails where id = :id").setParameter("id", id).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;

    }


    public ComboDetails getByComboIdAndProductId(int comboId, int concessionProductId){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (ComboDetails) session.createQuery("FROM ComboDetails where comboId = :combo_id and concessionProductId = :concession_product_id")
                    .setParameter("combo_id", comboId)
                    .setParameter("concession_product_id", concessionProductId)
                    .uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;
    }

    public ComboDetails deleteComboProduct(ComboDetails comboProduct){
        Session session = null;
        System.out.println(comboProduct);

        try{
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.delete(comboProduct);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }

        return null;
    }

}
