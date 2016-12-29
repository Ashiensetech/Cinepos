package dao;


import entity.Category;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by omar on 8/1/16.
 */
@Repository
public class CategoryDao extends BaseDao {


    public void insert(Category category){
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally {
            if(session!=null)session.close();
        }
    }


    public Category getById(int id){
        Session session = null;

        try{
            session = this.sessionFactory.openSession();
            return (Category)session.createQuery("FROM Category where id = :id").setParameter("id", id).uniqueResult();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return null;
    }

    public List<Category> getAll(){
        Session session = this.sessionFactory.openSession();
        try{
            session = this.sessionFactory.openSession();
            return session.createQuery("select distinct category " +
                    "FROM Category category LEFT " +
                    "JOIN FETCH category.subcategory " +
                    "where category.isSubcategory = false ORDER BY category.sortedOrder")
                    .list();
        }catch (HibernateException hEx){
            // Insert to database exception log
            hEx.printStackTrace();
        }finally{
            if(session!=null)session.close();
        }
        return new ArrayList<Category>();

    }

}
