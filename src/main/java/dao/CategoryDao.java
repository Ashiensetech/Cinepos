package dao;


import entity.Category;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by omar on 8/1/16.
 */
@Repository
public class CategoryDao extends BaseDao {


    public void insert(Category category){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(category);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(int categoryId){
        Category category = this.getById(categoryId);
        if(category == null) return;

        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(category);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(List<Category> categoryList){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        for(Category category : categoryList){
            for(Category subCategory : category.getSubcategory()){
                session.delete(subCategory);
            }
            session.delete(category);
        }
        session.getTransaction().commit();
        session.close();
    }
    public void delete(Category category){
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.delete(category);
        session.getTransaction().commit();
        session.close();
    }
    public Category getById(int id){
        Session session = this.sessionFactory.openSession();
        String hql = "FROM Category where id = :id";
        Query query =  session.createQuery(hql);
        query.setParameter("id", id);
        try{
            return (Category)query.uniqueResult();
        }finally{
            session.close();
        }
    }
    public List<Category> getAllParentBySubcategoryId(int subcategoryId){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("select distinct category FROM Category category " +
                    " join category.subcategory subcategory " +
                    " where subcategory.id = :subcategoryId")
                    .setParameter("subcategoryId",subcategoryId)
                    .list();
        }finally{
            session.close();
        }
    }
    public List<Category> getAllCategoryParent(){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("select distinct category FROM Category category where category.isSubcategory = false")
                    .list();
        }finally{
            session.close();
        }
    }
    public List<Category> getAll(){
        Session session = this.sessionFactory.openSession();
        try{
            return session.createQuery("select distinct category FROM Category category LEFT JOIN FETCH category.subcategory where category.isSubcategory = false ORDER BY category.sortedOrder")
                    .list();
        }finally{
            session.close();
        }
    }
    public List<Category> getByParentId(int parentId){
        Session session = this.sessionFactory.openSession();
        try {
            return session.createQuery("select distinct category FROM Category category INNER JOIN FETCH category.subcategory where category.id =:parentId")
                    .setParameter("parentId", parentId).list();
        }finally{
            session.close();
        }
    }

    public int maxSortOrder(){
        Session session = this.sessionFactory.openSession();
        try{
            return (Integer) session.createQuery("SELECT MAX(category.sortedOrder) FROM Category category").uniqueResult();
        }finally {
            session.close();
        }
    }
    public Category getParentCategory(int subCategoryId){
        Session session = this.sessionFactory.openSession();
        try{
            return (Category)session.createQuery("select distinct category FROM Category category INNER JOIN FETCH category.subcategory sc  where sc.id =:subCategoryId")
                    .setParameter("subCategoryId", subCategoryId)
                    .setMaxResults(1)
                    .uniqueResult();
        }finally {
            session.close();
        }
    }
    public void categoryCountIncrease(int categoryId){
        Session session = this.sessionFactory.openSession();
        try {
            Category category = (Category)session.createQuery("FROM Category where id = :categoryId")
                                                    .setParameter("categoryId", categoryId)
                                                    .setMaxResults(1)
                                                    .uniqueResult();
            int count = category.getProductCount();
            category.setProductCount(count + 1);
            session.beginTransaction();
            session.update(category);
            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }
    public void categoryCountDecrease(int categoryId){
        Session session = this.sessionFactory.openSession();
        try {
            Category category = (Category)session.createQuery("FROM Category where id = :categoryId")
                                                    .setParameter("categoryId", categoryId)
                                                    .setMaxResults(1)
                                                    .uniqueResult();
            int count = category.getProductCount();
            category.setProductCount(count - 1);
            session.beginTransaction();
            session.update(category);
            session.getTransaction().commit();
        }finally {
            session.close();
        }
    }

}
