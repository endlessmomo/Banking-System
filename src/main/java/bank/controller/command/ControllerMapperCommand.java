package bank.controller.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ControllerMapperCommand {
    LOGIN("1"),
    BANK("2"),
    ACCOUNT("3"),
    CARD("4"),
    QUIT("Q");

    private final String command;

    public static ControllerMapperCommand from(String command) {
        return Arrays.stream(values())
                .filter(i -> i.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령을 요청하셨습니다."));
    }

    public boolean isNotQuit(){
        return this != QUIT;
    }
}
