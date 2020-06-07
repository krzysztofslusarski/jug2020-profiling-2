package pl.ks.profiling.demo.memory;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstMemoryController {
    private static volatile long a;

    private long count = 3_000_000;

    @GetMapping("/memory/first")
    public HttpEntity<Long> get() {
        for (long i = 0; i < count; i++) {
            a++;
        }
        return new HttpEntity<>(a);
    }

    public void beforeJIT() {
        count = 10_000;
    }

    public void afterJIT() {
        count = 3_000_000;
    }
}
