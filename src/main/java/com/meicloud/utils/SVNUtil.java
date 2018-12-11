//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.meicloud.utils;

import com.meicloud.model.Queue;
import com.meicloud.utils.PropertiesUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNFileRevision;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNInfo;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SVNUtil {
    private static Logger LOG = LoggerFactory.getLogger(SVNUtil.class);
    private static String url = null;
    private static String username = null;
    private static String password = null;
    private static SVNRepository repository = null;
    private static StringBuilder paths = new StringBuilder();
    private static Map<String, Long> startRevisionMap = new HashMap();
    private static String dist = null;

    static {
        try {
            DAVRepositoryFactory.setup();
            SVNRepositoryFactoryImpl.setup();
            FSRepositoryFactory.setup();
            Properties e = PropertiesUtil.getPropertiesByFileName("svn.properties");
            if(e != null) {
                url = e.getProperty("project.svn.url");
                dist = PropertiesUtil.getEtlFilePathServerPrefix();
                username = e.getProperty("project.svn.username");
                password = e.getProperty("project.svn.password");
            }

            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);
            repository.setAuthenticationManager(authManager);
        } catch (Exception var2) {
            LOG.error(var2.getMessage());
        }

    }

    public SVNUtil() {
    }

    public static String getDist() {
        return dist;
    }

    public static void setDist(String dist) {
        SVNUtil.dist = dist;
    }

    public static SVNClientManager getSVNClientManager(String svnUserName, String svnPassWord) throws Exception {
        SVNClientManager ourClientManager = null;

        try {
            DefaultSVNOptions e = SVNWCUtil.createDefaultOptions(true);
            ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions)e, svnUserName, svnPassWord);
            return ourClientManager;
        } catch (Exception var4) {
            LOG.error(var4.getMessage());
            throw var4;
        }
    }

    public static Long doCheckOut(String svnPath, String newPath, SVNClientManager svnClientManager) throws Exception {
        LOG.info(svnPath + "------------->>>>>>>>>>>>>checkOut start!");
        File wcDir = new File(newPath);
        if(!wcDir.exists()) {
            wcDir.mkdirs();
        }

        try {
            SVNURL e = SVNURL.parseURIEncoded(svnPath);
            SVNUpdateClient updateClient = svnClientManager.getUpdateClient();
            updateClient.setIgnoreExternals(false);
            Long counts = Long.valueOf(updateClient.doCheckout(e, wcDir, SVNRevision.HEAD, SVNRevision.HEAD, true));
            LOG.info(svnPath + "------------->>>>>>>>>>>>>checkOut sucesss!");
            return counts;
        } catch (Exception var7) {
            LOG.error(svnPath + "------------->>>>>>>>>>>>>checkOut fail:" + var7.getMessage());
            throw var7;
        }
    }

    public static Long doUpdate(String updatePath, SVNClientManager svnClientManager) throws Exception {
        LOG.info(updatePath + "---->svnUpdate start!");
        File updateFile = new File(updatePath);
        if(!updateFile.exists()) {
            LOG.error(updatePath + "----> path is not exist!");
            return Long.valueOf(0L);
        } else {
            try {
                svnClientManager.getWCClient().doCleanup(updateFile);
                SVNUpdateClient e = svnClientManager.getUpdateClient();
                e.setIgnoreExternals(false);
                Long counts = Long.valueOf(e.doUpdate(updateFile, SVNRevision.HEAD, SVNDepth.INFINITY, false, false));
                LOG.info(updatePath + "---->svnUpdate sucesss!");
                return counts;
            } catch (Exception var5) {
                LOG.error(var5.getMessage());
                LOG.error(updatePath + "---->svnUpdate fail:" + var5.getMessage());
                throw var5;
            }
        }
    }

    public static Long getLastVersionNo(String svnUrl, SVNClientManager svnClientManager) throws Exception {
        Long version = Long.valueOf(0L);

        try {
            LOG.info(svnUrl + "---->在该路径上获取最新的svn版本号!");
            SVNURL e = SVNURL.parseURIEncoded(svnUrl);
            SVNInfo info = svnClientManager.getWCClient().doInfo(e, SVNRevision.HEAD, SVNRevision.HEAD);
            version = Long.valueOf(info.getCommittedRevision().getNumber());
            LOG.info(svnUrl + "---->该路径的版本号为:" + version);
            return version;
        } catch (Exception var5) {
            LOG.error(var5.getMessage());
            throw var5;
        }
    }

    private static SVNRepository getSVNRepository(Queue queue) throws Exception {
        SVNRepository repository = null;

        try {
            DAVRepositoryFactory.setup();
            SVNRepositoryFactoryImpl.setup();
            FSRepositoryFactory.setup();
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(queue.getSvnUrl()));
            ISVNAuthenticationManager e = SVNWCUtil.createDefaultAuthenticationManager(queue.getSvnUserName(), queue.getSvnPassWord());
            repository.setAuthenticationManager(e);
            return repository;
        } catch (Exception var3) {
            LOG.error(var3.getMessage());
            throw var3;
        }
    }

    public static Collection getLogEntries(Queue queue) throws Exception {
        long endRevision = -1L;

        try {
            SVNRepository e = getSVNRepository(queue);

            try {
                endRevision = e.getLatestRevision();
            } catch (SVNException var7) {
                LOG.error("error while fetching the latest repository revision: " + var7.getMessage(), var7);
            }

            Collection logEntries = null;

            try {
                if(!startRevisionMap.containsKey(queue.getQueueId())) {
                    startRevisionMap.put(queue.getQueueId(), Long.valueOf(0L));
                }

                logEntries = e.log(new String[]{""}, (Collection)null, ((Long)startRevisionMap.get(queue.getQueueId())).longValue(), endRevision, true, true);
            } catch (SVNException var6) {
                LOG.error("error while collecting log information for \'" + queue.getSvnUrl() + "\': " + var6.getMessage(), var6);
            }

            return logEntries;
        } catch (Exception var8) {
            LOG.error("error : " + var8.getMessage(), var8);
            return null;
        }
    }

    public static void listEntries(String path, SVNRepository repository) {
        try {
            Collection e = repository.getDir(path, -1L, (SVNProperties)null, (Collection)null);
            Iterator iterator = e.iterator();

            while(iterator.hasNext()) {
                SVNDirEntry entry = (SVNDirEntry)iterator.next();
                paths.append((path.equals("")?"":path + "/") + entry.getName());
                paths.append("#");
                if(entry.getKind() == SVNNodeKind.DIR) {
                    listEntries(path.equals("")?entry.getName():path + "/" + entry.getName(), repository);
                }
            }
        } catch (Exception var5) {
            LOG.error(var5.getMessage());
        }

    }

    public static String getVersionMsg(String SVNFile, Queue queue) {
        final StringBuffer tmp = new StringBuffer();

        try {
            SVNRepository e = getSVNRepository(queue);
            final SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
            ArrayList revisionList = new ArrayList();
            List list = (List)e.getFileRevisions(SVNFile, (Collection)null, -1L, e.getLatestRevision());
            Iterator var8 = list.iterator();

            while(var8.hasNext()) {
                SVNFileRevision endRevision = (SVNFileRevision)var8.next();
                revisionList.add(Long.valueOf(endRevision.getRevision()));
            }

            long endRevision1 = ((Long)Collections.max(revisionList)).longValue();
            startRevisionMap.put(queue.getQueueId(), Long.valueOf(endRevision1));
            boolean changedPath = false;
            boolean strictNode = true;
            String[] targetPaths = new String[]{SVNFile};
            e.log(targetPaths, ((Long)startRevisionMap.get(queue.getQueueId())).longValue(), endRevision1, changedPath, strictNode, new ISVNLogEntryHandler() {
                public void handleLogEntry(SVNLogEntry logEntry) throws SVNException {
                    tmp.append("提交者：" + logEntry.getAuthor() + "<br />");
                    tmp.append("版本号：" + logEntry.getRevision() + "<br />");
                    tmp.append("提交时间：" + sdf.format(logEntry.getDate()));
                }
            });
        } catch (Exception var12) {
            LOG.error(var12.getMessage());
        }

        return tmp.toString();
    }

    public static String getRootPath(Queue queue) {
        try {
            SVNRepository e = getSVNRepository(queue);
            String dir = queue.getSvnUrl().replace(e.getRepositoryRoot(true).toString(), "");
            paths.setLength(0);
            listEntries(dir, e);
        } catch (SVNException var3) {
            LOG.error(var3.getMessage());
        } catch (Exception var4) {
            LOG.error(var4.getMessage());
        }

        return paths.toString();
    }

    public static Long getLatestRevisionByFile(String file, Queue queue) {
        try {
            SVNRepository e = getSVNRepository(queue);
            ArrayList revisionList = new ArrayList();
            List list = (List)e.getFileRevisions(file, (Collection)null, -1L, e.getLatestRevision());
            Iterator var6 = list.iterator();

            while(var6.hasNext()) {
                SVNFileRevision revision = (SVNFileRevision)var6.next();
                revisionList.add(Long.valueOf(revision.getRevision()));
            }

            return (Long)Collections.max(revisionList);
        } catch (SVNException var7) {
            LOG.error(var7.getMessage());
        } catch (Exception var8) {
            LOG.error(var8.getMessage());
        }

        return null;
    }

    public static synchronized String export(String localDir, String svnFile, Queue queue) throws Exception {
        String toFile = localDir + svnFile;

        try {
            SVNRepository e = getSVNRepository(queue);
            LOG.info("start to export file [" + svnFile + "] to tomcat server dir [" + localDir + "]  from svn");
            DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
            ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(queue.getQueueName(), queue.getSvnPassWord());
            SVNClientManager clientManager = SVNClientManager.newInstance((DefaultSVNOptions)options, authManager);
            File file = new File(localDir + svnFile);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            clientManager.getUpdateClient().doExport(SVNURL.parseURIEncoded(e.getRepositoryRoot(true) + svnFile), file, SVNRevision.HEAD, SVNRevision.HEAD, "native", true, SVNDepth.INFINITY);
            LOG.info("success to export file [" + svnFile + "] to tomcat server dir [" + localDir + "]  from svn");
            return toFile;
        } catch (Exception var9) {
            throw var9;
        }
    }

    public static void deleteFile(File path) {
        try {
            if(!path.exists()) {
                return;
            }

            if(path.isDirectory()) {
                File[] e = path.listFiles();
                File[] var5 = e;
                int var4 = e.length;

                for(int var3 = 0; var3 < var4; ++var3) {
                    File file = var5[var3];
                    deleteFile(file);
                }
            }

            if(path.delete()) {
                LOG.info("删除成功");
            } else {
                LOG.info("删除失败");
            }
        } catch (Exception var6) {
            LOG.error(var6.getMessage());
        }

    }

    public static void deleteDir(File path) {
        if(path.exists()) {
            if(path.isFile()) {
                if(path.delete()) {
                    LOG.info("删除文件成功");
                } else {
                    LOG.info("删除文件失败");
                }

            } else {
                File[] files = path.listFiles();

                for(int i = 0; i < files.length; ++i) {
                    deleteDir(files[i]);
                }

                if(path.delete()) {
                    LOG.info("删除目录成功");
                } else {
                    LOG.info("删除目录失败");
                }

            }
        }
    }
}
