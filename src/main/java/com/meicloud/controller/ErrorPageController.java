package com.meicloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"error"})
public class ErrorPageController {

   @RequestMapping({"/401"})
   public ModelAndView toPage() {
      ModelAndView modelAndView = new ModelAndView("/error/401");
      return modelAndView;
   }
}
