package dal.zeynep.miniklarna.model;

import javax.persistence.*;

@Entity
@Table
public class User {
    public static final Integer LIMIT = 200;
    @Id
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Integer totalDebt;

    public User() {
        this.totalDebt = 0;
    }

    public User(String email, String password) {
        this.email  = email;
        this.password = password ;
        this.totalDebt = 0;
    }

    public Integer getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(Integer totalDebt) {
        this.totalDebt = totalDebt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
