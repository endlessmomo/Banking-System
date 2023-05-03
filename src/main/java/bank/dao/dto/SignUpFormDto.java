package bank.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class SignUpFormDto {
    // 이름
    private String name;
    // 아이디
    private String id;
    // 비밀번호
    private String pw;
    // 주민등록번호
    private String RRN;
    // 주소
    private String address;
    // 핸드폰 번호
    private String phoneNumber;
}
