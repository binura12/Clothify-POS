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
    private String size;
    private Double price;
    private Integer quantity;
    private String category;
    private String supplierId;

    public ProductsEntity (Long productId, String productName, String size, Double price, Integer quantity, String category, String supplierId) {
        this.productId = productId;
        this.productName = productName;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.supplierId = supplierId;
    }
}
