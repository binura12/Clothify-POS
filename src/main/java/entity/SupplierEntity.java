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
    private String supplierEmail;
    private String supplierCompany;
    private String supplierItem;
    private String category;

    public SupplierEntity (Long supplierId, String supplierName, String supplierEmail, String supplierCompany, String supplierItem, String category) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.supplierCompany = supplierCompany;
        this.supplierItem = supplierItem;
        this.category = category;
    }
}
