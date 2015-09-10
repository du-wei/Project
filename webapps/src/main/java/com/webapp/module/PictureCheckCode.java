package com.webapp.module;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class PictureCheckCode extends ActionSupport {

	/** @Fields serialVersionUID : TODO 字段说明 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public PictureCheckCode() {
		this.request = ServletActionContext.getRequest();
		this.response = ServletActionContext.getResponse();
	}

	@Override
	public String execute() throws Exception {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		int width = 80;
		int height = 25;
		BufferedImage image = new BufferedImage(width, height,
		        BufferedImage.TYPE_INT_RGB);
		Graphics grap = image.getGraphics();
		grap.setColor(getRandomColor(100));
		grap.fillRect(0, 0, width, height);

		// 画一条折线
		Graphics2D grap2D = (Graphics2D) grap;
		grap2D.setStroke(new BasicStroke(1.5f));

		grap.setColor(Color.DARK_GRAY); // 设置当前颜色为预定义颜色中的深灰色
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];
		Random random = new Random();
		for (int j = 0; j < 3; j++) {
			xPoints[j] = random.nextInt(width - 1);
			yPoints[j] = random.nextInt(height - 1);
		}
		grap.drawPolyline(xPoints, yPoints, 3);

		// 生成并输出随机的验证文字
		Font font = new Font("华文宋体", Font.BOLD, 20);
		grap.setFont(font);
		int temp = 0;
		String checkCode = "";
		for (int i = 0; i < 4; i++) {
			if (random.nextInt(2) == 1) {
				temp = random.nextInt(26) + 65;
			} else {
				temp = random.nextInt(10) + 48;
			}
			// TODO: Prefer StringBuffer over += for concatenating strings
			checkCode += String.valueOf((char) temp);
			Color color = new Color(20 + random.nextInt(110),
			        20 + random.nextInt(110), 20 + random.nextInt(110));
			grap.setColor(color);
			// System.out.println(checkCode);
			// 将文字旋转指定角度
			AffineTransform trans = new AffineTransform();
			trans.rotate(random.nextInt(45) * 3.14 / 180, 15 * i + 10, 5);
			// 随机缩放文字
			float scaleSize = random.nextFloat() + 0.8f;
			if (scaleSize > 1.1f) scaleSize = 1f;
			trans.scale(scaleSize, scaleSize);
			grap2D.setTransform(trans);
			grap.drawString(String.valueOf((char) temp), 20 * i + 10, 14);
			System.out.println(checkCode);
		}
		grap.dispose();
		ImageIO.write(image, "jpeg", response.getOutputStream());
		return null;
	}

	// 得到小于255大于s的RGB颜色值
	private Color getRandomColor(int s) {
		int e = 255;
		Random random = new Random();
		if (s > 255) s = 100;
		return new Color(s + random.nextInt(e - s), s + random.nextInt(e - s),
		        s + random.nextInt(e - s));
	}

}
