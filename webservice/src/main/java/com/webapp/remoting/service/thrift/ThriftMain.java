package com.webapp.remoting.service.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;

import com.webapp.remoting.service.thrift.ThriftServerUtils.TProtocol;
import com.webapp.remoting.service.thrift.ThriftServerUtils.TServerTrans;
import com.webapp.remoting.service.thrift.ThriftService.Iface;
import com.webapp.remoting.service.thrift.ThriftService.Processor;

public class ThriftMain {

    public static void main(String[] args) throws Exception {
        //创建TProcessor
        TProcessor processor = new Processor<Iface>(new ThriftServiceImpl());

        TServer server = ThriftServerUtils.getServer4TThreadPool(processor, TServerTrans.TServerSocket, 100, TProtocol.TBinary);
        System.out.println("Starting the multi threads server...");
        server.serve();
    }

}
