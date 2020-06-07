package pl.ks.profiling.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MagicNumbersCreatorImpl implements MagicNumbersCreator {
    private static List<Integer> MAGIC_INTS = IntStream.range(0, 1_000).boxed().collect(Collectors.toList());
    private static int DOUBLE_LIST_SIZE = 10_000_000;
    private static List<Double> REPORT_LIST;
    private static double[] REPORT_ARRAY;

    static {
        regenerate(new Random());
    }

    public static void beforeJIT() {
        MAGIC_INTS = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        DOUBLE_LIST_SIZE = 1_000;
        regenerate(new Random());
    }

    public static void afterJIT() {
        MAGIC_INTS = IntStream.range(0, 1_000).boxed().collect(Collectors.toList());
        DOUBLE_LIST_SIZE = 5_000_000;
        regenerate(new Random());
    }

    private static void regenerate(Random random) {
        REPORT_LIST = new ArrayList<>(DOUBLE_LIST_SIZE);
        REPORT_ARRAY = new double[DOUBLE_LIST_SIZE];
        for (int i = 0; i < DOUBLE_LIST_SIZE; i++) {
            double nextDouble = random.nextDouble();
            REPORT_LIST.add(nextDouble);
            REPORT_ARRAY[i] = nextDouble;
        }
    }

    @Override
    public List<Double> generateRandomMagicDoubles() {
        return REPORT_LIST;
    }

    @Override
    public List<Integer> generateRandomMagicIntegers() {
        return MAGIC_INTS;
    }

    @Override
    public double[] generateRandomMagicDoublesArray() {
        return REPORT_ARRAY;
    }
}
