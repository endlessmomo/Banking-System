package bank.view;

import bank.controller.command.ControllerMapperCommand;
import bank.controller.command.LoginCommand;
import bank.dao.dto.LoginDto;
import bank.dao.dto.SignUpFormDto;
import bank.util.ControllerMapperPrint;
import bank.util.Crypt;
import bank.util.LoginPrint;

import java.util.Scanner;

public class InputView {
    private final Scanner sc = new Scanner(System.in);
    private final InputValidator inputValidator = new InputValidator();

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

    public SignUpFormDto readSingUpFormInfo() {
        LoginPrint.signUpStart();

        String signUpForm = sc.nextLine();
        String[] info = signUpForm.split(DELIMITER);

        LoginPrint.checkPw();
        String checkPw = sc.nextLine();

        inputValidator.validateSignUp(info[2], checkPw, info[3], info[5]);

        return new SignUpFormDto(info[0], info[1], info[2], info[3], info[4], info[5]);
    }
}
