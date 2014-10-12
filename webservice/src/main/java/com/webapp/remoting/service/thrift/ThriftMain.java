package com.webapp.remoting.service.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import com.webapp.remoting.service.thrift.ThriftServerUtils.TProtocols;
import com.webapp.remoting.service.thrift.ThriftServerUtils.TServerTrans;
import com.webapp.remoting.service.thrift.ThriftService.Iface;
import com.webapp.remoting.service.thrift.ThriftService.Processor;

public class ThriftMain {
//	<property name="Pool.PingQuery" value="select 1 from redirect_url" />
//	  <property name="Pool.PingEnabled" value="true" />
//	  <property name="Pool.PingConnectionsOlderThan" value="0" />
//	  <property name="Pool.PingConnectionsNotUsedFor" value="3600000" />
	public static final String server_ip = "localhost";
	public static final int server_port = 8090;
	public static final int timeout = 30000;

	public static void main(String[] args) throws Exception {
        TProcessor processor = new Processor<Iface>(new ThriftServiceImpl());

//        TServer server = getServer4TSimple(processor);
//        TServer server = getServer4TThreadPool(processor);
//        TServer server = getServer4TThreadedSelector(processor);
        TServer server = getServer4TTNonblocking(processor);

        System.out.println("Starting the multi threads server...");
        server.serve();

    }

    public static TServer getServer4TSimple(TProcessor processor) {
		TServer server = ThriftServerUtils.getServer4TSimple(processor, TServerTrans.TServerSocket, server_port, TProtocols.TBinary);
        return server;
	}
    public static TServer getServer4TThreadPool(TProcessor processor) {
		TServer server = ThriftServerUtils.getServer4TThreadPool(processor, TServerTrans.TServerSocket, server_port, TProtocols.TBinary);
		return server;
	}
    public static TServer getServer4TThreadedSelector(TProcessor processor) {
		TServer server = ThriftServerUtils.getServer4TThreadedSelector(processor, server_port, TProtocols.TBinary);
		return server;
	}
    public static TServer getServer4TTNonblocking(TProcessor processor) {
		TServer server = ThriftServerUtils.getServer4TTNonblocking(processor, server_port, TProtocols.TCompact);
		return server;
	}

}
