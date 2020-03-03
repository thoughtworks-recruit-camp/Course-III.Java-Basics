import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

public class AppAlt {
    public static void main(String[] args) {
        CustomStringConsumer customStringConsumer = s -> System.out.printf("*%s*\n", s);
        Consumer<String> stringConsumer = s -> System.out.printf("*%s*\n", s);
        CustomIntFunction customIntFunction = i -> i * i;
        Function<Integer, Integer> integerIntegerFunction = i -> i * i;
        IntFunction<Integer> integerIntFunction = i -> i * i;
        ToIntFunction<Integer> integerToIntFunction = i -> i * i;

        customStringConsumer.consume("customStringConsumer");
        stringConsumer.accept("stringConsumer");
        System.out.printf("customIntFunction: 4 -> %d\n", customIntFunction.function(4))
                .printf("integerIntegerFunction: 4 -> %d(%s)\n", integerIntFunction.apply(4), integerIntegerFunction.apply(4).getClass().getSimpleName())
                .printf("integerIntFunction: 4 -> %d(%s)\n", integerIntFunction.apply(4), integerIntFunction.apply(4).getClass().getSimpleName())
                .printf("integerToIntFunction: 4 -> %s\n", integerToIntFunction.applyAsInt(4));
    }
}
