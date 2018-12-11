package com.meicloud.dao;

import java.util.List;

public interface ExportMapper {

   List dwTime();

   List out1500Time();

   List forbiddenJob();

   List errorJob();

   int jobNum();

   int waitNum();

   int successNum();
}
