package com.webapp.remoting.service.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;

import com.webapp.remoting.service.thrift.ThriftService.Iface;
import com.webapp.remoting.service.thrift.ThriftService.Processor;

public class ThriftMain {

    public static void main(String[] args) throws Exception {
        //创建TProcessor
        TProcessor processor = new Processor<Iface>(new ThriftServiceImpl());

        //创建TServerTransport
        TServerTransport serverSocket = ThriftServerUtils.getTrans4TServerSocket(1234);

        Args args2 = new Args(serverSocket);
        args2.processor(processor);
        args2.protocolFactory(ThriftServerUtils.getProtocolFactory4TBinary());

        //创建TServer
        TServer server = ThriftServerUtils.getServer4TThreadPool(args2);
        System.out.println("Starting the multi threads server...");
        server.serve();
    }

    private void ok() throws Exception {
    	//协议
    	TServerTransport serverTransport = new TServerSocket(111);

    	//处理层
    	TProcessor processor = new Processor<Iface>(new ThriftServiceImpl());

    	TProtocolFactory factory = new TBinaryProtocol.Factory(true, true);

    	TThreadPoolServer.Args args = new Args(serverTransport);
//    	args.inputProtocolFactory(factory);
//    	args.inputTransportFactory(factory)
//    	args.outputProtocolFactory(factory)
//    	args.outputTransportFactory(factory)
//    	args.maxWorkerThreads(n)
//    	args.minWorkerThreads(n);
    	args.processor(processor);
    	args.processorFactory(new TProcessorFactory(processor));
    	args.protocolFactory(new TCompactProtocol.Factory());
    	args.transportFactory(new TTransportFactory());

    	TServer server = new TThreadPoolServer(args);
    	server.serve();

    }

    private void jj() throws Exception {
    	/* ---------服务类型----------- */
    	//单线程服务器端使用标准的阻塞式 I/O
    	TServer server = new TSimpleServer(null);
    	//多线程服务器端使用标准的阻塞式 I/O
    	TServer server2 = new TThreadPoolServer(null);
    	TServer server4 = new TThreadedSelectorServer(null);
    	//多线程服务器端使用非阻塞式 I/O
    	TServer server3 = new TNonblockingServer(null);


    	/* ---------服务器传输类型----------- */
    	//阻塞式I/O传输
    	TServerTransport serverTransport = new TServerSocket(111);
    	//非阻塞方式I/O传输
    	TServerTransport serverTransport2 = new TNonblockingServerSocket(11);


    	/* ---------传输类型----------- */
    	//TSocket			------>阻塞式I/O传输
    	//TFramedTransport	------>非阻塞方式I/O传输
    	//TNonblockingSocket------>用于构建异步客户端
    	//TMemoryTransport	------>使用内存I/O

    	/* ---------传输协议层----------- */
    	//TBinaryProtocol.Factory	------>二进制编码
    	//TCompactProtocol.Factory	------>高效率的、密集的二进制编码
    	//TJSONProtocol.Factory		------>JSON 的数据编码协议
    	//TSimpleJSONProtocol.Factory------>提供JSON只写的协议
    }

}
