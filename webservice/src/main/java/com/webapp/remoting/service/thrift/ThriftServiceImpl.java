package com.webapp.remoting.service.thrift;

import org.apache.thrift.TException;

public class ThriftServiceImpl implements ThriftService.Iface {

    @Override
    public String getAll(String para) throws TException {
        System.out.println(para);
        return "responce - "+para;
    }

}
