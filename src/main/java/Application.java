import global.controller.Controller;
import global.controller.MapperController;
import global.controller.command.ControllerMapper;
import login.controller.LoginController;
import login.service.LoginService;

import static global.controller.command.ControllerMapperCommand.LOGIN;

public class Application {
    public static void main(String[] args) {
        Controller controller = new MapperController(controllerMapper());
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

    // 싱글톤 패턴을 유지하기 위한 로직
    public static LoginService loginService() {
        return new LoginService();
    }
}
