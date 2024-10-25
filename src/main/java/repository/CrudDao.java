package repository;

import entity.SupplierEntity;

public interface CrudDao <T> extends Superdao  {
    boolean save(T t);
    boolean updateSupplier(SupplierEntity supplierEntity);
    boolean deleteSupplier(SupplierEntity deletedSupplierEntity);
}
