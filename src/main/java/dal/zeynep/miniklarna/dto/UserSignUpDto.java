package dal.zeynep.miniklarna.dto;

public class UserSignUpDto {
    private String userEmail ;

    public UserSignUpDto(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
