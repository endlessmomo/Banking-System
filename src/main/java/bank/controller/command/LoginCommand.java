package bank.controller.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum LoginCommand {
    SIGNUP("1"),
    LOGIN("2"),
    LOGOUT("3"),
    FINDID("4"),
    FINDPW("5"),
    PREVIOUS("P");

    private final String command;


    public static LoginCommand from(String command) {
        return Arrays.stream(values())
                .filter(i -> i.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
    }

    public boolean isNotPrevious() {
        return this != PREVIOUS;
    }
}
