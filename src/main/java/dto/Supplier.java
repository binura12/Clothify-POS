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
    private String supplierEmail;
    private String supplierCompany;
    private String supplierItem;
    private String category;

    public Supplier (String supplierName, String supplierEmail, String supplierCompany, String supplierItem, String category) {
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.supplierCompany = supplierCompany;
        this.supplierItem = supplierItem;
        this.category = category;
    }
}
