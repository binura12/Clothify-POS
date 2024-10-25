package service.custom;

import dto.Product;
import dto.Supplier;
import service.SuperService;

import java.util.List;

public interface ProductService extends SuperService {
    List<Supplier> getSuppliersByCategory(String selectedCategory);
    boolean addProduct(Product product);
    List<Product> getAllProducts();
    List<Product> searchProduct(String text);
    boolean updateProduct(Product updatedProduct);
    boolean deleteProduct(Product deletedProduct);
}
