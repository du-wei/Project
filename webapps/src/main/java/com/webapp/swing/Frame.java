package com.webapp.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class Frame extends JFrame {

	public static void main(String[] args) {
		new MyFrame();
	}

	/** Prevent instantiation */
	private Frame() {
	}

}

class MyFrame extends JFrame {
	// TODO: It is a good practice to call super() in a constructor
	public MyFrame() {
		this.setTitle("hello world");

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension size = tk.getScreenSize();
		int h = size.height;
		int w = size.width;
		this.setSize(w / 2, h / 2);
		this.setLocation(w / 4, h / 4);

		Image icon = tk.getImage(System.getProperty("user.dir")
		        + "\\WebRoot\\images\\ok.gif");
		this.setIconImage(icon);

		JPanel jp = new JPanel();
		jp.setBackground(Color.white);

		JLabel jl = new JLabel("�����ҵĵ�һ��swing����");
		jp.add(jl);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("�ҵĵ���");
		DefaultMutableTreeNode c = new DefaultMutableTreeNode("C��");
		DefaultMutableTreeNode d = new DefaultMutableTreeNode("D��");
		DefaultMutableTreeNode e = new DefaultMutableTreeNode("E��");
		DefaultMutableTreeNode f = new DefaultMutableTreeNode("F��");
		root.add(c);
		root.add(d);
		root.add(e);
		root.add(f);

		JPanel jt = new JPanel();
		JTree tree = new JTree(root);
		jt.add(tree);
		jt.setSize(200, 200);

		this.setLayout(new GridBagLayout());
		this.add(jp);
		this.add(jt);
		this.setVisible(true);
	}

}
