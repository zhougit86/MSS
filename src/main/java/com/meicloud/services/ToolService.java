package com.meicloud.services;

import com.meicloud.model.Tool;
import java.util.List;

public interface ToolService {

   List dwTime();

   List out1500Time();

   List forbiddenJob();

   List errorJob();

   int jobNum();

   int waitNum();

   int successNum();

   List list();

   void add(Tool var1);

   void delete(Tool var1);
}
