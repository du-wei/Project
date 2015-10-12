package com.webapp.remoting.service.thrift;

import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.webapp.remoting.service.thrift.ThriftService.AsyncClient.getAll_call;

public class ThriftTest {

	public static final String server_ip = "localhost";
	public static final int server_port = 8090;
	public static final int timeout = 30000;

	public static void main(String[] args) throws Exception {
		ThriftTest test = new ThriftTest();
		test.startNonblockingSocketClient();
	}

	public void startClient() throws Exception{
        startClient(new TSocket(server_ip, server_port));
	}

	public void startFramedClient() throws Exception{
        startClient(new TFramedTransport(new TSocket(server_ip, server_port, timeout)));
	}

	public void startNonblockingSocketClient() throws Exception{
		TAsyncClientManager clientManager = new TAsyncClientManager();
		TNonblockingTransport transport = new TNonblockingSocket(server_ip,server_port, timeout);

		ThriftService.AsyncClient asyncClient = new ThriftService.AsyncClient(new TCompactProtocol.Factory(), clientManager, transport);
//		CountDownLatch latch = new CountDownLatch(1);
		AsynCallback callBack = new AsynCallback();
		System.out.println("call method sayHello start ...");
		asyncClient.getAll("ffff", callBack);
		System.out.println("call method sayHello .... end");
//		boolean wait = latch.await(30, TimeUnit.SECONDS);
//		System.out.println("latch.await =:" + wait);
	}
	public class AsynCallback implements AsyncMethodCallback<getAll_call> {
//	    private CountDownLatch latch;

//	    public AsynCallback(CountDownLatch latch) {
//	        this.latch = latch;
//	    }

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
//	            latch.countDown();
	        }
	    }

	    @Override
	    public void onError(Exception exception) {
	        System.out.println("onError :" + exception.getMessage());
//	        latch.countDown();
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
}
