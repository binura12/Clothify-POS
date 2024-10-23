package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "admin")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    private String email;
    private String password;
    private int age;
    private String company;

    public AdminEntity(Long id, String fullname, String email, String password, int age, String company) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.age = age;
        this.company = company;
    }
}
