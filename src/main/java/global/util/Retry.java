package global.util;

import java.util.function.Function;
import java.util.function.Supplier;

public class Retry {
    private static final int REPEAT_COUNT = 3;
    private static final String REPEAT_FAIL_MESSAGE = "기능을 수행할 수 없습니다.";

    public static <T> T execute(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return execute(supplier);
        }
    }

    public static void execute(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException e) {
            execute(runnable);
        }
    }

    public static <T, R> R execute(Function<T, R> service, T parameter) {
        for (int i = 0; i < REPEAT_COUNT; i++) {
            try {
                return service.apply(parameter);
            } catch (RuntimeException e) {
            }
        }
        throw new IllegalArgumentException(REPEAT_FAIL_MESSAGE);
    }
}

