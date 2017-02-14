package dao.reportDao;

import dao.BaseDao;
import entity.tableview.report.ProductSummaryReportView;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ProductSummaryReportViewDao extends BaseDao {

    public List<ProductSummaryReportView> getAll(){
        Session session=this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM ProductSummaryReportView").list();

        }catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();
    }
    public List<ProductSummaryReportView> getByDateRange(Timestamp startDate,Timestamp endDate){
        Session session=this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM ProductSummaryReportView " +
                    " where createdAt between :startDate and :endDate ")
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .list();

        }catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();
    }
    public List<ProductSummaryReportView> getByDate(Date startDate){
        Session session=this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM ProductSummaryReportView " +
                    " where createdAt = :startDate  ")
                    .setParameter("startDate", startDate)
                    .list();

        }catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();
    }
}
