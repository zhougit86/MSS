package com.meicloud.utils;

import java.io.IOException;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {

   private static Logger LOG = Logger.getLogger("Base64Util");


   public static String jdkBase64Encoder(String str) {
      BASE64Encoder encoder = new BASE64Encoder();
      String encode = null;

      try {
         encode = encoder.encode(str.getBytes("UTF-8"));
      } catch (Exception var4) {
         LOG.error(var4.getMessage());
      }

      return encode;
   }

   public static String jdkBase64Decoder(String str) {
      BASE64Decoder decoder = new BASE64Decoder();
      Object decode = null;

      try {
         byte[] e = decoder.decodeBuffer(str);
         return new String(e, "UTF-8");
      } catch (IOException var4) {
         LOG.error(var4.getMessage());
         return (String)decode;
      }
   }
}
