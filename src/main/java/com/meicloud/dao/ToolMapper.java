package com.meicloud.dao;

import com.meicloud.model.Tool;
import java.util.List;

public interface ToolMapper {

   List dwTime();

   List out1500Time();

   List forbiddenJob();

   List errorJob();

   List list();

   void add(Tool var1);

   int jobNum();

   int waitNum();

   int successNum();

   void delete(Tool var1);
}
