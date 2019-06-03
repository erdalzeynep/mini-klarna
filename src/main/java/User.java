import java.util.UUID;

public class User {
    public static final Integer LIMIT = 200;
    private String id;
    private Integer totalDebt;

    public User() {
        this.id = UUID.randomUUID().toString();
        this.totalDebt = 0;
    }

    public String getId() {
        return id;
    }

    public Integer getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(Integer totalDebt) {
        this.totalDebt = totalDebt;
    }
}
