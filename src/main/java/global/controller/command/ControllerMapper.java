package global.controller.command;

import global.controller.Controller;

import java.util.EnumMap;
import java.util.Map;

public class ControllerMapper {
    private final Map<ControllerMapperCommand, Controller> mapper = new EnumMap<>(ControllerMapperCommand.class);

    public void put(ControllerMapperCommand command, Controller controller) {
        mapper.put(command, controller);
    }

    public Controller get(ControllerMapperCommand command) {
        return mapper.get(command);
    }
}
