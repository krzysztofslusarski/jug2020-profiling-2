package pl.ks.profiling.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ServletComponentScan
@SpringBootApplication
/*
 * Run with:
 * -XX:+PreserveFramePointer -XX:+UnlockDiagnosticVMOptions -XX:+DebugNonSafepoints
 * -Xlog:safepoint,gc*,gc+heap=trace,gc+age=trace,gc+phases=trace,gc+humongous=trace:file=/tmp/app.log
 * -Xms90G -Xmx90G
 */
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
