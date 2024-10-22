package dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Cashier {
    private Long id;
    private String fullname;
    private String email;
    private String password;
    private int age;
    private String company;

    public Cashier(String fullname, String email, String password, int age, String company) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.age = age;
        this.company = company;
    }
}
