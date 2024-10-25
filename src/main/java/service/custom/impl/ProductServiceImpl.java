package service.custom.impl;

import dto.Product;
import dto.Supplier;
import entity.ProductsEntity;
import entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductDao;
import repository.custom.SupplierDao;
import service.custom.ProductService;
import util.DaoType;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Supplier> getSuppliersByCategory(String selectedCategory) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.supplier);
        List<SupplierEntity> supplierEntities = supplierDao.getSuppliersByCategory(selectedCategory);
        ModelMapper modelMapper = new ModelMapper();
        return supplierEntities.stream()
                .map(supplierEntity -> modelMapper.map(supplierEntity, Supplier.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addProduct(Product product) {
        ProductsEntity productsEntity = new ModelMapper().map(product, ProductsEntity.class);

        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.product);
        productDao.save(productsEntity);

        return true;
    }

    @Override
    public List<Product> getAllProducts() {
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.product);
        List<ProductsEntity> productsEntityList = productDao.loadAllSuppliers();
        ModelMapper modelMapper = new ModelMapper();
        return productsEntityList.stream()
                .map(productsEntity -> modelMapper.map(productsEntity, Product.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> searchProduct(String productName) {
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.product);
        List<ProductsEntity> productsEntityList = productDao.searchProduct(productName);
        ModelMapper modelMapper = new ModelMapper();
        return productsEntityList.stream()
                .map(productsEntity -> modelMapper.map(productsEntity, Product.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateProduct(Product updatedProduct) {
        ProductsEntity updatedProductEntity = new ModelMapper().map(updatedProduct, ProductsEntity.class);

        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.product);
        productDao.update(updatedProductEntity);

        return true;
    }

    @Override
    public boolean deleteProduct(Product deletedProduct) {
        ProductsEntity deletedProductEntity = new ModelMapper().map(deletedProduct, ProductsEntity.class);

        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.product);
        productDao.delete(deletedProductEntity);

        return true;
    }
}
