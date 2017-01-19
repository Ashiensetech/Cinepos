package dao;

import entity.ConcessionPriceShift;
import entity.ConcessionProduct;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunno on 1/16/17.
 */
@Repository
public class ConcessionPriceShiftDao extends BaseDao{

    @Autowired
    ConcessionProductDao concessionProductDao;

    public ConcessionPriceShift insert(ConcessionPriceShift concessionPriceShift){
        return (ConcessionPriceShift) super.insert(concessionPriceShift);
    }
    public void update(ConcessionPriceShift concessionPriceShift){
        super.update(concessionPriceShift);
    }

    public boolean delete(ConcessionPriceShift concessionPriceShift){
        return super.delete(concessionPriceShift);
    }

    public List<ConcessionPriceShift> getAll(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("FROM ConcessionPriceShift")
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<ConcessionPriceShift>();
    }

    public ConcessionPriceShift getById(Integer id){
        Session session = null;
        try{
            session = this.sessionFactory.openSession();
            return (ConcessionPriceShift) session.createQuery("FROM ConcessionPriceShift where id = :id")
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .uniqueResult();
        }
        catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }

        return null;
    }



    public ConcessionPriceShift getByProductAndDates(Integer productId,Date startDate, Date endDate){
        Session session = null;
        ConcessionProduct concessionProduct = concessionProductDao.getById(productId);
        try{
            session = this.sessionFactory.openSession();
            return (ConcessionPriceShift) session.createQuery("FROM ConcessionPriceShift where concessionProduct =:concessionProduct and " +
                    "((startDate BETWEEN :startDate AND :endDate) or (endDate BETWEEN :startDate AND :endDate) " +
                    "or (:startDate BETWEEN startDate AND endDate) or (:endDate BETWEEN startDate AND endDate))")
                    .setParameter("concessionProduct", concessionProduct)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setMaxResults(1)
                    .uniqueResult();
        }
        catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }

        return null;
    }

}
