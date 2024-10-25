package service.custom.impl;

import dto.Supplier;
import entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import util.DaoType;

import java.util.List;
import java.util.stream.Collectors;

public class SupplierServiceImpl implements SupplierService {
    @Override
    public boolean addSupplier(Supplier supplier) {
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);

        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.supplier);
        supplierDao.save(supplierEntity);

        return true;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.supplier);
        List<SupplierEntity> supplierEntityList = supplierDao.loadAllSuppliers();
        ModelMapper modelMapper = new ModelMapper();
        return supplierEntityList.stream()
                .map(supplierEntity -> modelMapper.map(supplierEntity, Supplier.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Supplier> searchSupplier(String supplierName) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.supplier);
        List<SupplierEntity> supplierEntityList = supplierDao.searchSupplier(supplierName);
        ModelMapper modelMapper = new ModelMapper();
        return supplierEntityList.stream()
               .map(supplierEntity -> modelMapper.map(supplierEntity, Supplier.class))
               .collect(Collectors.toList());
    }

    @Override
    public boolean updateSupplier(Supplier updatedSupplier) {
        SupplierEntity updatedSupplierEntity = new ModelMapper().map(updatedSupplier, SupplierEntity.class);

        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.supplier);
        supplierDao.updateSupplier(updatedSupplierEntity);

        return true;
    }

    @Override
    public boolean deleteSupplier(Supplier deletedSupplier) {
        SupplierEntity deletedSupplierEntity = new ModelMapper().map(deletedSupplier, SupplierEntity.class);

        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.supplier);
        supplierDao.deleteSupplier(deletedSupplierEntity);

        return true;
    }
}
