package com.meicloud.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

public class Encryp3DES {

   private static Logger LOG = Logger.getLogger("Encryp3DES");
   private static final String Algorithm = "DESede";
   private static final int keysize = 168;


   public static String encryptDes(String src) {
      try {
         byte[] e3 = getKeySpec();
         if(e3 != null) {
            SecretKeySpec deskey = new SecretKeySpec(e3, "DESede");
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(1, deskey);
            return byte2hexString(c1.doFinal(src.getBytes()));
         }

         return null;
      } catch (NoSuchAlgorithmException var4) {
         LOG.error(var4.getMessage());
      } catch (NoSuchPaddingException var5) {
         LOG.error(var5.getMessage());
      } catch (Exception var6) {
         LOG.error(var6.getMessage());
      }

      return null;
   }

   public static String discryptDes(String src) {
      try {
         byte[] e3 = getKeySpec();
         if(e3 != null) {
            SecretKeySpec deskey = new SecretKeySpec(e3, "DESede");
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(2, deskey);
            return new String(c1.doFinal(hexStringToBytes(src)));
         }

         return null;
      } catch (NoSuchAlgorithmException var4) {
         System.out.println("密文解密失败");
         LOG.error(var4.getMessage());
      } catch (NoSuchPaddingException var5) {
         System.out.println("密文解密失败");
         LOG.error(var5.getMessage());
      } catch (Exception var6) {
         System.out.println("密文解密失败");
         LOG.error(var6.getMessage());
      }

      return null;
   }

   public static String byte2hexString(byte[] b) {
      String hs = "";
      String stmp = "";

      for(int n = 0; n < b.length; ++n) {
         stmp = Integer.toHexString(b[n] & 255);
         if(stmp.length() == 1) {
            hs = hs + "0" + stmp;
         } else {
            hs = hs + stmp;
         }
      }

      return hs.toUpperCase();
   }

   public static byte[] hexStringToBytes(String hexString) {
      if(hexString != null && !hexString.equals("")) {
         hexString = hexString.toUpperCase();
         int length = hexString.length() / 2;
         char[] hexChars = hexString.toCharArray();
         byte[] d = new byte[length];

         for(int i = 0; i < length; ++i) {
            int pos = i * 2;
            d[i] = (byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]) & 255);
         }

         return d;
      } else {
         return null;
      }
   }

   private static byte charToByte(char c) {
      return (byte)"0123456789ABCDEF".indexOf(c);
   }

   public static void writeKeyFile(String strKey) throws IOException {
      FileOutputStream f = null;
      ObjectOutputStream b = null;

      try {
         KeyGenerator e = KeyGenerator.getInstance("DESede");
         SecureRandom sr = new SecureRandom(strKey.getBytes());
         e.init(168, sr);
         SecretKey key = e.generateKey();
         URL url = Encryp3DES.class.getResource("/");
         f = new FileOutputStream(url.getPath() + "keyfile.dat");
         b = new ObjectOutputStream(f);
         b.writeObject(key);
      } catch (NoSuchAlgorithmException var15) {
         LOG.error(var15.getMessage());
      } finally {
         try {
            if(b != null) {
               b.close();
            }

            if(f != null) {
               f.close();
            }
         } catch (Exception var14) {
            LOG.error(var14.getMessage());
         }

      }

   }

   public static byte[] getKeySpec() {
      byte[] keyBytes = null;
      ObjectInputStream o = null;

      try {
         ClassPathResource e = new ClassPathResource("keyfile.dat");
         o = new ObjectInputStream(e.getInputStream());
         Key key1 = (Key)o.readObject();
         keyBytes = key1.getEncoded();
      } catch (Exception var12) {
         LOG.error(var12.getMessage());
      } finally {
         try {
            if(o != null) {
               o.close();
            }
         } catch (Exception var11) {
            LOG.error(var11.getMessage());
         }

      }

      return keyBytes;
   }

   public static void main(String[] args) {
      new Encryp3DES();
      String[] src = new String[]{"78A295E2E57DD68C76B09C2CC3A75A89"};

      for(int i = 0; i < src.length; ++i) {
         System.out.println(discryptDes(src[i]));
         System.out.println(discryptDes(encryptDes(src[i])));
         System.out.println(encryptDes(src[i]));
      }

   }
}
