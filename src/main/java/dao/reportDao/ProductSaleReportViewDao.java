package dao.reportDao;

import entity.Combo;
import entity.reportEntity.ProductSaleReportView;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarwar on 2/1/2017.
 */
@Repository
public class ProductSaleReportViewDao extends BaseDao {

    public List<ProductSaleReportView> getAll(){
        Session session=this.sessionFactory.openSession();

        try{
            return session.createQuery("FROM ProductSaleReportView").list();

        }catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();
    }
}
