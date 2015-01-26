package com.webapp.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.junit.Test;

public class NioTest {

	// @Test
	public void testFileLock() throws Exception {
		File file = new File("d:" + File.separator + "out.txt");
		FileOutputStream output = null;
		output = new FileOutputStream(file, true);
		FileChannel fout = null;
		fout = output.getChannel();// 得到通道
		FileLock lock = fout.tryLock(); // 进行独占锁的操作

		if (lock != null) {
			System.out.println(file.getName() + "文件锁定300秒");
			Thread.sleep(300000);
			lock.release(); // 释放
			System.out.println(file.getName() + "文件解除锁定。");
		}
		fout.close();
		output.close();
	}

	// @Test
	public void testMappedByteBuf() throws Exception {
		File file = new File("d:" + File.separator + "mldn.txt");
		FileInputStream input = null;
		input = new FileInputStream(file);
		FileChannel fin = null; // 定义输入的通道
		fin = input.getChannel(); // 得到输入的通道
		MappedByteBuffer mbb = null;
		mbb = fin.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		byte data[] = new byte[(int) file.length()]; // 开辟空间接收内容
		int foot = 0;
		while (mbb.hasRemaining()) {
			data[foot++] = mbb.get(); // 读取数据
		}
		System.out.println(new String(data)); // 输出内容
		fin.close();
		input.close();
	}

	// @Test
	public void testChannel() throws Exception {
		File file1 = new File("d:" + File.separator + "note.txt");
		File file2 = new File("d:" + File.separator + "outnote.txt");
		FileInputStream input = null;
		FileOutputStream output = null;
		output = new FileOutputStream(file2);
		input = new FileInputStream(file1);
		FileChannel fout = null; // 声明FileChannel对象
		FileChannel fin = null; // 定义输入的通道
		fout = output.getChannel(); // 得到输出的通道
		fin = input.getChannel(); // 得到输入的通道
		ByteBuffer buf = ByteBuffer.allocate(1024);

		int temp = 0;
		while ((temp = fin.read(buf)) != -1) {
			buf.flip();
			fout.write(buf);
			buf.clear(); // 清空缓冲区,所有的状态变量的位置恢复到原点
		}
		fin.close();
		fout.close();
		input.close();
		output.close();

	}

	@Test
	public void testSubBuf() {
		IntBuffer buf = IntBuffer.allocate(10); // 准备出10个大小的缓冲区
		IntBuffer sub = null; // 定义子缓冲区
		for (int i = 0; i < 10; i++) {
			buf.put(2 * i + 1); // 在主缓冲区中加入10个奇数
		}
		// 需要通过slice() 创建子缓冲区
		buf.position(2);
		buf.limit(6);
		sub = buf.slice();

		for (int i = 0; i < sub.capacity(); i++) {
			int temp = sub.get(i);
			sub.put(temp - 1);
		}
		p("position = " + buf.position() + "，limit = " + buf.limit()
		        + "，capacty = " + buf.capacity());
		buf.flip(); // 重设缓冲区
		buf.limit(buf.capacity());
		System.out.print("主缓冲区中的内容：");
		iterate(buf);

	}

	// @Test
	public void testIntBuf() {
		IntBuffer buf = IntBuffer.allocate(10);
		p("1、写入数据之前的position、limit和capacity：");
		p("position = " + buf.position() + "，limit = " + buf.limit()
		        + "，capacty = " + buf.capacity());

		int temp[] =
		{ 5, 7, 9 };// 定义一个int数组
		buf.put(3); // 设置一个数据
		buf.put(temp); // 此时已经存放了四个记录

		p("2、写入数据之后的position、limit和capacity：");
		p("position = " + buf.position() + "，limit = " + buf.limit()
		        + "，capacty = " + buf.capacity());

		buf.flip(); // 重设缓冲区
		buf.flip();
		p("3、准备输出数据时 buf.flip() position、limit和capacity：");
		p("position = " + buf.position() + "，limit = " + buf.limit()
		        + "，capacty = " + buf.capacity());

		buf.rewind();
		p("position = " + buf.position() + "，limit = " + buf.limit()
		        + "，capacty = " + buf.capacity());

		System.out.print("缓冲区中的内容：");
		while (buf.hasRemaining()) {
			int x = buf.get();
			System.out.print(x + "、");
		}

		buf.clear();
		p("\n4、准备写入数据时 buf.clear() position、limit和capacity：");
		p("position = " + buf.position() + "，limit = " + buf.limit()
		        + "，capacty = " + buf.capacity());

	}

	public void iterate(Buffer buf) {
		while (buf.hasRemaining()) {
			int x = ((IntBuffer) buf).get();
			System.out.print(x + "、");
		}
	}

	public void p(Object obj) {
		System.out.println(obj);
	}

}
