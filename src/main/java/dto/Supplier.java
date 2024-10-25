package dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Supplier {
    private Long supplierId;
    private String supplierName;
    private String supplierCompany;
    private String supplierEmail;
    private String supplierItem;
    private String category;

    public Supplier (String supplierName, String supplierCompany, String supplierEmail, String supplierItem, String category) {
        this.supplierName = supplierName;
        this.supplierCompany = supplierCompany;
        this.supplierEmail = supplierEmail;
        this.supplierItem = supplierItem;
        this.category = category;
    }
}
