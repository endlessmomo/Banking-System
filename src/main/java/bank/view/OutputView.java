package bank.view;

import bank.dao.dto.FindLoginIdResponseDto;
import bank.util.LoginPrint;

public class OutputView {
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    private static final String EXCEPTION_MESSAGE = RED + "\n[ERROR] : ";

    public void printException(String message) {
        System.out.println(EXCEPTION_MESSAGE + message);
    }

    public void successLogIn(){
        LoginPrint.successLogin();
    }
    public void successLogout(){
        LoginPrint.successLogout();
    }

    public void viewFindId(FindLoginIdResponseDto responseDto) {
        System.out.println(YELLOW + "\n찾으시는 고객님의 아이디는 " + responseDto.getLoginId() + " 입니다.");
    }
}
