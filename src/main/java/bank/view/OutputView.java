package bank.view;

public class OutputView {
    private static final String EXCEPTION_MESSAGE = "[ERROR] : ";

    public void printException(String message) {
        System.out.println(EXCEPTION_MESSAGE + message);
    }
}
