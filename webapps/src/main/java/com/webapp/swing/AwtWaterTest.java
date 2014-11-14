package com.webapp.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;

import org.junit.Test;

public class AwtWaterTest extends HttpServlet {

	private static long serialVersionUID = 1L;

	@Test
	public void TestImageIo() {
		String[] readSuf = ImageIO.getReaderFileSuffixes();
		System.out.print("所有能读的图形文件的文件后缀：");
		for (String temp : readSuf) {
			System.out.print(temp + "-");
		}

		String[] readName = ImageIO.getReaderFormatNames();
		System.out.print("\n所有能读的图形文件的非正式文件名称：");
		for (String temp : readName) {
			System.out.print(temp + "-");
		}

		String[] writeSuf = ImageIO.getWriterFileSuffixes();
		System.out.print("\n所有能写的图形文件的文件后缀：");
		for (String temp : writeSuf) {
			System.out.print(temp + "-");
		}

		String[] writeName = ImageIO.getWriterFormatNames();
		System.out.print("\n所有能写的图形文件的非正式文件名称：");
		for (String temp : writeName) {
			System.out.print(temp + "-");
		}
	}

	@Test
	public void TestImageMix() throws Exception {
		String root = System.getProperty("user.dir");
		BufferedImage image = ImageIO.read(new File(root
		        + "/WebRoot/images/img_bg.png"));
		Graphics grap = image.getGraphics();
		Image star = ImageIO.read(new File(root
		        + "/WebRoot/images/img_star.png"));
		grap.drawImage(star, 90, 100, 15, 15, null); // 乌鲁木齐
		// grap.drawImage(star, 180, 170, 15, 15, null);//西宁
		// grap.drawImage(star, 90, 210, 15, 15, null);//拉萨
		// grap.drawImage(star, 235, 205, 15, 15, null);//兰州
		// grap.drawImage(star, 200, 230, 15, 15, null);//成都
		// grap.drawImage(star, 245, 175, 15, 15, null);//银川
		// grap.drawImage(star, 195, 300, 15, 15, null);//昆明
		// grap.drawImage(star, 262, 268, 15, 15, null);//贵阳
		// grap.drawImage(star, 260, 240, 15, 15, null);//重庆
		// grap.drawImage(star, 255, 302, 15, 15, null);//南宁
		// grap.drawImage(star, 286, 128, 15, 15, null);//呼和浩特
		// grap.drawImage(star, 288, 256, 15, 15, null);//长沙
		// grap.drawImage(star, 290, 228, 15, 15, null);//武汉
		// grap.drawImage(star, 275, 185, 15, 15, null);//西安
		// grap.drawImage(star, 296, 153, 15, 15, null);//太原
		// grap.drawImage(star, 328, 138, 15, 15, null);//北京
		// grap.drawImage(star, 340, 250, 15, 15, null);//南昌
		// grap.drawImage(star, 293, 198, 15, 15, null);//郑州
		// grap.drawImage(star, 362, 165, 15, 15, null);//济南
		// grap.drawImage(star, 362, 192, 15, 15, null);//南京
		// grap.drawImage(star, 337, 206, 15, 15, null);//合肥
		// grap.drawImage(star, 380, 250, 15, 15, null);//杭州
		// grap.drawImage(star, 350, 270, 15, 15, null);//福州
		// grap.drawImage(star, 325, 330, 15, 15, null);//香港
		// grap.drawImage(star, 340, 315, 15, 15, null);//深圳
		// grap.drawImage(star, 308, 292, 15, 15, null);//广州
		// grap.drawImage(star, 388, 212, 15, 15, null);//上海
		// grap.drawImage(star, 380, 82, 15, 15, null);//长春
		// grap.drawImage(star, 385, 115, 15, 15, null);//沈阳
		// grap.drawImage(star, 400, 45, 15, 15, null);//哈尔滨
		// grap.drawImage(star, 315, 146, 15, 15, null);//石家庄
		grap.dispose();

		ImageIO.write(image, "png", new File(root
		        + "/WebRoot/images/img_mix.png"));
	}

	@Test
	public void TestImageStr() throws Exception {
		String root = System.getProperty("user.dir");
		BufferedImage image = ImageIO.read(new File(root
		        + "/WebRoot/images/image.png"));
		Graphics g = image.createGraphics();
		g.setColor(Color.RED);
		g.setFont(new Font("宋体", Font.PLAIN, 20));
		g.drawString("hello world", image.getWidth() - 150,
		        image.getHeight() - 10);
		ImageIO.write(image, "png", new File(root
		        + "/WebRoot/images/ImageStr.png"));
	}

}
