package com.withwiz.cryptocurrency.exchangemonitor.scheduler;

import com.withwiz.cryptocurrency.exchangemonitor.config.BotProperties;
import com.withwiz.cryptocurrency.exchangeinterfaces.upbit.dto.disclosure.DisclosurePost;
import com.withwiz.cryptocurrency.exchangeinterfaces.upbit.util.UpbitDisclosureUtil;
import com.withwiz.cryptocurrency.exchangemonitor.scheduler.config.SchedulerProperties;
import com.withwiz.sandbeach.interfaces.telegram.TelegramUtil;
import com.withwiz.sandbeach.interfaces.telegram.config.Bot;
import com.withwiz.sandbeach.interfaces.telegram.dto.TelegramResponseSendMessage;
import com.withwiz.sandbeach.scheduler.ASpringDynamicScheduler;
import com.withwiz.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

/**
 * scheduled job
 */
@Component
public class UpbitProjectDisclosureScheduler extends ASpringDynamicScheduler {

    @Autowired
    BotProperties botProperties;

    @Autowired
    SchedulerProperties schedulerProperties;

    /**
     * telegram publish message
     */
    @Value("${telegram.notice.message}")
    String message;

    @Value("${exchange.upbit.schedule.delay.max}")
    int randomMaxBound;

    @Override
    public void processJob(Map data) {
        try {
            DisclosurePost newDisclosurePost = UpbitDisclosureUtil.checkNewDisclosure();
            //if new disclosure exist
            if (newDisclosurePost != null) {
                //publish a message to telegram
                TelegramResponseSendMessage response = null;
                String sendMessage = message + newDisclosurePost.toString();
                for (Bot bot : botProperties.getBots()) {
                    response = TelegramUtil.publishMessageToChannel(bot.getToken(), Long.toString(bot.getChatId()), sendMessage);
                    log.info("telegram response: {}", response);
                }
            }
        } catch (IOException e) {
            log.error("{}", e);
        }
    }

    @Override
    public Trigger getTrigger() {

        return new Trigger() {
//            @Override
//            public Date nextExecutionTime(TriggerContext triggerContext) {
//                int seconds = RandomUtil.randomInt(randomMaxBound);
//                log.info("delay randomized: {}", seconds);
//
//                Calendar nextExecutionTime = new GregorianCalendar();
//                Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
//                nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
//                nextExecutionTime.add(Calendar.MILLISECOND, seconds * 1000); //you can get the value from wherever you want
//                return nextExecutionTime.getTime();
//            }

            @Override
            public Instant nextExecution(TriggerContext triggerContext) {
                int seconds = RandomUtil.randomInt(randomMaxBound);
                log.info("delay randomized: {}", seconds);

                Instant nextExecutionTime = triggerContext.lastActualExecution() != null
                        ? Instant.ofEpochMilli(triggerContext.lastActualExecution().toEpochMilli()) : Instant.now();
                nextExecutionTime.plusMillis(seconds * 1000); //you can get the value from wherever you want
                return nextExecutionTime;
            }
        };
    }
}
