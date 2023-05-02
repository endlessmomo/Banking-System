package bank.view;

import bank.controller.command.ControllerMapperCommand;
import bank.controller.command.LoginControllerCommand;
import bank.util.ControllerMapperPrint;
import bank.util.LoginPrint;

import java.util.Scanner;

public class InputView {
    private final Scanner sc = new Scanner(System.in);

    public ControllerMapperCommand readControllerMapperCommand() {
        ControllerMapperPrint.getReadCommand();
        String command = sc.nextLine();
        return ControllerMapperCommand.from(command);
    }
}
