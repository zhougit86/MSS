package com.meicloud.webservice;

import com.meicloud.webservice.MetadataInterface;
import java.net.URL;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public interface MetadataService extends Service {

   String getMetadataAddress();

   MetadataInterface getMetadata() throws ServiceException;

   MetadataInterface getMetadata(URL var1) throws ServiceException;
}
