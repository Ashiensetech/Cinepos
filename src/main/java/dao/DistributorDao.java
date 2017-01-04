package dao;


import entity.Distributor;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DistributorDao extends BaseDao{

    public List<Distributor> getAll(){
        Session session=this.sessionFactory.openSession();

         try{
            return session.createQuery("FROM Distributor").list();

         }catch (HibernateException hEx){
             hEx.printStackTrace();
         }finally{
             if(session!=null)session.close();
         }
         return new ArrayList<Distributor>();
    }



}
