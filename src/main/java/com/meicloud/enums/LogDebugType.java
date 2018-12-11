package com.meicloud.enums;


public enum LogDebugType {

   Basic("Basic", 0),
   Debug("Debug", 1),
   Detailed("Detailed", 2),
   Error("Error", 3),
   Nothing("Nothing", 4),
   Rowlevel("Rowlevel", 5);
   // $FF: synthetic field
   private static final LogDebugType[] ENUM$VALUES = new LogDebugType[]{Basic, Debug, Detailed, Error, Nothing, Rowlevel};


   private LogDebugType(String var1, int var2) {}
}
