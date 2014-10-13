package com.webapp.utils.regex;


public class RegexConst {

	/** 整数 --> {@value} */
	public static final String Integer 	= "^-?[1-9]\\d*|0$";
	/** 正整数 --> {@value} */
    public static final String Integer_p= "^[1-9]\\d*|0$";
    /** 负整数 --> {@value} */
    public static final String Integer_n= "^-[1-9]\\d*|0$";
    /** 数字 --> {@value} */
    public static final String Num     	= "^([+-]?)\\d*\\.?\\d+$";
    /** 浮点数 --> {@value} */
    public static final String Decimal  = "^([+-]?)\\d*\\.\\d+$";
    /** 正浮点数 --> {@value} */
    public static final String Decimal_p= "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";
    /** 负浮点数 --> {@value} */
    public static final String Decimal_n= "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";
    /** 邮件 --> {@value} */
    public static final String Email   	= "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
    /** 颜色 --> {@value} */
    public static final String Color   	= "^[a-fA-F0-9]{6}$";
    /** url --> {@value} */
    public static final String Url     	= "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";
    /** 仅中文 --> {@value} */
    public static final String Chinese 	= "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
    /** 仅ACSII字符 --> {@value} */
    public static final String Ascii   	= "^[\\x00-\\xFF]+$";
    /** 邮编 --> {@value} */
    public static final String Zipcode 	= "^\\d{6}$";
    /** 手机 --> {@value} */
    public static final String Mobile  	= "^(13[0-9]|14[5,7]|15[^4,\\D]|17[6-8]|18[0-9])\\d{8}$";
    /** ip地址 --> {@value} */
    public static final String Ip4     	= "^((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)(\\.((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)){3}";
    /** 非空 --> {@value} */
    public static final String Notempty	= "^\\S+$";
    /** 图片 --> {@value} */
    public static final String Picture 	= "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";
    /** 压缩文件 --> {@value} */
    public static final String Rar     	= "(.*)\\.(rar|zip|7zip|tgz)$";
    /** 日期 --> {@value} */
    public static final String Date    	= "^\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}$";
    /** QQ号码 --> {@value} */
    public static final String QQ      	= "^[1-9]*[1-9][0-9]*$";
    /** 电话号码的函数(包括验证国内区号,国际区号,分机号) --> {@value} */
    public static final String Tel     	= "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";
    /** 字母 --> {@value} */
    public static final String Letter  	= "^[A-Za-z]+$";
    /** 大写字母 --> {@value} */
    public static final String Letter_u	= "^[A-Z]+$";
    /** 小写字母 --> {@value} */
    public static final String Letter_l	= "^[a-z]+$";
    /** 身份证 --> {@value} */
    public static final String Idcard  	= "^[1-9]([0-9]{14}|[0-9]{17})$";

}
