package com.webapp.utils.string;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 身份证检验类
 */
public class IDUtils {
	
	//校验加权因子数组
	private static final int[] checkCodes = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
	private ID id;		//身份证号码
	private List<String> errorMsgs = new ArrayList<>();	//非法信息集合
	private String addr = "";		//地址信息
	
	public static IDUtils of(String num) {
	    return new IDUtils(num);
    }
	/**
	 * 构造方法
	 * @param num 身份证字符串,18位
	 */
	private IDUtils(String num) {
		this.id = new ID(num.length() >= 18 ? num : "0000000000000000000");
	}
	
	/**
	 * 获取中文格式的出生年月日
	 * @return 
	 */
	public String getBirth(){
		return id.getBirth().substring(0, 4) + "年" + id.getBirth().substring(4,6) + "月" + id.getBirth().substring(6,8) + "日";
	}
	
	/**
	 * 获取性别
	 * @return 男  女
	 */
	public String getSex() {
		return id.getSeq().charAt(2)%2 == 0 ? "女" : "男";
	}
	
	/**
	 * 获得地址
	 * @return
	 */
	public String getAddr(){
		if("".equals(addr)) {
			checkAddr();
		}
		return addr;
	}
	
	/**
	 * 总的身份证验证
	 * 验证顺序：长度 -> 生日 -> 最后一位校验码 -> 地址
	 * @return 身份证合法则返回true，否则false
	 */
	public boolean checkAll(){
		//验证长度	//验证生日	//验证最后一校验码	//验证地址
		if(!checkLength()) {		//验证长度
			return false;			//长度都不对，其他的就不用验证了
		}
		return checkBirth() & checkCode() & checkAddr() ? true : false;
	}
	
	/**
	 * 检查身份证长度是否正确
	 * @return 长度为18则返回true，否则返回false
	 */
	public boolean checkLength(){
		int length = id.getNum().length();
		if(length == 18) {
			return true;
		}
		errorMsgs.add("身份证长度不正确");
		return false;
	}
	
	/**
	 * 验证身份证出生年月日是否合法
	 * @return 合法返回true,否则返回false
	 */
	public boolean checkBirth(){
		String birth = id.getBirth();
		int year, month, day;		//年月日
		try{
			year = Integer.valueOf(birth.substring(0,4));
			month = Integer.valueOf(birth.substring(4,6));
			day = Integer.valueOf(birth.substring(6, 8));
		} catch (NumberFormatException e) {
			errorMsgs.add("身份证生日码不正确！");
			return false;
		}
		if((year >= 1900 && year <= 2010) && (month >=1 && month <= 12) && (day >= 1 && day <= 31)) {
			return true;
		}
		errorMsgs.add("身份证生日码不正确！");
		return false;
	}
	
	/**
	 * 验证地址码是否存在
	 * @return 存在返回true，不存在返回false
	 */
	public boolean checkAddr(){
		String addrCode = id.getAddr();
		addr = readAddress(addrCode);
		if(addr == null) {
			errorMsgs.add("身份证地址码不正确！");
			return false;
		}
		return true;
	}

	/**
	 * 验证校验码是否正确
	 * @return 正确返回true，否则返回false
	 */
	public boolean checkCode(){
		String chCode = caculateCheckCode(id.getNum());		//计算正确的末位校验码
		if(!id.getCheck().equalsIgnoreCase(chCode)){
			errorMsgs.add("身份证校验码不正确, 正确的校验码是 " + chCode);
			return false;
		}
		return true;
	}
	
	/**
	 * 取得错误信息
	 * @return
	 */
	public String getErrorMsg(){
		return errorMsgs.size() > 0 ? errorMsgs.get(0) : "";
	}
	
	/**
	 * 取得错误信息集合
	 * @return
	 */
	public List<String> getErrorMsgs() {
		return errorMsgs;
	}
	
	/**
	 * 分离身份证号码
	 * @return 整型数组，最后一位若是'X'，则返回10
	 */
	public static final int[] toInt(String num){
		int[] ins = new int[18];
		int i = 0;
		for( ; i < ins.length - 1; i ++) {
			ins[i] = Integer.valueOf(num.substring(i, i+1));
		}
		String last = num.substring(i, i+1);
		ins[i] = "X".equals(last) ? 10 : Integer.valueOf(last);
		return ins;
	}
	
	/**
	 * 计算校验位
	 * @return 
	 */
	private static final String caculateCheckCode(String num){
		int total = 0;		//校验值和
		int[] ins = new int[18];
		try{
			for(int i = 0; i < ins.length - 1; i ++) {
				ins[i] = Integer.valueOf(num.substring(i, i+1));
				total += (ins[i]*checkCodes[i]);
			}
		} catch(NumberFormatException e) {
			return null;
		}
		int modResult = total % 11;
		
		String result = null;
		//校验码对应值: 1 0 X 9 8 7 6 5 4 3 2
		switch (modResult) {
			case 0:
				result = "1";
				break;
			case 1:
				result = "0";
				break;
			case 2:
				result = "X";
				break;
			case 3:
				result = "9";
				break;
			case 4:
				result = "8";
				break;
			case 5:
				result = "7";
				break;
			case 6:
				result = "6";
				break;
			case 7:
				result = "5";
				break;
			case 8:
				result = "4";
				break;
			case 9:
				result = "3";
				break;
			case 10:
				result = "2";
				break;
			default:
				break;
		}
		return result;
	}
	
	/**
	 * 读取地址码
	 * @param addrNum
	 * @return 若存在，则返回该地址码对应的地址名称，若不存在，返回null
	 */
	private static final String readAddress(String addrNum) {
		char first = addrNum.charAt(0);
		if(first == '1' || first == '2' || first == '3' || first == '4' || first == '5' || first == '6'){
			String filePath = "/address/" + first + ".dic";
			String addr =  readAddress(filePath, "UTF-8", addrNum);
			return addr;
		}
		return null;
	}
	
	/**
	 * 读取地址码是否存在
	 * @param filePath 文件路径
	 * @param charset 文件编码
	 * @param addrNum 地址码，6位数字
	 * @return 存在返回地址名称，否则返回null
	 */
	private static final String readAddress(String filePath, String charset, String addrNum){
		String addr = null;
		try {
			InputStream is = IDUtils.class.getResourceAsStream(filePath);
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(is,charset));

			String line;
			while((line = buffReader.readLine()) != null){
				if(addrNum.equals(line.substring(0,6))) {
					addr = line.substring(7, line.length());
					break;
				}
			}
			buffReader.close(); 
			
		} catch (FileNotFoundException e) {
			System.err.println("找到不地址码文件");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("读取地址码文件失败");
			e.printStackTrace();
		}
		return addr;
	}
	
	private class ID {
		
		private String num;		//全长身份证号码
		private String addr;	//地址
		private String birth;	//生日
		private String seq;		//序号
		private String check;	//校验码

		public ID(String num) {
			this.num = num;
			this.addr = num.substring(0, 6);
			this.birth = num.substring(6, 14);
			this.seq = num.substring(14,17);
			this.check = num.substring(17,18);
		}
		
		public String getAddr() {
			return addr;
		}
		public String getBirth() {
			return birth;
		}
		public String getSeq() {
			return seq;
		}
		public String getCheck() {
			return check;
		}
		public String getNum() {
			return num;
		}
	}
	
}
