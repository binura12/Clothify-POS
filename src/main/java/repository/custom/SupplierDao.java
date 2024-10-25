package repository.custom;

import entity.SupplierEntity;
import repository.CrudDao;

import java.util.List;

public interface SupplierDao extends CrudDao <SupplierEntity> {
    List<SupplierEntity> loadAllSuppliers();
    List<SupplierEntity> searchSupplier(String supplierName);
    List<SupplierEntity> getSuppliersByCategory(String selectedCategory);
}
