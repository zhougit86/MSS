package com.meicloud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meicloud.controller.BasicController;
import com.meicloud.dto.DicInfo;
import com.meicloud.enums.LengthLimit;
import com.meicloud.model.GroupLevel;
import com.meicloud.services.GroupService;
import com.meicloud.utils.Utils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
@RequestMapping({"groupLevel"})
public class GroupLevelController extends BasicController {

   private static final Logger LOG = Logger.getLogger(GroupLevelController.class);
   @Autowired
   private GroupService groupService;


   @ApiOperation(
      value = "组织层级配置列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/list"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object list(
      @ApiParam(
         value = "页数",
         required = true
      ) @RequestParam String pagenum, 
      @ApiParam(
         value = "行数",
         required = true
      ) @RequestParam String rownum) {
      LOG.info("------------->/groupLevel/list 组织层级配置列表");

      try {
         PageHelper.startPage(Integer.parseInt(Utils.isEmptyStr(pagenum)?"1":pagenum), Integer.parseInt(Utils.isEmptyStr(rownum)?"10":rownum));
         List e = this.groupService.getGroupLevelList();
         PageInfo page = new PageInfo(e);
         return this.outBound(this.getDataInfo(new GroupLevel(), page.getList(), Long.valueOf(page.getTotal())));
      } catch (Exception var5) {
         LOG.error("------------->/groupLevel/list 接口异常" + var5.getMessage());
         return this.errorHandler("组织层级配置列表查询异常:" + var5.getMessage());
      }
   }

   @ApiOperation(
      value = "组织层级配置详情",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/detail"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object detail(Integer levelId) {
      LOG.info("------------->/groupLevel/detail 组织层级配置详情");

      try {
         GroupLevel e = this.groupService.getGroupLevelByGroupId(levelId);
         return this.outBound(e);
      } catch (Exception var3) {
         LOG.error("------------->/groupLevel/detail 接口异常" + var3.getMessage());
         return this.errorHandler("组织层级配置详情查询异常:" + var3.getMessage());
      }
   }

   @ApiOperation(
      value = "层级配置下拉列表",
      notes = "返回json"
   )
   @RequestMapping(
      value = {"/optionList"},
      method = {RequestMethod.POST}
   )
   @ResponseBody
   public Object optionList(
      @ApiParam(
         value = "集群ID",
         required = true
      ) @RequestParam String queueId) {
      LOG.info("------------->/groupLevel/optionList 层级配置下拉列表");

      try {
         ArrayList e = new ArrayList();
         List list = this.groupService.getGroupLevelListByQueueId(queueId);
         if(!Utils.isEmpityCollection(list)) {
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
               GroupLevel groupLevel = (GroupLevel)var5.next();
               DicInfo info = new DicInfo();
               info.setKey(groupLevel.getLevelId());
               info.setName(groupLevel.getLevelName());
               e.add(info);
            }
         }

         return this.outBound(e);
      } catch (Exception var7) {
         LOG.error("------------->/groupLevel/optionList 接口异常" + var7.getMessage());
         return this.errorHandler("层级配置下拉列表查询异常:" + var7.getMessage());
      }
   }

   @RequestMapping(
      value = {"/save"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "组织层级配置新增/修改",
      notes = "返回json"
   )
   @ResponseBody
   public Object save(@RequestBody GroupLevel groupLevel) {
      LOG.info("------------->/groupLevel/save 组织层级配置保存");

      try {
         if(groupLevel.getLevelName().length() > Integer.parseInt(LengthLimit.GoupgLevel_levelName.toString())) {
            return this.errorHandler("层级名称过长！");
         } else {
            boolean e = this.groupService.checkLevelNameExists(groupLevel);
            if(e) {
               return this.errorHandler("名称为[" + groupLevel.getLevelName() + "]的层级已存在！");
            } else {
               if(Utils.isEmptyStr((Object)groupLevel.getLevelId())) {
                  this.groupService.insertGroupLevel(groupLevel);
               } else {
                  this.groupService.updateGroupLevel(groupLevel);
               }

               return this.outBound("组织层级配置保存成功");
            }
         }
      } catch (Exception var3) {
         LOG.error("------------->/groupLevel/save 接口异常" + var3.getMessage());
         return this.errorHandler("组织层级配置保存异常:" + var3.getMessage());
      }
   }

   @RequestMapping(
      value = {"/delete"},
      method = {RequestMethod.POST}
   )
   @ApiOperation(
      value = "删除组织层级配置",
      notes = "返回json"
   )
   @ResponseBody
   public Object delete(String levelId) {
      LOG.info("------------->/groupLevel/delete 组织层级配置删除");

      try {
         this.groupService.deleteGroupLevel(Integer.valueOf(Integer.parseInt(levelId)));
         return this.outBound("组织层级配置删除成功");
      } catch (Exception var3) {
         LOG.error("------------->/groupLevel/delete 接口异常" + var3.getMessage());
         return this.errorHandler("组织层级配置删除异常:" + var3.getMessage());
      }
   }
}
