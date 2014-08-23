package com.webapp.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/demoservlet", asyncSupported = true)
public class AsyncDemoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
		out.println("进入Servlet的时间：" + new Date() + ".");
		out.flush();

		boolean asyncSupported = req.isAsyncSupported();
		out.println("是否支持异步：" + asyncSupported + ".<br/>");
		out.println("下面开始启动异步处理............<br/>");
		out.flush();

		// 在子线程中执行业务调用，并由其负责输出响应，主线程退出
		AsyncContext ctx = req.startAsync();
		ctx.addListener(new AsyncListener() {

			public void onTimeout(AsyncEvent asyncEvent) throws IOException {
				System.out.println("onTimeout");
			}

			public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
				System.out.println("onStartAsync");
			}

			public void onError(AsyncEvent asyncEvent) throws IOException {
				System.out.println("onError");
			}

			public void onComplete(AsyncEvent asyncEvent) throws IOException {
				System.out.println("onComplete");
			}
		});
		new Thread(new Executor(ctx)).start();
		out.println("结束Servlet的时间：" + new Date() + ".");
		out.flush();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	public class Executor implements Runnable {
		private AsyncContext ctx = null;

		public Executor(AsyncContext ctx) {
			this.ctx = ctx;
		}

		public void run() {
			try {
				// 等待十秒钟，以模拟业务方法的执行
				Thread.sleep(10000);
				PrintWriter out = ctx.getResponse().getWriter();
				out.println("业务处理完毕的时间：" + new Date() + ".");
				out.flush();
				ctx.complete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
