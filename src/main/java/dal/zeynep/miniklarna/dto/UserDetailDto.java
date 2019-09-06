package dal.zeynep.miniklarna.dto;

public class UserDetailDto {
    private Integer totalDebt;
    private String userEmail ;

    public UserDetailDto(String userEmail, Integer totalDebt) {
        this.userEmail = userEmail;
        this.totalDebt = totalDebt;
    }

    public Integer getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(Integer totalDebt) {
        this.totalDebt = totalDebt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
