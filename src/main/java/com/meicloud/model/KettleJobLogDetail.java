package com.meicloud.model;

import com.alibaba.fastjson.JSONArray;
import java.io.Serializable;
import java.util.Date;

public class KettleJobLogDetail implements Serializable {

   private static final long serialVersionUID = 1L;
   private int idBatch;
   private String channelId;
   private Date logDate;
   private String transName;
   private String stepName;
   private int linesRead;
   private int linesWritten;
   private int linesUpdated;
   private int linesInput;
   private int linesOutput;
   private int linesReject;
   private int errors;
   private String result;
   private int nrResultRows;
   private int nrResultFiles;
   private int copyNr;
   private int jobId;


   public int getJobId() {
      return this.jobId;
   }

   public void setJobId(int jobId) {
      this.jobId = jobId;
   }

   public int getIdBatch() {
      return this.idBatch;
   }

   public void setIdBatch(int idBatch) {
      this.idBatch = idBatch;
   }

   public String getChannelId() {
      return this.channelId;
   }

   public void setChannelId(String channelId) {
      this.channelId = channelId;
   }

   public Date getLogDate() {
      return this.logDate;
   }

   public void setLogDate(Date logDate) {
      this.logDate = logDate;
   }

   public String getTransName() {
      return this.transName;
   }

   public void setTransName(String transName) {
      this.transName = transName;
   }

   public String getStepName() {
      return this.stepName;
   }

   public void setStepName(String stepName) {
      this.stepName = stepName;
   }

   public int getLinesRead() {
      return this.linesRead;
   }

   public void setLinesRead(int linesRead) {
      this.linesRead = linesRead;
   }

   public int getLinesWritten() {
      return this.linesWritten;
   }

   public void setLinesWritten(int linesWritten) {
      this.linesWritten = linesWritten;
   }

   public int getLinesUpdated() {
      return this.linesUpdated;
   }

   public void setLinesUpdated(int linesUpdated) {
      this.linesUpdated = linesUpdated;
   }

   public int getLinesInput() {
      return this.linesInput;
   }

   public void setLinesInput(int linesInput) {
      this.linesInput = linesInput;
   }

   public int getLinesOutput() {
      return this.linesOutput;
   }

   public void setLinesOutput(int linesOutput) {
      this.linesOutput = linesOutput;
   }

   public int getLinesReject() {
      return this.linesReject;
   }

   public void setLinesReject(int linesReject) {
      this.linesReject = linesReject;
   }

   public int getErrors() {
      return this.errors;
   }

   public void setErrors(int errors) {
      this.errors = errors;
   }

   public String getResult() {
      return this.result;
   }

   public void setResult(String result) {
      this.result = result;
   }

   public int getNrResultRows() {
      return this.nrResultRows;
   }

   public void setNrResultRows(int nrResultRows) {
      this.nrResultRows = nrResultRows;
   }

   public int getNrResultFiles() {
      return this.nrResultFiles;
   }

   public void setNrResultFiles(int nrResultFiles) {
      this.nrResultFiles = nrResultFiles;
   }

   public int getCopyNr() {
      return this.copyNr;
   }

   public void setCopyNr(int copyNr) {
      this.copyNr = copyNr;
   }

   public String toString() {
      return JSONArray.toJSONString(this);
   }
}
