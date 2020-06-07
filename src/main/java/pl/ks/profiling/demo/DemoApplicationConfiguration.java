package pl.ks.profiling.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
class DemoApplicationConfiguration {
    private static final int MAX_REQUEST_PAYLOAD_LENGTH = 10 * 1024 * 1024;

    @Bean
    CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(MAX_REQUEST_PAYLOAD_LENGTH);
        return loggingFilter;
    }
}
