package repository.custom.impl;

import entity.ProductsEntity;
import entity.SupplierEntity;
import repository.custom.ProductDao;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductsEntity products) {
        return false;
    }

    @Override
    public boolean updateSupplier(SupplierEntity updatedSupplier) {
        return false;
    }

    @Override
    public boolean deleteSupplier(SupplierEntity deletedSupplierEntity) {
        return false;
    }
}
