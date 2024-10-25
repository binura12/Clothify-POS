package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "supplier")
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    private String supplierName;
    private String supplierCompany;
    private String supplierEmail;
    private String supplierItem;
    private String category;

    public SupplierEntity (Long supplierId, String supplierName, String supplierCompany, String supplierEmail, String supplierItem, String category) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierCompany = supplierCompany;
        this.supplierEmail = supplierEmail;
        this.supplierItem = supplierItem;
        this.category = category;
    }
}
