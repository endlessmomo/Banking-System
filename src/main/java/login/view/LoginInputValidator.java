package login.view;

import java.util.regex.Pattern;

public class LoginInputValidator {
    public static final String rrnPattern = "^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$";
    public static final String phoneNumberPattern = "\\d{3}-\\d{4}-\\d{4}";
    public static final String namePatter = "^[가-힣]*$";

    public void validateSignUp(String plainPw, String checkPw, String RRN, String phoneNumber) {
        validatePassword(plainPw, checkPw);
        validateRRN(RRN);
        validatePhoneNumber(phoneNumber);
    }

    public void validatePassword(String plainPw, String checkPw) {
        if (!plainPw.equals(checkPw)) {
            throw new IllegalArgumentException("[UNMATCH]\n패스워드가 일치하지 않습니다. \n 회원가입을 다시 시도해 주십시오.");
        }
    }

    public void validateRRN(String RRN) {
        if (!Pattern.matches(rrnPattern, RRN)) {
            throw new IllegalArgumentException("[WRONG] \n잘못된 주민등록 번호 양식입니다. \n 확인 후 회원가입을 다시 시도해 주십시오");
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (!Pattern.matches(phoneNumberPattern, phoneNumber)) {
            throw new IllegalArgumentException("[WRONG] \n잘못된 핸드폰 번호 양식입니다. \n 확인 후 회원가입을 다시 시도해 주십시오.");
        }
    }

    public void validateName(String name) {
        if (!Pattern.matches(namePatter, name)) {
            throw new IllegalArgumentException("[WRONG] 한글만 입력 가능합니다.");
        }
    }
}
