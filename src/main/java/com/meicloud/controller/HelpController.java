package com.meicloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"help"})
public class HelpController {

   @RequestMapping(
      value = {"/cronExample"},
      method = {RequestMethod.POST, RequestMethod.GET}
   )
   public ModelAndView cronExample() {
      ModelAndView modelAndView = new ModelAndView("help/cronExample");
      return modelAndView;
   }
}
