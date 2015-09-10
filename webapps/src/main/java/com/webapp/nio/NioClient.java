package com.webapp.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NioClient {
	private Charset charset = Charset.forName("utf-8");
	private Selector selector = null;
	private SocketChannel client = null;

	public void init() throws Exception {
		selector = Selector.open();
		client = SocketChannel.open(new InetSocketAddress(8080));
		client.configureBlocking(false);
		client.register(selector, SelectionKey.OP_READ);

		new ClientThread().start();

		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			client.write(charset.encode(line));
		}
	}

	private class ClientThread extends Thread {
		@Override
		public void run() {
			try {
				while (selector.select() > 0) {
					for (SelectionKey key : selector.selectedKeys()) {
						selector.selectedKeys().remove(key);
						if (key.isReadable()) {
							SocketChannel sc = (SocketChannel) key.channel();
							ByteBuffer buf = ByteBuffer.allocate(1024);
							StringBuffer str = new StringBuffer();
							while (sc.read(buf) > 0) {
								sc.read(buf);
								buf.flip();
								str.append(charset.decode(buf));
							}
							System.out.println(str.toString());
							key.interestOps(SelectionKey.OP_READ);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
