package dao.reportDao;

import dao.BaseDao;
import entity.tableview.report.ConcessionSalesByOperatorView;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ConcessionSalesByOperatorViewDao extends BaseDao{

    public List<ConcessionSalesByOperatorView> getAll(){
        Session session=this.sessionFactory.openSession();
        try{
            return session.createQuery("FROM ConcessionSalesByOperatorView").list();
        }catch (HibernateException hEx){
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<>();
    }
}
