package com.webapp.remoting.service.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {
    
    public static void main(String[] args) throws Exception {
        
        TTransport transport = new TSocket("localhost", 1234);
        TProtocol protocol = new TBinaryProtocol(transport);
        
        ThriftService.Client client = new ThriftService.Client(protocol);
        
        transport.open();
        
        String all = client.getAll("hello");
        System.out.println(all);
        
        transport.close();
        
    }
    
}
