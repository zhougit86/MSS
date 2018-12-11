package com.meicloud.services;

import java.util.List;

public interface KettleJobLogService {

   List getListByJobName(String var1);

   List getKettleJobLogDetailListByIdBatch(int var1);
}
