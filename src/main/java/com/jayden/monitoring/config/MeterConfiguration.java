package com.jayden.monitoring.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.config.NamingConvention;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.actuate.metrics.web.servlet.DefaultWebMvcTagsProvider;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import com.google.common.cache.CacheBuilder;

@Configuration
public class MeterConfiguration {

    private final Map<HttpServletResponse, Tags> responseTags = CacheBuilder.newBuilder()
        .maximumSize(10_000)
        .expireAfterWrite(Duration.ofSeconds(10))
        .<HttpServletResponse, Tags>build()
        .asMap();

    @Bean
    public MeterRegistry meterRegistry() {
        MeterRegistry meterRegistry = new SimpleMeterRegistry();
        meterRegistry.config().namingConvention(NamingConvention.dot);
        return meterRegistry;
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

    @Bean
    public WebMvcTagsProvider webMvcTagsProvider() {
        return new DefaultWebMvcTagsProvider() {
            @Override
            public Iterable<Tag> getTags(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Throwable exception) {
                return Tags.concat(
                    super.getTags(request, response, handler, exception),
                    Optional.ofNullable(responseTags.remove(response)).orElse(Tags.empty())
                );
            }
        };
    }
}
