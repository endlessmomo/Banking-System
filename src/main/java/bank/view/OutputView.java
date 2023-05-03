package bank.view;

import bank.util.LoginPrint;

public class OutputView {
    public static final String RED = "\u001B[31m";
    private static final String EXCEPTION_MESSAGE = RED + "\n[ERROR] : ";

    public void printException(String message) {
        System.out.println(EXCEPTION_MESSAGE + message);
    }

    public void successLogin(){
        LoginPrint.successLogin();
    }
}
