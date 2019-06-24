package dal.zeynep.miniklarna.dto;

public class UserDto {
    private Integer totalDebt;

    public UserDto(Integer totalDebt) {
        this.totalDebt = totalDebt;
    }

    public Integer getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(Integer totalDebt) {
        this.totalDebt = totalDebt;
    }
}
