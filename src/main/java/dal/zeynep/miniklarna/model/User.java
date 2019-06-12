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
    private Integer totalDebt;

    public User() {
        this.totalDebt = 0;
    }

    public User(String email) {
        this.email  = email;
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

}
