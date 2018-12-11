//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.meicloud.schedule.listener;

import com.meicloud.schedule.MSSScheduler;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger LOG = Logger.getLogger("SchedulerListener");
    @Autowired
    public MSSScheduler mSSScheduler;

    public SchedulerListener() {
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            this.mSSScheduler.scheduleJobs();
        } catch (SchedulerException var3) {
            LOG.error(var3.getMessage());
        }

    }
}
