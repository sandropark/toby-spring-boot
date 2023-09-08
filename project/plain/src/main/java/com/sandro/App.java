package com.sandro;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    @Bean
    ApplicationRunner run(ConditionEvaluationReport report) {
        // 스프링 부트가 등록해준 빈을 확인할 수 있는 방법
        return args -> {
            System.out.println(report.getConditionAndOutcomesBySource().entrySet().stream()
                    .filter(co -> co.getValue().isFullMatch() && !co.getKey().contains("Jmx"))
                    .map(co -> {
                        System.out.println(co.getKey());
                        co.getValue().forEach(c -> System.out.println("\t" + c.getOutcome()));
                        System.out.println();
                        return co;
                    }).count());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
