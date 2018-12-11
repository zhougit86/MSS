package com.meicloud.controller;

import com.meicloud.controller.BasicController;
import com.meicloud.model.Export;
import com.meicloud.services.ToolService;
import io.swagger.annotations.ApiOperation;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"tool"})
public class ToolController extends BasicController {

   private static Logger LOG = LoggerFactory.getLogger(ToolController.class);
   @Autowired
   private ToolService toolService;


   @ApiOperation(
      value = "调度系统作业统计",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/export"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object export(HttpServletResponse response) {
      LOG.info("------------->/tool/export 调度系统作业统计");

      try {
         ServletOutputStream e = response.getOutputStream();
         String fileName = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()) + "_调度系统作业统计.xls";
         response.setContentType("application/vnd.ms-excel");
         response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1"));
         WritableWorkbook workbook = Workbook.createWorkbook(e);
         this.writeDwTime(workbook);
         int out1500Num = this.write1500Time(workbook);
         int forbiddenNum = this.writeForbiddenJob(workbook);
         int errorNum = this.errorJob(workbook);
         this.statistic((double)out1500Num, (double)forbiddenNum, (double)errorNum, workbook);
         workbook.write();
         workbook.close();
         e.close();
         return this.outBound("下载成功");
      } catch (Exception var8) {
         LOG.error("------------->/tool/export 接口异常" + var8.getMessage());
         return this.errorHandler("调度系统作业统计异常:" + var8.getMessage());
      }
   }

   private void statistic(double out1500Num, double forbiddenNum, double errorNum, WritableWorkbook workbook) {
      try {
         double e = (double)this.toolService.jobNum();
         double waitNum = (double)this.toolService.waitNum();
         double successNum = (double)this.toolService.successNum();
         NumberFormat df = NumberFormat.getPercentInstance();
         df.setMinimumFractionDigits(2);
         WritableSheet sheet = workbook.createSheet("统计", 0);
         Label l1 = new Label(0, 0, "总数");
         sheet.addCell(l1);
         Label l2 = new Label(1, 0, "成功数");
         sheet.addCell(l2);
         Label l3 = new Label(2, 0, "失败数");
         sheet.addCell(l3);
         Label l4 = new Label(3, 0, "等待（月）");
         sheet.addCell(l4);
         Label l5 = new Label(4, 0, "禁用");
         sheet.addCell(l5);
         Label l6 = new Label(5, 0, "超1500s");
         sheet.addCell(l6);
         sheet.addCell(new Label(0, 1, String.valueOf(e)));
         sheet.addCell(new Label(1, 1, String.valueOf(successNum)));
         sheet.addCell(new Label(2, 1, String.valueOf(errorNum)));
         sheet.addCell(new Label(3, 1, String.valueOf(waitNum)));
         sheet.addCell(new Label(4, 1, String.valueOf(forbiddenNum)));
         sheet.addCell(new Label(5, 1, String.valueOf(out1500Num)));
         sheet.addCell(new Label(0, 2, "100%"));
         sheet.addCell(new Label(1, 2, df.format(successNum / e)));
         sheet.addCell(new Label(2, 2, df.format(errorNum / e)));
         sheet.addCell(new Label(3, 2, df.format(waitNum / e)));
         sheet.addCell(new Label(4, 2, df.format(forbiddenNum / e)));
         sheet.addCell(new Label(5, 2, df.format(out1500Num / e)));
      } catch (Exception var22) {
         LOG.error(var22.getMessage());
      }

   }

   private int write1500Time(WritableWorkbook workbook) {
      int result = 0;

      try {
         WritableSheet e = workbook.createSheet("超过1500s作业", 2);
         e.setColumnView(0, 20);
         e.setColumnView(1, 20);
         e.setColumnView(2, 35);
         e.setColumnView(3, 20);
         e.setColumnView(4, 20);
         e.setColumnView(5, 10);
         e.setColumnView(6, 10);
         e.setColumnView(7, 10);
         e.setColumnView(8, 10);
         List out1500Time = this.toolService.out1500Time();
         Label topicName = new Label(0, 0, "主题名称");
         e.addCell(topicName);
         Label groupName = new Label(1, 0, "组名称");
         e.addCell(groupName);
         Label jobName = new Label(2, 0, "作业名称");
         e.addCell(jobName);
         Label startTime = new Label(3, 0, "开始时间");
         e.addCell(startTime);
         Label endTime = new Label(4, 0, "结束时间");
         e.addCell(endTime);
         Label jobTodayTime = new Label(5, 0, "今日耗时(s)");
         e.addCell(jobTodayTime);
         Label jobYesTime = new Label(6, 0, "昨日耗时(s)");
         e.addCell(jobYesTime);
         Label jobDBYTime = new Label(7, 0, "前日耗时(s)");
         e.addCell(jobDBYTime);
         Label username = new Label(8, 0, "责任人");
         e.addCell(username);

         for(int i = 0; i < out1500Time.size(); ++i) {
            e.addCell(new Label(0, i + 1, ((Export)out1500Time.get(i)).getTopicName()));
            e.addCell(new Label(1, i + 1, ((Export)out1500Time.get(i)).getGroupName()));
            e.addCell(new Label(2, i + 1, ((Export)out1500Time.get(i)).getJobName()));
            if(((Export)out1500Time.get(i)).getStartTime() != null) {
               e.addCell(new Label(3, i + 1, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(((Export)out1500Time.get(i)).getStartTime())));
            }

            if(((Export)out1500Time.get(i)).getEndTime() != null) {
               e.addCell(new Label(4, i + 1, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(((Export)out1500Time.get(i)).getEndTime())));
            }

            e.addCell(new Label(5, i + 1, ((Export)out1500Time.get(i)).getJobTodayTime()));
            e.addCell(new Label(6, i + 1, ((Export)out1500Time.get(i)).getJobYesTime()));
            e.addCell(new Label(7, i + 1, ((Export)out1500Time.get(i)).getJobDbyTime()));
            e.addCell(new Label(8, i + 1, ((Export)out1500Time.get(i)).getUserName()));
         }

         result = out1500Time.size();
      } catch (RowsExceededException var15) {
         LOG.error(var15.getMessage());
      } catch (WriteException var16) {
         LOG.error(var16.getMessage());
      }

      return result;
   }

   private int writeForbiddenJob(WritableWorkbook workbook) {
      int result = 0;

      try {
         WritableSheet e = workbook.createSheet("禁用作业", 3);
         e.setColumnView(0, 20);
         e.setColumnView(1, 20);
         e.setColumnView(2, 35);
         e.setColumnView(3, 10);
         e.setColumnView(4, 10);
         e.setColumnView(5, 10);
         e.setColumnView(6, 15);
         List forbiddenJob = this.toolService.forbiddenJob();
         Label l0 = new Label(0, 0, "主题名称");
         e.addCell(l0);
         Label l1 = new Label(1, 0, "组名称");
         e.addCell(l1);
         Label l2 = new Label(2, 0, "作业名称");
         e.addCell(l2);
         Label l3 = new Label(3, 0, "组状态");
         e.addCell(l3);
         Label l4 = new Label(4, 0, "作业状态");
         e.addCell(l4);
         Label l5 = new Label(5, 0, "状态");
         e.addCell(l5);
         Label l6 = new Label(6, 0, "责任人");
         e.addCell(l6);

         for(int i = 0; i < forbiddenJob.size(); ++i) {
            e.addCell(new Label(0, i + 1, ((Export)forbiddenJob.get(i)).getTopicName()));
            e.addCell(new Label(1, i + 1, ((Export)forbiddenJob.get(i)).getGroupName()));
            e.addCell(new Label(2, i + 1, ((Export)forbiddenJob.get(i)).getJobName()));
            e.addCell(new Label(3, i + 1, ((Export)forbiddenJob.get(i)).getGroupEnable()));
            e.addCell(new Label(4, i + 1, ((Export)forbiddenJob.get(i)).getJobEnable()));
            e.addCell(new Label(5, i + 1, "禁用"));
            e.addCell(new Label(6, i + 1, ((Export)forbiddenJob.get(i)).getUserName()));
         }

         result = forbiddenJob.size();
      } catch (RowsExceededException var13) {
         LOG.error(var13.getMessage());
      } catch (WriteException var14) {
         LOG.error(var14.getMessage());
      }

      return result;
   }

   private int errorJob(WritableWorkbook workbook) {
      int result = 0;

      try {
         WritableSheet e = workbook.createSheet("失败作业", 1);
         e.setColumnView(0, 20);
         e.setColumnView(1, 20);
         e.setColumnView(2, 35);
         e.setColumnView(3, 20);
         e.setColumnView(4, 20);
         e.setColumnView(5, 10);
         e.setColumnView(6, 15);
         List errorJob = this.toolService.errorJob();
         Label l0 = new Label(0, 0, "主题名称");
         e.addCell(l0);
         Label l1 = new Label(1, 0, "组名称");
         e.addCell(l1);
         Label l2 = new Label(2, 0, "作业名称");
         e.addCell(l2);
         Label l3 = new Label(3, 0, "开始时间");
         e.addCell(l3);
         Label l4 = new Label(4, 0, "结束时间");
         e.addCell(l4);
         Label l5 = new Label(5, 0, "耗时");
         e.addCell(l5);
         Label l6 = new Label(6, 0, "责任人");
         e.addCell(l6);

         for(int i = 0; i < errorJob.size(); ++i) {
            e.addCell(new Label(0, i + 1, ((Export)errorJob.get(i)).getTopicName()));
            e.addCell(new Label(1, i + 1, ((Export)errorJob.get(i)).getGroupName()));
            e.addCell(new Label(2, i + 1, ((Export)errorJob.get(i)).getJobName()));
            if(((Export)errorJob.get(i)).getStartTime() != null) {
               e.addCell(new Label(3, i + 1, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(((Export)errorJob.get(i)).getStartTime())));
            }

            if(((Export)errorJob.get(i)).getEndTime() != null) {
               e.addCell(new Label(4, i + 1, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(((Export)errorJob.get(i)).getEndTime())));
            }

            e.addCell(new Label(5, i + 1, ((Export)errorJob.get(i)).getJobTodayTime()));
            e.addCell(new Label(6, i + 1, ((Export)errorJob.get(i)).getUserName()));
         }

         result = errorJob.size();
      } catch (RowsExceededException var13) {
         LOG.error(var13.getMessage());
      } catch (WriteException var14) {
         LOG.error(var14.getMessage());
      }

      return result;
   }

   private void writeDwTime(WritableWorkbook workbook) {
      try {
         WritableSheet e = workbook.createSheet("窗口期", 4);
         e.setColumnView(0, 20);
         e.setColumnView(1, 20);
         e.setColumnView(2, 20);
         e.setColumnView(3, 10);
         e.setColumnView(4, 10);
         e.setColumnView(5, 20);
         e.setColumnView(6, 20);
         List dwTime = this.toolService.dwTime();
         Label topicName = new Label(0, 0, "主题名称");
         e.addCell(topicName);
         Label groupName = new Label(1, 0, "组名称");
         e.addCell(groupName);
         Label cron = new Label(2, 0, "调度表达式");
         e.addCell(cron);
         Label jobNum = new Label(3, 0, "作业数量");
         e.addCell(jobNum);
         Label groupTime = new Label(4, 0, "组耗时(s)");
         e.addCell(groupTime);
         Label startTime = new Label(5, 0, "开始时间");
         e.addCell(startTime);
         Label endTime = new Label(6, 0, "结束时间");
         e.addCell(endTime);

         for(int i = 0; i < dwTime.size(); ++i) {
            e.addCell(new Label(0, i + 1, ((Export)dwTime.get(i)).getTopicName()));
            e.addCell(new Label(1, i + 1, ((Export)dwTime.get(i)).getGroupName()));
            e.addCell(new Label(2, i + 1, ((Export)dwTime.get(i)).getCron()));
            e.addCell(new Label(3, i + 1, ((Export)dwTime.get(i)).getJobNum()));
            e.addCell(new Label(4, i + 1, ((Export)dwTime.get(i)).getGroupTime()));
            if(((Export)dwTime.get(i)).getStartTime() != null) {
               e.addCell(new Label(5, i + 1, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(((Export)dwTime.get(i)).getStartTime())));
            }

            if(((Export)dwTime.get(i)).getEndTime() != null) {
               e.addCell(new Label(6, i + 1, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(((Export)dwTime.get(i)).getEndTime())));
            }
         }
      } catch (RowsExceededException var12) {
         LOG.error(var12.getMessage());
      } catch (WriteException var13) {
         LOG.error(var13.getMessage());
      }

   }
}
