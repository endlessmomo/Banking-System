package bank.util;

public class ControllerMapperPrint {
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";
    public static void getReadCommand(){
        System.out.println(GREEN + "\n기능을 선택하세요.");
        System.out.println("1. 로그인 업무");
        System.out.println("2. 은행 업무");
        System.out.println("3. 계좌 업무");
        System.out.println("4. 카드 업무");
        System.out.print(PURPLE + "명령어를 입력하세요 : ");
    }
}
