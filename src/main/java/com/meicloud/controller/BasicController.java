package com.meicloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.meicloud.utils.BasicDataUtil;
import com.meicloud.utils.beans.ResultBean;
import java.util.List;

public abstract class BasicController {

   protected ResultBean outBound(Object data) throws IllegalAccessException {
      ResultBean result = new ResultBean();
      result.setData(data);
      result.set__statusCode("1");
      return result;
   }

   protected ResultBean errorHandler(Exception e) {
      ResultBean result = new ResultBean();
      result.setData((Object)null);
      result.set__statusCode("0");
      result.set__errorMessage(e.getMessage());
      return result;
   }

   protected ResultBean errorHandler(String error) {
      ResultBean result = new ResultBean();
      result.setData((Object)null);
      result.set__statusCode("0");
      result.set__errorMessage(error);
      return result;
   }

   protected String getDataInfo(Object ob1, Object ob2, Object ob3) {
      List objList = (List)ob2;
      Long count = (Long)ob3;
      return JSONObject.toJSONString(new BasicDataUtil(ob1, objList, count, "X"), new SerializerFeature[]{SerializerFeature.WriteNullStringAsEmpty});
   }

   protected String getDataObjectInfo(Object ob1, Object ob2, Object ob3) {
      List objList = (List)ob2;
      Long count = (Long)ob3;
      return JSONObject.toJSONString(new BasicDataUtil(ob1, objList, count, (String)null), new SerializerFeature[]{SerializerFeature.WriteNullStringAsEmpty});
   }
}
