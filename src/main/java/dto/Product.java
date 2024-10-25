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
    private int size;
    private double price;
    private int quantity;
    private Image productImage;
    private String category;
    private Long supplierId;

    public Product (String productName, int size, double price, int quantity, Image productImage, String category, Long supplierId) {
        this.productName = productName;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.productImage = productImage;
        this.category = category;
        this.supplierId = supplierId;
    }
}
