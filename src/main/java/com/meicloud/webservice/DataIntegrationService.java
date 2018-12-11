package com.meicloud.webservice;

import com.meicloud.webservice.DataIntegrationInterface;
import java.net.URL;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public interface DataIntegrationService extends Service {

   String getDataIntegrationAddress();

   DataIntegrationInterface getDataIntegration() throws ServiceException;

   DataIntegrationInterface getDataIntegration(URL var1) throws ServiceException;
}
