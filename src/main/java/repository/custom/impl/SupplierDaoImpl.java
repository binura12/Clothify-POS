package repository.custom.impl;

import entity.SupplierEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.SupplierDao;
import util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity supplier) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(supplier);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateSupplier(SupplierEntity updatedSupplier) {
        Long supplierId = updatedSupplier.getSupplierId();
        String newName = updatedSupplier.getSupplierName();
        String newEmail = updatedSupplier.getSupplierEmail();
        String newCategory = updatedSupplier.getCategory();
        String newCompany = updatedSupplier.getSupplierCompany();
        String newItemName = updatedSupplier.getSupplierItem();

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "update SupplierEntity set supplierId = :supplierId, supplierName = :newName, supplierEmail = :newEmail, category = :newCategory, supplierCompany = :newCompany, supplierItem = :newItemName where supplierId = :supplierId";
        Query query = session.createQuery(hql);
        query.setParameter("supplierId", supplierId);
        query.setParameter("newName", newName);
        query.setParameter("newEmail", newEmail);
        query.setParameter("newCategory", newCategory);
        query.setParameter("newCompany", newCompany);
        query.setParameter("newItemName", newItemName);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result > 0;
    }

    @Override
    public boolean deleteSupplier(SupplierEntity deletedSupplierEntity) {
        Long id = deletedSupplierEntity.getSupplierId();
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        String hql = "delete from SupplierEntity where supplierId = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return result > 0;
    }

    @Override
    public List<SupplierEntity> loadAllSuppliers() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        List<SupplierEntity> supplierList = session.createQuery("from SupplierEntity").list();
        session.getTransaction().commit();
        session.close();
        return supplierList;
    }

    @Override
    public List<SupplierEntity> searchSupplier(String supplierName) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from SupplierEntity where supplierName like :supplierName";
        Query query = session.createQuery(hql);
        query.setParameter("supplierName", supplierName);
        List<SupplierEntity> supplierList = query.list();
        session.close();
        return supplierList;
    }
}
