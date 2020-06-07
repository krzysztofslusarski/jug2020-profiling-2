package pl.ks.profiling.demo;

import java.util.List;

public interface MagicNumbersCreator {
    List<Double> generateRandomMagicDoubles();

    double[] generateRandomMagicDoublesArray();

    List<Integer> generateRandomMagicIntegers();
}
