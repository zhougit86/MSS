package com.meicloud.services;

import java.util.List;

public interface ExportService {

   List dwTime();

   List out1500Time();

   List forbiddenJob();

   List errorJob();

   int jobNum();

   int waitNum();

   int successNum();
}
