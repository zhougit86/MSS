package com.meicloud.services;


public interface SendJobService {

   void send(int var1);

   boolean completeGroup(int var1) throws Exception;

   boolean completeJob(int var1) throws Exception;
}
