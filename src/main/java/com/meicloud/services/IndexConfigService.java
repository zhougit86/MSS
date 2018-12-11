package com.meicloud.services;

import com.meicloud.model.Server;
import java.util.List;

public interface IndexConfigService {

   List queryCmServerList() throws Exception;

   Server queryCmServer(int var1) throws Exception;
}
