package com.meicloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class MssApplication {

   public static void main(String[] args) {
      SpringApplication.run(MssApplication.class, args);
   }
}
