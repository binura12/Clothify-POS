package dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;

@Data
@NoArgsConstructor
@ToString
public class Product {
    private Long productId;
    private String productName;
    private String size;
    private Double price;
    private Integer quantity;
    private String category;
    private String supplierId;

    public Product (String productName, String size, Double price, Integer quantity, String category, String supplierId) {
        this.productName = productName;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.supplierId = supplierId;
    }
}
