package dao;

import entity.ComboDetails;
import entity.FilmImage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    public boolean insertOrUpdateBatch(Set<ComboDetails> comboDetailsSet){
        Session session = null;

        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            Iterator<ComboDetails> comboDetailsItr =comboDetailsSet.iterator();
            for(int i=0;comboDetailsItr.hasNext();i++){

                ComboDetails comboDetails = comboDetailsItr.next();
                System.out.println(comboDetails);
                session.saveOrUpdate(comboDetails);
                if(i%15==0){
                    session.flush();
                    session.clear();
                }
            }

            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
            return false;
        }finally {
            if(session!=null)session.close();
        }
        return true;
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
            return (ComboDetails) session.createQuery("FROM ComboDetails where comboId = :combo_id and concessionProduct.id = :concession_product_id")
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
    public ComboDetails getSeatTypeByComboIdAndSeatTypeId(int comboId, int seatTypeId){

        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (ComboDetails) session.createQuery("FROM ComboDetails where comboId = :combo_id and seatType.id = :seatTypeId")
                    .setParameter("combo_id", comboId)
                    .setParameter("seatTypeId", seatTypeId)
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
