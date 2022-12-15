package com.cyworld.admin;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(
        exclude = { RedisRepositoriesAutoConfiguration.class, DataSourceAutoConfiguration.class }
)
@EnableCaching //캐시 사용 선언
@ComponentScan(basePackages = {"com.cyworld"})
@EntityScan(basePackages = {"com.cyworld"})
@EnableScheduling
@Slf4j
public class AdminApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(AdminApplication.class, args);
        } catch (Throwable t) {
            if(t != null && t.toString().indexOf("SilentExitExceptionHandler") == -1)
                log.error("SpringBoot Startup Error", t);
        }
    }

    //서버 timezone 변경[S]
    @Value("${site.zone.id:Asia/Seoul}") private String zoneId;

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone(zoneId));
    }
    //서버 timezone 변경[E]
}
