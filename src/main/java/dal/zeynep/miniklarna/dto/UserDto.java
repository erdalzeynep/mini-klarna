package dal.zeynep.miniklarna.dto;

public class UserDto {
    public static final Integer LIMIT = 200;
    private Integer totalDebt;

    public UserDto(Integer totalDebt) {
        this.totalDebt = totalDebt;
    }

    public static Integer getLIMIT() {
        return LIMIT;
    }

    public Integer getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(Integer totalDebt) {
        this.totalDebt = totalDebt;
    }
}
