package login.dto.request;

import login.util.Crypt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignUpRequestDto {
    // 이름
    private String userName;
    // 아이디
    private String loginID;
    // 비밀번호
    private String password;
    // 주민등록번호
    private String RRN;
    // 주소
    private String address;
    // 핸드폰 번호
    private String phoneNumber;

    public SignUpRequestDto(String userName, String loginID, String password, String RRN, String address, String phoneNumber) {
        this.userName = userName;
        this.loginID = loginID;
        this.password = Crypt.encryptPassword(password);
        this.RRN = RRN;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
