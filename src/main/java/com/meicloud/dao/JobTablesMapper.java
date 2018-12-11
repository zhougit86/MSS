package com.meicloud.dao;

import com.meicloud.model.JobTables;
import java.util.List;

public interface JobTablesMapper {

   void add(JobTables var1);

   void update(JobTables var1);

   void delete(int var1);

   void deleteByJobId(int var1);

   JobTables getById(int var1);

   List getByJobId(int var1);

   int checkIfExists4New(JobTables var1);

   int checkIfExists4Update(JobTables var1);
}
