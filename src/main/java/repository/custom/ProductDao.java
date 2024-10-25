package repository.custom;

import entity.ProductsEntity;
import repository.CrudDao;

import java.util.List;

public interface ProductDao extends CrudDao <ProductsEntity> {
    List<ProductsEntity> loadAllSuppliers();
    List<ProductsEntity> searchProduct(String productName);
}
