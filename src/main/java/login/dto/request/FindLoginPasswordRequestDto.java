package login.dto.request;

import login.util.Crypt;
import login.util.RandomPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindLoginPasswordRequestDto {
    private String userName;
    private String RRN;
    private String loginID;
    private String password;

    public FindLoginPasswordRequestDto(String userName, String RRN, String loginID) {
        this.userName = userName;
        this.RRN = RRN;
        this.loginID = loginID;
        this.password = RandomPassword.randomPassword().toLowerCase();
    }
}
