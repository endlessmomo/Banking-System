package bank.controller;

import bank.controller.command.ControllerMapper;
import bank.controller.command.ControllerMapperCommand;
import bank.view.InputView;

import static bank.util.Retry.execute;

public class MapperController implements Controller {
    private final InputView inputView = new InputView();

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
