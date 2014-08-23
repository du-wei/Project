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
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class ThriftServerUtils {

	//Processor
	public static TProcessorFactory getProcessorFactory(TProcessor processor) {
    	return new TProcessorFactory(processor);
    }

	//Transport
	public static TServerTransport getTrans4TServerSocket(int port) {
	    try {
	        return new TServerSocket(port);
        } catch (TTransportException e) {
	        e.printStackTrace();
        }
	    return null;
    }
    public static TServerTransport getTrans4TNonblocking(int port) {
    	try {
	        return new TNonblockingServerSocket(port);
        } catch (TTransportException e) {
	        e.printStackTrace();
        }
    	return null;
    }

    //TProtocolFactory
    public static TProtocolFactory getProtocolFactory4TBinary(){
    	return new TBinaryProtocol.Factory(true, true);
    }
    public static TProtocolFactory getProtocolFactory4TCompact(){
    	return new TCompactProtocol.Factory();
    }
    public static TProtocolFactory getProtocolFactory4TJSON(){
    	return new TJSONProtocol.Factory();
    }
    public static TProtocolFactory getProtocolFactory4TSimpleJSON(){
    	return new TSimpleJSONProtocol.Factory();
    }

    //Server
    public static TServer getServer4TSimple(TSimpleServer.Args args) {
	    return new TSimpleServer(args);
    }
    public static TServer getServer4TThreadPool(TThreadPoolServer.Args args) {
	    return new TThreadPoolServer(args);
    }
    public static TServer getServer4TThreadedSelector(TThreadedSelectorServer.Args args) {
	    return new TThreadedSelectorServer(args);
    }
    public static TServer getServer4TTNonblocking(TNonblockingServer.Args args) {
	    return new TNonblockingServer(args);
    }

}
