import java.util.function.Consumer;
import java.util.function.IntFunction;

public class App {
    private static String testString = "Test String";
    private static int testInt = 4;

    public static void main(String[] args) {
        useCustomStringConsumer(s -> System.out.printf("*%s*\n", s));
        useStringConsumer(s -> System.out.printf("*%s*\n", s));
        System.out.printf("%d -> %d\n", testInt, useCustomIntFunction(n -> n * n));
        System.out.printf("%d -> %d\n", testInt, useIntegerIntFunction(n -> n * n));
    }

    private static void useCustomStringConsumer(CustomStringConsumer customStringConsumer) {
        customStringConsumer.consume(testString);
    }

    private static void useStringConsumer(Consumer<String> stringConsumer) {
        stringConsumer.accept(testString);
    }

    private static int useCustomIntFunction(CustomIntFunction customIntFunction) {
        return customIntFunction.function(testInt);
    }

    private static int useIntegerIntFunction(IntFunction<Integer> integerIntFunction) {
        return integerIntFunction.apply(testInt);
    }
}
