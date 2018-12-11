package com.meicloud.schedule.listener;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.quartz.Trigger.CompletedExecutionInstruction;

public class SimpleTriggerListener implements TriggerListener {

   private final Logger logger = Logger.getLogger(this.getClass());


   public String getName() {
      String name = this.getClass().getSimpleName();
      this.logger.info(" listener name is:" + name);
      return name;
   }

   public void triggerFired(Trigger trigger, JobExecutionContext context) {
      String triggerName = trigger.getKey().getName();
      this.logger.info(triggerName + " was fired");
   }

   public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
      String triggerName = trigger.getKey().getName();
      this.logger.info(triggerName + " was not vetoed");
      return false;
   }

   public void triggerMisfired(Trigger trigger) {
      String triggerName = trigger.getKey().getName();
      this.logger.info(triggerName + " misfired");
   }

   public void triggerComplete(Trigger trigger, JobExecutionContext context, CompletedExecutionInstruction triggerInstructionCode) {
      String triggerName = trigger.getKey().getName();
      this.logger.info(triggerName + " is complete");
   }
}
