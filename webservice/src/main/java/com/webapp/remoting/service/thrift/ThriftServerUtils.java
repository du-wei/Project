package com.webapp.remoting.service.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.TSimpleJSONProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

public class ThriftServerUtils {

	//Transport	传输层
	//Protocol	协议层
	//Processors处理器

	enum TServerTrans{
		TServerSocket, TNonblockingServerSocket;
	}
	enum TProtocols{
		TBinary, TCompact, TJSON, TSimpleJSON;
	}
	enum TServers{
		TSimpleServer, TThreadPoolServer, TThreadedSelectorServer, TNonblockingServer;
	}

	//TServerTransport
	public static TServerTransport getTServerTrans(int port, TServerTrans tServerTrans) {
		TServerTransport trans = null;
		try {
			if(tServerTrans == TServerTrans.TServerSocket){
				trans = new TServerSocket(port);
			}else if(tServerTrans == TServerTrans.TNonblockingServerSocket){
				trans = new TNonblockingServerSocket(port);
			}
		} catch (TTransportException e) {
			e.printStackTrace();
		}
		return trans;
	}

    //TProtocolFactory
	public static TProtocolFactory getProtFty(TProtocols tProtocol) {
		TProtocolFactory tProtFty = null;
		if (tProtocol == TProtocols.TCompact) {
			tProtFty = new TCompactProtocol.Factory();
		}else if (tProtocol == TProtocols.TJSON) {
			tProtFty = new TJSONProtocol.Factory();
		}else if (tProtocol == TProtocols.TSimpleJSON) {
			tProtFty = new TSimpleJSONProtocol.Factory();
		}else{
			tProtFty = new TBinaryProtocol.Factory(true, true);
		}
		return tProtFty;
	}


    //Server
	//单线程服务器端使用标准的阻塞式 I/O
	public static TServer getServer4TSimple(TProcessor processor, TServerTrans tServerTrans, int port, TProtocols tProtocol) {
	    TServerTransport serverSocket = getTServerTrans(port, tServerTrans);
	    TSimpleServer.Args opts = new TSimpleServer.Args(serverSocket);
	    opts.processorFactory(new TProcessorFactory(processor));
	    opts.protocolFactory(getProtFty(tProtocol));
	    opts.transportFactory(new TTransportFactory());
	    TServer server = new TSimpleServer(opts);
	    return server;
	}

	//多线程服务器端使用标准的阻塞式 I/O
	public static TServer getServer4TThreadPool(TProcessor processor, TServerTrans tServerTrans, int port, TProtocols tProtocol) {
		TServerTransport serverSocket = getTServerTrans(port, tServerTrans);
	    TThreadPoolServer.Args opts = new TThreadPoolServer.Args(serverSocket);
	    opts.maxWorkerThreads(200);
	    opts.minWorkerThreads(8);

	    opts.processorFactory(new TProcessorFactory(processor));
	    opts.protocolFactory(getProtFty(tProtocol));
	    opts.transportFactory(new TTransportFactory());

	    TServer server = new TThreadPoolServer(opts);
	    return server;
	}

	//多线程服务器端使用非阻塞式 I/O
	public static TServer getServer4TThreadedSelector(TProcessor processor, int port, TProtocols tProtocol) {
		TServerTransport serverSocket = getTServerTrans(port, TServerTrans.TNonblockingServerSocket);
	    TThreadedSelectorServer.Args opts = new TThreadedSelectorServer.Args((TNonblockingServerTransport) serverSocket);

	    opts.processorFactory(new TProcessorFactory(processor));
	    opts.protocolFactory(getProtFty(tProtocol));
	    opts.transportFactory(new TFramedTransport.Factory());

	    TServer server = new TThreadedSelectorServer(opts);
	    return server;
	}

	//多线程服务器端使用非阻塞式 I/O
	public static TServer getServer4TTNonblocking(TProcessor processor, int port, TProtocols tProtocol) {
		TServerTransport serverSocket = getTServerTrans(port, TServerTrans.TNonblockingServerSocket);
		TNonblockingServer.Args opts = new TNonblockingServer.Args((TNonblockingServerTransport) serverSocket);

	    opts.processorFactory(new TProcessorFactory(processor));
	    opts.protocolFactory(getProtFty(tProtocol));
	    opts.transportFactory(new TFramedTransport.Factory());

	    TServer server = new TNonblockingServer(opts);
	    return server;
	}

}
