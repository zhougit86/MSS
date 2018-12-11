package com.meicloud.sync;

import com.meicloud.sync.RoleInfo;
import com.meicloud.sync.UserInfo;
import com.meicloud.sync.UserRoleInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {

   private static Logger LOG = Logger.getLogger("JsonUtil");


   public static List getUserList(JSONObject object) throws JSONException {
      try {
         if(object.get("userList") == null) {
            return null;
         } else {
            ArrayList e = new ArrayList();
            JSONArray jsonArray = (JSONArray)object.get("userList");

            for(int i = 0; i < jsonArray.length(); ++i) {
               JSONObject obj = jsonArray.getJSONObject(i);
               UserInfo user = new UserInfo();
               user.setUserAccount(obj.has("userAccount")?obj.get("userAccount").toString():null);
               user.setUserName(obj.has("userName")?obj.get("userName").toString():null);
               user.setUserEmail(obj.has("userEmail")?obj.get("userEmail").toString():null);
               user.setEffStartDate(obj.has("effStartDate")?obj.get("effStartDate").toString():null);
               user.setEffEndDate(obj.has("effEndDate")?obj.get("effEndDate").toString():null);
               user.setUserMobileNo(obj.has("userMobileNo")?obj.get("userMobileNo").toString():null);
               user.setPositionName(obj.has("positionName")?obj.get("positionName").toString():null);
               user.setOpStatus(obj.has("opStatus")?obj.get("opStatus").toString():null);
               e.add(user);
            }

            return e;
         }
      } catch (JSONException var6) {
         LOG.error(var6.getMessage());
         return null;
      }
   }

   public static List getRoleList(JSONObject object) {
      try {
         if(object.get("roleList") == null) {
            return null;
         } else {
            ArrayList e = new ArrayList();
            JSONArray jsonArray = (JSONArray)object.get("roleList");

            for(int i = 0; i < jsonArray.length(); ++i) {
               JSONObject obj = jsonArray.getJSONObject(i);
               RoleInfo role = new RoleInfo();
               role.setRoleCode(obj.has("roleCode")?obj.get("roleCode").toString():null);
               role.setRoleName(obj.has("roleName")?obj.get("roleName").toString():null);
               role.setRoleDesc(obj.has("roleDesc")?obj.get("roleDesc").toString():null);
               role.setOpStatus(obj.has("opStatus")?obj.get("opStatus").toString():null);
               e.add(role);
            }

            return e;
         }
      } catch (JSONException var6) {
         LOG.error(var6.getMessage());
         return null;
      }
   }

   public static List getUserRoleList(JSONObject object, Map roleMap) throws Exception {
      try {
         if(object.get("roleUserDTOList") == null) {
            return null;
         } else {
            ArrayList e = new ArrayList();
            JSONArray jsonArray = (JSONArray)object.get("roleUserDTOList");

            for(int i = 0; i < jsonArray.length(); ++i) {
               JSONObject obj = jsonArray.getJSONObject(i);
               UserRoleInfo userRole = new UserRoleInfo();
               if(obj.has("roleCode")) {
                  userRole.setRoleCode(obj.get("roleCode").toString());
                  if(roleMap.containsKey(userRole.getRoleCode())) {
                     userRole.setRoleId((String)roleMap.get(userRole.getRoleCode()));
                  }
               }

               if(obj.has("userCodes")) {
                  ArrayList userCodes = new ArrayList();
                  JSONArray userCodeArry = (JSONArray)obj.get("userCodes");

                  for(int j = 0; j < userCodeArry.length(); ++j) {
                     UserInfo userInfo = new UserInfo();
                     if(userCodeArry.get(j) != null) {
                        userInfo.setUserAccount(userCodeArry.get(j).toString());
                     }

                     userCodes.add(userInfo);
                  }

                  userRole.setUserCodes(userCodes);
               }

               e.add(userRole);
            }

            return e;
         }
      } catch (JSONException var11) {
         LOG.error(var11.getMessage());
         throw var11;
      }
   }
}
