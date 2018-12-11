package com.meicloud.services;

import com.meicloud.model.JobLog;
import java.util.List;

public interface LogService {

   List getListByJobId(int var1) throws Exception;

   JobLog getLogById(int var1) throws Exception;
}
