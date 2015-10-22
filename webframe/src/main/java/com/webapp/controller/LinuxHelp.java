package com.webapp.controller;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.dao.LinuxDao;
import com.webapp.model.Linux;

@ControllerAdvice
@Controller
@RequestMapping(value = { "/linux", "/help" })
public class LinuxHelp {
	private static final Logger logger = LoggerFactory.getLogger(LinuxHelp.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		logger.error("", ex);
		ModelAndView mav = new ModelAndView("redirect:/linux/h");
		return mav;
    }


	private static final String split = "_";
	private static final String cmd_start = "-";
	private static final String cmd_split = "-:";
	private static final String cmd_split1 = "-";
	private static final String cmd_split2 = ":";
	private static final String cmd_desc = ".@";
	private static final String cmd_desc1 = ".";
	private static final String cmd_desc1zn = "..";
	private static final String cmd_desc2 = "@";
	private static final String cmd_desc2zn = "@@";
	private static TreeMap<String, Linux> linuxs;
	static {
		linuxs = new TreeMap<String, Linux>((tsort1, tsort2)->{
			String[] ts1 = tsort1.split(split);
			String[] ts2 = tsort2.split(split);
			if(ts1[0].compareToIgnoreCase(ts2[0]) == 0){
				return Integer.valueOf(ts1[1]).compareTo(Integer.valueOf(ts2[1]));
			}
			return ts1[0].compareToIgnoreCase(ts2[0]);
		});
	}
	private static String helps = null;
	private static Pattern compile = Pattern.compile("(.+?)(\\d+)");

	@Autowired
	private LinuxDao linuxDao;

	@ResponseBody
	@RequestMapping(value={"/{cmd:[-]?[a-zA-Z]+[-:]?[a-zA-Z]*\\d{0,2}[\\.@]{0,2}}"})
	public String cmd(@PathVariable("cmd") String cmd) {
		logger.info("cmd -> {}", cmd);
		if(linuxs.size() <= 0) reload();
		if(cmd.equals("h") || cmd.equals("-h")) return helps;
		if(cmd.equals("l") || cmd.equals("-l")) return reload();

		cmd = StringUtils.removeStart(cmd, cmd_start);

		boolean hasDesc = false;
		if(cmd.endsWith(cmd_desc1) || cmd.endsWith(cmd_desc2)) hasDesc = true;

		boolean isZn = false;
		if(cmd.endsWith(cmd_desc1zn) || cmd.endsWith(cmd_desc2zn)) isZn = true;
		cmd = cmd.replaceAll("[\\" + cmd_desc + "]", "");

		String pindex = "";
		boolean hasIndex = false;
		Matcher matcher = compile.matcher(cmd);
		if(matcher.find()){
			cmd = matcher.group(1);
			pindex = matcher.group(2);
			hasIndex = true;
		}

		boolean hasType = false;
		boolean hasKey = false;
		String ptype = "";
		String pkey = "";
		String[] split = cmd.split("[ " + cmd_split + " ]");
		if(split.length == 2){
			hasType = true;
			ptype = split[0];
			hasKey = true;
			pkey = split[1];
		}else if(split.length == 1){
			if(cmd.endsWith(cmd_split1) || cmd.endsWith(cmd_split2)){
				hasType = true;
				ptype = split[0];
			}else {
				hasKey = true;
				pkey = split[0];
			}
		}

		logger.info("type -> {} key -> {} index -> {} desc -> {}", ptype, pkey, pindex, hasDesc ? (isZn ? "zn" : "cn") : "");

		final StringBuffer result = new StringBuffer();
		Iterator<String> keys = linuxs.keySet().iterator();
		for(int i=0;keys.hasNext();){
			String next = keys.next();
			Linux linux = linuxs.get(next);

			String type = linux.getType();
			String ckey = linux.getCkey();
			String ksort = linux.getKsort().toString();

			if(hasType && hasKey){
				if(ptype.equals(type) && pkey.equals(ckey)){
					if(hasIndex && !pindex.equals(ksort)) continue;
					result.append(addCmd(linux, hasDesc, isZn, ++i));
				}
			}else if(hasType){
				if(ptype.equals(type)){
					if(hasIndex && !pindex.equals(ksort)) continue;
					result.append(addCmd(linux, hasDesc, isZn, ++i));
				}
			}else if(hasKey){
				if(pkey.equals(ckey)){
					if(hasIndex && !pindex.equals(ksort)) continue;
					result.append(addCmd(linux, hasDesc, isZn, ++i));
				}
			}
		}

		return result.toString();
	}

	private String addCmd(Linux linux, boolean hasDesc, boolean isZn, int index) {
		String result = "";
		if(hasDesc){
			result = isZn ? fmt(index, linux.getCmd(), linux.getDescZn()) : fmt(index, linux.getCmd(), linux.getDescEn());
		}else {
			result = fmt(index, linux.getCmd());
		}
		return result;
	}

	private void help() {
		final StringBuffer result = new StringBuffer();
		result.append(fmt(0, "type[-:]key[i][.@]", "desc"));

		List<Linux> help = linuxDao.getHelp();
		AtomicInteger count = new AtomicInteger(1);
		help.forEach(x -> result.append(fmt(count.getAndIncrement(), x.getType(), x.getCkey(), x.getDescEn())));

		helps =  result.toString();
	}

	private void load() {
		List<Linux> all = linuxDao.getAll();
		linuxs.clear();
		all.forEach( x -> linuxs.put( x.getType() + split + x.getTsort(), x));
		logger.info("all -> " + all.size() + " linux -> " + linuxs.size());
	}

	private String reload() {
		help();
		load();
		return "";
	}

	private static String fmt(int index, String cmd) {
		return String.format("%1d\t%2$s\n", index, cmd);
	}
	private static String fmt(int index, String cmd, String desc) {
		return String.format("%1d\t%2$s\t\t%3$s\n", index, cmd, desc);
    }
	
	private static String fmt(int index, String type, String cmd, String desc) {
		return String.format("%1d\t%2$s\t%3$s\t\t%4$s\n", index, type, cmd, desc);
    }

}
