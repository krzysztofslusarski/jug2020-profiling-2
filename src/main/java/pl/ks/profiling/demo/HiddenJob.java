package pl.ks.profiling.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class HiddenJob {
    @Scheduled(fixedRate = 1000L)
    public void doInScheduledRuntime() {
        Runtime.getRuntime().gc();
    }
}
