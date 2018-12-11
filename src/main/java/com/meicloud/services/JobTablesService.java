package com.meicloud.services;

import com.meicloud.model.JobTables;
import java.util.List;

public interface JobTablesService {

   void add(JobTables var1);

   void update(JobTables var1);

   void delete(int var1);

   JobTables getById(int var1);

   List listByJobId(int var1);

   void deleteByJobId(int var1);

   boolean checkIfExist(JobTables var1, boolean var2);
}
