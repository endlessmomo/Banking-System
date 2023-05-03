package bank.util;

public class LoginPrint {
    public static final String GREEN = "\u001B[32m";
    public static final String PURPLE = "\u001B[35m";

    public static void getReadCommand(){
        System.out.println(GREEN + "\n기능을 선택하세요.");
        System.out.println("1. 회원 가입");
        System.out.println("2. 로그인");
        System.out.println("3. 로그아웃");
        System.out.println("4. 이전");
        System.out.print(PURPLE + "명령어를 입력하세요 : ");
    }
    public static void signUpStart(){
        System.out.println(GREEN + "\n회원가입을 위해 아래의 양식과 같이 작성해 주세요");
        System.out.println("이름, 아이디, 패스워드, 주민번호, 주소, 전화번호 순으로 작성을 하며, 주민번호, 전화번호 기입 시 \"-\"로 구분해 주세요");
        System.out.print(PURPLE + "정보를 기입해 주세요 : ");
    }

    public static void checkPw(){
        System.out.print(PURPLE + "확인을 위하여 패스워드를 한번 더 입력해 주세요 : ");
    }
}
