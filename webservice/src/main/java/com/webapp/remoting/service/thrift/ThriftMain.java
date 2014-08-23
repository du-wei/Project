package com.webapp.remoting.service.thrift;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.TProcessor;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.Test;

import com.webapp.remoting.service.thrift.ThriftServerUtils.TProtocols;
import com.webapp.remoting.service.thrift.ThriftServerUtils.TServerTrans;
import com.webapp.remoting.service.thrift.ThriftService.AsyncClient.getAll_call;
import com.webapp.remoting.service.thrift.ThriftService.Iface;
import com.webapp.remoting.service.thrift.ThriftService.Processor;

public class ThriftMain {

	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 8090;
	public static final int TIMEOUT = 30000;

	public static void main(String[] args) throws Exception {
        TProcessor processor = new Processor<Iface>(new ThriftServiceImpl());

//        TServer server = getServer4TSimple(processor);
//        TServer server = getServer4TThreadPool(processor);
//        TServer server = getServer4TThreadedSelector(processor);
        TServer server = getServer4TTNonblocking(processor);

        System.out.println("Starting the multi threads server...");
        server.serve();
    }

	@Test
	public void startClient() throws Exception{
        startClient(new TSocket("localhost", 1234));
	}

	@Test
	public void startFramedClient() throws Exception{
        startClient(new TFramedTransport(new TSocket("localhost", 1234, TIMEOUT)));
	}

	@Test
	public void startNonblockingSocketClient() throws Exception{
		TAsyncClientManager clientManager = new TAsyncClientManager();
		TNonblockingTransport transport = new TNonblockingSocket(SERVER_IP,SERVER_PORT, TIMEOUT);

		TProtocolFactory tprotocol = new TCompactProtocol.Factory();
		ThriftService.AsyncClient asyncClient = new ThriftService.AsyncClient(
		        tprotocol, clientManager, transport);
		System.out.println("Client start .....");

		CountDownLatch latch = new CountDownLatch(1);
		AsynCallback callBack = new AsynCallback(latch);
		System.out.println("call method sayHello start ...");
		asyncClient.getAll("ffff", callBack);
		System.out.println("call method sayHello .... end");
		boolean wait = latch.await(30, TimeUnit.SECONDS);
		System.out.println("latch.await =:" + wait);
	}
	public class AsynCallback implements AsyncMethodCallback<getAll_call> {
	    private CountDownLatch latch;

	    public AsynCallback(CountDownLatch latch) {
	        this.latch = latch;
	    }

	    @Override
	    public void onComplete(getAll_call response) {
	        System.out.println("onComplete");
	        try {
	            // Thread.sleep(1000L * 1);
	            System.out.println("AsynCall result =:"
	                    + response.getResult().toString());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            latch.countDown();
	        }
	    }

	    @Override
	    public void onError(Exception exception) {
	        System.out.println("onError :" + exception.getMessage());
	        latch.countDown();
	    }
	}

	private void startClient(TTransport transport) throws Exception {
        TProtocol protocol = new TBinaryProtocol(transport);
        ThriftService.Client client = new ThriftService.Client(protocol);
        transport.open();
        String all = client.getAll("hello");
        System.out.println(all);
        transport.close();
	}

    public static TServer getServer4TSimple(TProcessor processor) {
		TServer server = ThriftServerUtils.getServer4TSimple(processor, TServerTrans.TServerSocket, 100, TProtocols.TBinary);
        return server;
	}
    public static TServer getServer4TThreadPool(TProcessor processor) {
		TServer server = ThriftServerUtils.getServer4TThreadPool(processor, TServerTrans.TServerSocket, 100, TProtocols.TBinary);
		return server;
	}
    public static TServer getServer4TThreadedSelector(TProcessor processor) {
		TServer server = ThriftServerUtils.getServer4TThreadedSelector(processor, 100, TProtocols.TBinary);
		return server;
	}
    public static TServer getServer4TTNonblocking(TProcessor processor) {
		TServer server = ThriftServerUtils.getServer4TTNonblocking(processor, 100, TProtocols.TBinary);
		return server;
	}

}
