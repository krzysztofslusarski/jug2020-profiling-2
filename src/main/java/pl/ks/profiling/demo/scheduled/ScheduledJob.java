package pl.ks.profiling.demo.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class ScheduledJob {
    @Scheduled(fixedRate = 5000L)
    public void doInScheduledSystem() {
        System.gc();
    }
}
