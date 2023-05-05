package login.view;

import global.util.ControllerMapperPrint;
import login.controller.command.LoginCommand;
import login.dto.request.FindLoginIdRequestDto;
import login.dto.request.FindLoginPasswordRequestDto;
import login.dto.request.LoginRequestDto;
import login.dto.request.SignUpRequestDto;
import global.controller.command.ControllerMapperCommand;
import login.util.LoginPrint;

import java.util.Scanner;

public class LoginInputView {
    private final Scanner sc = new Scanner(System.in);
    private final LoginInputValidator inputValidator = new LoginInputValidator();

    private static final String DELIMITER = ", ";

    public ControllerMapperCommand readControllerMapperCommand() {
        ControllerMapperPrint.getReadCommand();
        String command = sc.nextLine().toUpperCase();
        return ControllerMapperCommand.from(command);
    }

    public LoginCommand readLoginCommand() {
        LoginPrint.getReadCommand();
        String command = sc.nextLine().toUpperCase();
        return LoginCommand.from(command);
    }

    public SignUpRequestDto readSingUpInfo() {
        LoginPrint.signUp();

        String signUpForm = sc.nextLine();
        String[] info = signUpForm.split(DELIMITER);

        LoginPrint.getCheckPW();
        String checkPw = sc.nextLine();

        inputValidator.validateSignUp(info[2], checkPw, info[3], info[5]);

        return new SignUpRequestDto(info[0], info[1], info[2], info[3], info[4], info[5]);
    }

    public LoginRequestDto readLoginInfo() {
        LoginPrint.login();
        String userId = sc.nextLine();

        LoginPrint.getLoginPW();
        String userPw = sc.nextLine();

        return new LoginRequestDto(userId, userPw);
    }

    public FindLoginIdRequestDto readFindIdInfo() {
        LoginPrint.findID();
        String userName = sc.nextLine();
        inputValidator.validateName(userName);

        LoginPrint.inputRRN();
        String RRN = sc.nextLine();
        inputValidator.validateRRN(RRN);

        return new FindLoginIdRequestDto(userName, RRN);
    }

    public FindLoginPasswordRequestDto readFindPasswordInfo() {
        LoginPrint.findPassword();
        String userName = sc.nextLine();
        inputValidator.validateName(userName);

        LoginPrint.inputRRN();
        String RRN = sc.nextLine();
        inputValidator.validateRRN(RRN);

        LoginPrint.inputID();
        String loginID = sc.nextLine();
        inputValidator.validateName(userName);

        return new FindLoginPasswordRequestDto(userName, RRN, loginID);
    }
}
