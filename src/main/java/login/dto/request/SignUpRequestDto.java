package login.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
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
}
