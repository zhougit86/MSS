package com.meicloud.services;


public interface JobCheckAndUpdateService {

   void doCheckAndSend();

   boolean doCheckAndUpdateByGroupId(int var1);
}
