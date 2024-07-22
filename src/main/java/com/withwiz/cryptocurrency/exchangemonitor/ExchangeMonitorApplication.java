package com.withwiz.cryptocurrency.exchangemonitor;

import com.withwiz.cryptocurrency.exchangemonitor.scheduler.config.Instance;
import com.withwiz.cryptocurrency.exchangemonitor.scheduler.config.SchedulerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.InvocationTargetException;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.withwiz")
@Slf4j
public class ExchangeMonitorApplication {

    @Autowired
    SchedulerProperties schedulerProperties;

    /**
     * application start
     *
     * @param context Spring ApplicationContext
     */
    public void start(ApplicationContext context) {
//        UpbitProjectDisclosureScheduler disclosureScheduler = (UpbitProjectDisclosureScheduler) context.getBean("upbitProjectDisclosureScheduler");
//        disclosureScheduler.begin();
//        UpbitNoticeListingScheduler noticeListingScheduler = (UpbitNoticeListingScheduler) context.getBean("upbitNoticeListingScheduler");
//        noticeListingScheduler.begin();
        try {
            for (Instance instance : schedulerProperties.getInstances()) {
                Class clz = Class.forName(instance.getClassName());
                Object obj = context.getBean(clz);
                obj.getClass().getMethod("begin").invoke(obj);
            }
        } catch (IllegalAccessException e) {
            log.error("{}", e);
        } catch (InvocationTargetException e) {
            log.error("{}", e);
        } catch (NoSuchMethodException e) {
            log.error("{}", e);
        } catch (ClassNotFoundException e) {
            log.error("{}", e);
        }
    }

    /**
     * application main
     *
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ExchangeMonitorApplication.class, args);
        ((ExchangeMonitorApplication) context.getBean("exchangeMonitorApplication")).start(context);
    }

}
