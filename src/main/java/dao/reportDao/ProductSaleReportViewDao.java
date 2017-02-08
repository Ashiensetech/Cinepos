package dao.reportDao;

import dao.BaseDao;
import entity.entityReport.ProductSummaryReportView;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ProductSaleReportViewDao extends BaseDao {

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
}
