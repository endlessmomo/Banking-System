import bank.controller.CommandController;
import bank.controller.Controller;
import bank.controller.LoginController;
import bank.controller.command.ControllerMapper;
import bank.service.LoginService;

import static bank.controller.command.ControllerMapperCommand.LOGIN;

public class Application {
    public static void main(String[] args) {
        Controller controller = new CommandController(controllerMapper());
        controller.run();
    }

    private static ControllerMapper controllerMapper() {
        ControllerMapper mapper = new ControllerMapper();
        mapper.put(LOGIN, new LoginController(loginService()));
//        mapper.put(BANK, new BankController());
//        mapper.put(ACCOUNT, new AccountController());
//        mapper.put(CARD, new CardController());
        return mapper;
    }

    public static LoginService loginService(){
        return new LoginService();
    }

}
