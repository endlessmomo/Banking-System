package bank.controller;

import bank.controller.command.LoginCommand;
import bank.dao.dto.LoginDto;
import bank.dao.dto.SignUpFormDto;
import bank.service.LoginService;
import bank.view.InputView;
import bank.view.OutputView;

import java.util.HashMap;
import java.util.Map;

import static bank.controller.command.LoginCommand.*;
import static bank.util.Retry.execute;

public class LoginController implements Controller {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final Map<LoginCommand, Runnable> service = new HashMap<>();
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
        service.put(SIGNUP, this::signUp);
        service.put(LOGIN, this::logIn);
        service.put(LOGOUT, this::logOut);
    }

    @Override
    public void run() {
        LoginCommand command = execute(inputView::readLoginCommand);
        while (command.isNotPrevious()) {
            service.get(command).run();
            command = execute(inputView::readLoginCommand);
        }
    }

    public void signUp() {
        SignUpFormDto dto = execute(inputView::readSingUpFormInfo);

        try{
            loginService.signUp(dto);
        } catch (Exception e){
            outputView.printException(e.getMessage());
        }
    }

    public void logIn() {
        LoginDto dto = execute(inputView::readLogInInfo);

        try{
            loginService.logIn(dto);
            outputView.successLogin();
        } catch (Exception e){
            outputView.printException(e.getMessage());
        }
    }

    public void logOut() {

    }
}
