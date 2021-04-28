package com.withwiz.cryptocurrency.exchangeinterfaces.exchange.upbit.scheduler;

import com.withwiz.cryptocurrency.exchangeinterfaces.exchange.upbit.UpbitConsts;
import com.withwiz.cryptocurrency.exchangeinterfaces.exchange.upbit.dto.notice.NoticePost;
import com.withwiz.cryptocurrency.exchangeinterfaces.exchange.upbit.util.UpbitNoticeUtil;
import com.withwiz.interfaces.scheduler.config.SchedulerProperties;
import com.withwiz.interfaces.telegram.TelegramUtil;
import com.withwiz.interfaces.telegram.config.Bot;
import com.withwiz.interfaces.telegram.config.BotProperties;
import com.withwiz.interfaces.telegram.dto.TelegramResponseSendMessage;
import com.withwiz.plankton.scheduler.ASpringDynamicScheduler;
import com.withwiz.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * scheduled job: listing in notice
 */
@Component
public class UpbitNoticeListingScheduler extends ASpringDynamicScheduler {

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
            NoticePost newNoticePost = UpbitNoticeUtil.checkNewPost();
            //if new post exist
            if (newNoticePost != null) {
                //publish a message to telegram
                TelegramResponseSendMessage response = null;
                String detailPageUrl = UpbitConsts.URL_NOTICE_DETAIL_PAGE + newNoticePost.getId();
                String sendMessage = message + newNoticePost.toString() + ", url=" + detailPageUrl;
                for(Bot bot: botProperties.getBots()) {
                    response = TelegramUtil.publishMessageToChannel(bot.getToken(), bot.getChatId(), sendMessage);
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
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                int seconds = RandomUtil.randomInt(randomMaxBound);
                log.info("delay randomized: {}", seconds);

                Calendar nextExecutionTime =  new GregorianCalendar();
                Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
                nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                nextExecutionTime.add(Calendar.MILLISECOND, seconds * 1000); //you can get the value from wherever you want
                return nextExecutionTime.getTime();
            }
        };
    }
}
