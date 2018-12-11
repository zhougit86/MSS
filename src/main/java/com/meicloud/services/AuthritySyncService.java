package com.meicloud.services;

import org.json.JSONObject;

public interface AuthritySyncService {

   void saveUser(JSONObject var1) throws Exception;

   void saveRole(JSONObject var1) throws Exception;

   void saveUserRole(JSONObject var1) throws Exception;
}
