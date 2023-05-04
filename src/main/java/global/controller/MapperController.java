package global.controller;

import global.controller.command.ControllerMapper;
import global.controller.command.ControllerMapperCommand;
import login.view.LoginInputView;

import static global.util.Retry.execute;

public class MapperController implements Controller {
    private final LoginInputView inputView = new LoginInputView();

    private final ControllerMapper controllerMapper;

    public MapperController(ControllerMapper controllerMapper) {
        this.controllerMapper = controllerMapper;
    }

    @Override
    public void run() {
        ControllerMapperCommand command = execute(inputView::readControllerMapperCommand);
        while (command.isNotQuit()) {
            Controller controller = controllerMapper.get(command);
            execute(controller::run);
            command = execute(inputView::readControllerMapperCommand);
        }
    }
}
