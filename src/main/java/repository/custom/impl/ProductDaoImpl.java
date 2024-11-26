package repository.custom.impl;

import entity.ProductsEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.ProductDao;
import util.HibernateUtil;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductsEntity products) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(products);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(ProductsEntity productsEntity) {
        Long productId = productsEntity.getProductId();
        String productName = productsEntity.getProductName();
        String size = productsEntity.getSize();
        Double price = productsEntity.getPrice();
        Integer quantity = productsEntity.getQuantity();
        String category = productsEntity.getCategory();
        String supplierId = productsEntity.getSupplierId();

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "UPDATE ProductsEntity SET productId = :productId, productName = :productName, size = :size, price = :price, quantity = :quantity, category = :category, supplierId = :supplierId where productId = :productId";
        Query query = session.createQuery(hql);
        query.setParameter("productId", productId);
        query.setParameter("productName", productName);
        query.setParameter("size", size);
        query.setParameter("price", price);
        query.setParameter("quantity", quantity);
        query.setParameter("category", category);
        query.setParameter("supplierId", supplierId);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result > 0;
    }

    @Override
    public boolean delete(ProductsEntity productsEntity) {
        Long id = productsEntity.getProductId();
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "delete from ProductsEntity where productId = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result > 0;
    }

    @Override
    public List<ProductsEntity> loadAllSuppliers() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<ProductsEntity> productsList = session.createQuery("from ProductsEntity").list();
        session.getTransaction().commit();
        session.close();
        return productsList;
    }

    @Override
    public List<ProductsEntity> searchProduct(String productName) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from ProductsEntity where productName like :productName";
        Query query = session.createQuery(hql);
        query.setParameter("productName", productName);
        List<ProductsEntity> productsEntityList = query.list();
        session.close();
        return productsEntityList;
    }
}
