package com.meicloud.services;

import com.meicloud.model.KillJobParam;
import com.meicloud.model.RunJob;

public interface ExecuteJobService {

   void executeJob(RunJob var1) throws Exception;

   void killJob(KillJobParam var1) throws Exception;
}
