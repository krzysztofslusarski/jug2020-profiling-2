package pl.ks.profiling.demo.stat;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ks.profiling.demo.MagicNumbersCreator;
import pl.ks.profiling.demo.MagicNumbersCreatorImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StatController {
    private static MagicNumbersCreator magicNumbersCreator = new MagicNumbersCreatorImpl();

    private static List<Double> generateReport() {
        List<Double> ret = new ArrayList<>();
        ret.add(compute1());
        ret.add(compute2());
        ret.add(compute3());
        ret.add(compute4());
        return ret;
    }

    private static Double compute1() {
        return magicNumbersCreator.generateRandomMagicDoubles().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private static Double compute2() {
        return magicNumbersCreator.generateRandomMagicDoubles().stream()
                .mapToDouble(Double::doubleValue)
                .min().orElse(0);
    }

    private static Double compute3() {
        return magicNumbersCreator.generateRandomMagicDoubles().stream()
                .mapToDouble(Double::doubleValue)
                .sorted()
                .sum();
    }

    private static Double compute4() {
        return null;
    }

    @RequestMapping(value = "/stat", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Double> get() {
        return generateReport();
    }
}

