package com.meicloud.services;

import com.meicloud.dao.GroupMapper;
import com.meicloud.dao.TagsMapper;
import com.meicloud.model.Group;
import com.meicloud.model.Tags;
import com.meicloud.services.TagsService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tagsService")
public class TagsServiceImpl implements TagsService {

   private static Logger LOG = Logger.getLogger(TagsServiceImpl.class);
   @Autowired
   private TagsMapper tagsMappper;
   @Autowired
   private GroupMapper groupMapper;


   public boolean add(int groupId, String tagName, int tagNum) throws Exception {
      try {
         Group e = this.groupMapper.getById(groupId);
         Tags ts = new Tags();
         ts.setTagName(tagName);
         ts.setQueueId(e != null?e.getQueueId():null);
         Tags tagDB = this.tagsMappper.getByName(ts);
         Tags t = new Tags();
         t.setGroupId(groupId);
         if(tagDB != null) {
            t.setTagId(tagDB.getTagId());
         } else {
            Tags refered = new Tags();
            refered.setTagName(tagName);
            refered.setTagNum(0);
            refered.setQueueId(ts.getQueueId());
            this.tagsMappper.addTag(refered);
            t.setTagId(refered.getTagId());
         }

         boolean refered1 = this.tagsMappper.countRefer(t) > 0;
         if(!refered1) {
            this.tagsMappper.addTagRefer(t);
         }

         return true;
      } catch (Exception var9) {
         throw var9;
      }
   }

   public boolean delete(int tid) throws Exception {
      try {
         this.tagsMappper.delete(tid);
         return true;
      } catch (Exception var3) {
         throw var3;
      }
   }

   public List list() {
      try {
         return this.tagsMappper.list();
      } catch (Exception var2) {
         LOG.error(var2);
         return null;
      }
   }

   public List findByqueueIds(List queueIds) {
      return this.tagsMappper.findByqueueIds(queueIds);
   }

   public Tags getByName(Tags tags) {
      return this.tagsMappper.getByName(tags);
   }

   public List getByNameAndQueueId(Tags tags) {
      return this.tagsMappper.getByNameAndQueueId(tags);
   }
}
