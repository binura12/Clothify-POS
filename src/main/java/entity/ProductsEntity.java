package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class ProductsEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private int size;
    private double price;
    private int quantity;
    private Image productImage;
    private String category;
    private Long supplierId;

    public ProductsEntity (Long productId, String productName, int size, double price, int quantity, Image productImage, String category, Long supplierId) {
        this.productId = productId;
        this.productName = productName;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.productImage = productImage;
        this.category = category;
        this.supplierId = supplierId;
    }
}
