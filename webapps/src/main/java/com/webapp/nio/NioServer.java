package com.webapp.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NioServer {

	private Charset charset = Charset.forName("utf-8");
	private Selector selector = null;

	public void init() throws Exception {
		selector = Selector.open();

		ServerSocketChannel server = ServerSocketChannel.open();
		server.configureBlocking(false);
		server.socket().bind(new InetSocketAddress(8080));
		server.register(selector, SelectionKey.OP_ACCEPT);

		while (selector.select() > 0) {
			for (SelectionKey key : selector.selectedKeys()) {
				selector.selectedKeys().remove(key);

				if (key.isAcceptable()) {
					// SocketChannel client = server.accept();
					SocketChannel client = ((ServerSocketChannel) key.channel())
					        .accept();
					client.configureBlocking(false);
					client.register(selector, SelectionKey.OP_READ);
					key.interestOps(SelectionKey.OP_ACCEPT);
					System.out.println("accept one");
				}
				if (key.isReadable()) {
					System.out.println("is readalbe");
					SocketChannel client = (SocketChannel) key.channel();
					ByteBuffer buf = ByteBuffer.allocate(1024);

					StringBuffer str = new StringBuffer();

					try {
						while (client.read(buf) > 0) {
							buf.flip();
							str.append(charset.decode(buf));
						}

						System.out.println(str.toString());
						key.interestOps(SelectionKey.OP_READ);
					} catch (Exception e) {
						key.cancel();
						if (key.channel() != null) {
							key.channel().close();
						}
					}
				}
			}
		}
	}
}
