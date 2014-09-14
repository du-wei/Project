/**
 * @Title: LoadJobPlugins.java
 * @Package com.webapp.scheduled
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-27 下午5:53:10
 * @version V1.0
 */
package com.webapp.quartz;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.simpl.CascadingClassLoadHelper;
import org.quartz.spi.ClassLoadHelper;
import org.quartz.spi.SchedulerPlugin;
import org.quartz.xml.XMLSchedulingDataProcessor;

/**
 * @ClassName: LoadJobPlugins.java
 * @Package com.webapp.scheduled
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-27 下午5:53:10
 * @version V1.0
 */
public class LoadJobsPlugin implements SchedulerPlugin {

	private static Logger logger = LogManager.getLogger(LoadJobsPlugin.class);

	private Scheduler scheduler;
	private String jobsDir;
	private List<Path> jobsPath;

	public LoadJobsPlugin() {
		this.jobsPath = new ArrayList<Path>();
	}

	public void setJobsDir(String jobsDir) {
		this.jobsDir = jobsDir;
	}

	@Override
	public void start() {
		processJobs();
	}

//	@Override
//	public void initialize(String name, Scheduler scheduler)
//			throws SchedulerException {
//		this.scheduler = scheduler;
//		logger.info("启动插件:" + name);
//		try {
//			loadJobs(name);
//		} catch (Exception e) {
//			throw new SchedulerConfigException("未找到任务配置目录：" + jobsDir);
//		}
//	}

	@Override
	public void shutdown() {
		try {
			this.scheduler.shutdown();
		} catch (SchedulerException e) {
			logger.error("调度退出异常。", e);
		}
	}

	private void loadJobs(String pluginName) throws Exception {
		Path path = Paths.get(ConfigurationUtils.locate(jobsDir).toURI());

		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
			for (Path file : ds) {
				jobsPath.add(file);
			}
		} catch (IOException e) {
			logger.error("遍历工作目录失败");
		}

		logger.info("任务配置目录：" + path.toString() + ",任务数量："
				+ this.jobsPath.size());
	}

	public void processJobs() {
		String fileName = null;
		try {
			ClassLoadHelper clhelper = new CascadingClassLoadHelper();
			clhelper.initialize();
			XMLSchedulingDataProcessor processor = new XMLSchedulingDataProcessor(
					clhelper);
			int size = jobsPath.size();
			for (int i = 0; i < size; i++) {
				fileName = jobsPath.get(i).toUri().toString();
				processor.processFileAndScheduleJobs(fileName, scheduler);
				logger.info("加载任务配置文件成功: " + fileName);
			}
		} catch (Exception ex) {
			logger.error("加载任务配置文件失败: " + fileName, ex);
		}

	}

	@Override
	public void initialize(String arg0, Scheduler arg1, ClassLoadHelper arg2)
			throws SchedulerException {
		// TODO Auto-generated method stub

	}

}
