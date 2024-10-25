package service.custom;

import dto.Supplier;
import service.SuperService;

import java.util.List;

public interface SupplierService extends SuperService {
    boolean addSupplier(Supplier supplier);
    List<Supplier> getAllSuppliers();
    List<Supplier> searchSupplier(String supplierName);
    boolean updateSupplier(Supplier updatedSupplier);
    boolean deleteSupplier(Supplier deletedSupplier);
}
