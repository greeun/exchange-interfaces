package com.withwiz.cryptocurrency.exchangemonitor;

import com.withwiz.cryptocurrency.exchangeinterfaces.exchange.upbit.scheduler.UpbitProjectDisclosureScheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableAspectJAutoProxy
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.withwiz")
public class ExchangeMonitorApplication {

    /**
     * application main
     *
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ExchangeMonitorApplication.class, args);
        UpbitProjectDisclosureScheduler scheduler = (UpbitProjectDisclosureScheduler) context.getBean("upbitProjectDisclosureScheduler");
        scheduler.begin();
    }

}
