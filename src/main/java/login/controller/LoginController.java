package login.controller;

import global.controller.Controller;
import global.util.Retry;
import login.controller.command.LoginCommand;
import login.dto.request.FindLoginIdRequestDto;
import login.dto.request.FindLoginPasswordRequestDto;
import login.dto.request.LoginRequestDto;
import login.dto.request.SignUpRequestDto;
import login.dto.response.FindLoginIdResponseDto;
import login.dto.response.FindLoginPasswordResponseDto;
import login.service.LoginService;
import login.view.LoginInputView;
import login.view.LoginOutputView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static global.util.Retry.execute;

public class LoginController implements Controller {
    private final LoginInputView inputView = new LoginInputView();
    private final LoginOutputView outputView = new LoginOutputView();
    private final Map<LoginCommand, Runnable> service = new HashMap<>();
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
        service.put(LoginCommand.SIGNUP, this::signUp);
        service.put(LoginCommand.LOGIN, this::login);
        service.put(LoginCommand.LOGOUT, this::logout);
        service.put(LoginCommand.FINDID, this::findID);
        service.put(LoginCommand.FINDPW, this::findPW);
    }

    @Override
    public void run() {
        LoginCommand command = Retry.execute(inputView::readLoginCommand);
        while (command.isNotPrevious()) {
            service.get(command).run();
            command = Retry.execute(inputView::readLoginCommand);
        }
    }

    public void signUp() {
        SignUpRequestDto requestDto = execute(inputView::readSingUpInfo);

        try {
            loginService.signUp(requestDto);
            outputView.successSignUp();
        } catch (Exception e) {
            outputView.printException(e.getMessage());
        }
    }

    public void login() {
        LoginRequestDto requestDto = execute(inputView::readLoginInfo);

        try {
            loginService.login(requestDto);
            outputView.successLogin();
        } catch (Exception e) {
            outputView.printException(e.getMessage());
        }
    }

    public void logout() {
        try {
            loginService.logout();
            outputView.successLogout();
        } catch (Exception e) {
            outputView.printException(e.getMessage());
        }
    }

    public void findID() {
        FindLoginIdRequestDto requestDto = Retry.execute(inputView::readFindIdInfo);

        try {
            FindLoginIdResponseDto responseDto = loginService.findLoginId(requestDto);
            outputView.viewFindID(responseDto);
        } catch (Exception e) {
            outputView.printException(e.getMessage());
        }
    }

    public void findPW() {
        FindLoginPasswordRequestDto requestDto = Retry.execute(inputView::readFindPasswordInfo);

        try {
            FindLoginPasswordResponseDto responseDto = loginService.findLoginPassword(requestDto);
            outputView.viewFindPW(responseDto);
        } catch (SQLException e) {
            outputView.printException(e.getMessage());
        }
    }
}
