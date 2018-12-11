package com.meicloud.Exception;

import com.alibaba.fastjson.JSON;
import com.meicloud.Exception.JsonException;
import com.meicloud.model.ErrorInfo;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHander {

   public static final String DEFAULT_ERROR_VIEW = "error";


   @ExceptionHandler({Exception.class})
   public ModelAndView defaultErrorHandler(HttpServletRequest req, HttpServletResponse res, Exception e) throws Exception {
      String accept = req.getHeader("Accept");
      String requestType = req.getHeader("X-Requested-With");
      boolean ajax = requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest");
      if(!ajax && (accept == null || !accept.contains("json"))) {
         ModelAndView mav1 = new ModelAndView();
         mav1.addObject("exception", e);
         mav1.addObject("url", req.getRequestURL());
         mav1.setViewName("error");
         return mav1;
      } else {
         ErrorInfo mav = new ErrorInfo();
         mav.setMessage(e.getMessage());
         mav.setCode(ErrorInfo.ERROR);
         mav.setUrl(req.getRequestURL().toString());
         PrintWriter writer = res.getWriter();
         writer.write(JSON.toJSONString(mav));
         writer.flush();
         writer.close();
         return null;
      }
   }

   @ExceptionHandler({JsonException.class})
   @ResponseBody
   public ErrorInfo jsonErrorHandler(HttpServletRequest req, JsonException e) throws Exception {
      ErrorInfo r = new ErrorInfo();
      r.setMessage(e.getMessage());
      r.setCode(ErrorInfo.ERROR);
      r.setUrl(req.getRequestURL().toString());
      return r;
   }
}
