package com.webapp.utils.format;

import java.beans.Beans;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.lang3.math.NumberUtils;

import com.webapp.utils.string.Utils.Symbol;

public class FmtUtils {

	private String data;
	private boolean endZero = true;
	public FmtUtils(String data){
		this.data = data;
	}
//	pattern()
	public static <T> FmtUtils of(T data) {
		String result;
		if(Beans.isInstanceOf(data, Number.class)){
			result = data.toString();
		}else if(Beans.isInstanceOf(data, String.class)){
			result = String.valueOf(data).replaceAll("(?<!\\d)(\\.)|[^\\d\\.]", "");
		}else {
			throw new IllegalArgumentException("Parameter should be Number or String");
		}
	    return new FmtUtils(result);
    }

	public String fmt(int scale){
		return fmt(getPattern(data), scale);
	}

	public String fmt(String pattern) {
	    return fmt(pattern, -1);
    }

	public String fmtCurrency(){
		return fmtCurrency(-1);
	}
	public String fmtCurrency(int scale){
		return fmt(getPattern(data, "￥"), scale);
	}
	public String fmtPercent(){
		return fmtPercent(-1);
	}
	public String fmtPercent(int scale){
		return fmt(getPattern(data, Symbol.Empty, "%"), scale);
	}
	public String fmtRMB() {
	    return fmtRMB(-1);
    }
	public String fmtRMB(int scale) {
	    return fmt(getPattern(data, "RMB"), scale);
    }
	public String fmtDollar() {
	    return fmtDollar(-1);
    }
	public String fmtDollar(int scale) {
	    return fmt(getPattern(data, "$"), scale);
    }

	public int toInt(){
		return NumberUtils.toInt(data);
	}
	public double toDouble(){
		return NumberUtils.toDouble(data);
	}
	public float toFloat(){
		return NumberUtils.toFloat(data);
	}
	public long toLong(){
		return NumberUtils.toLong(data);
	}
	public short toShort(){
		return NumberUtils.toShort(data);
	}

	public FmtUtils endZero(boolean endZero) {
	    this.endZero = endZero;
	    return this;
    }

	private String fmt(String pattern, int scale){
		DecimalFormat format = new DecimalFormat();
		format.setRoundingMode(RoundingMode.HALF_UP);

		Number parse = null;
        try {
	        parse = format.parse(data);
        } catch (ParseException e) {
	        e.printStackTrace();
        }
        format.applyPattern(pattern);

        if(scale > 0){
        	format.setMaximumFractionDigits(scale);
    		format.setMinimumFractionDigits(scale);
        }

		return format.format(parse);
	}

	private String getPattern(String data) {
		return getPattern(data, "", "");
	}

	private String getPattern(String data, String before) {
		return getPattern(data, before, "");
	}

	private String getPattern(String data, String before, String after) {
	    int length = data.replaceFirst("\\d+\\.|\\d+", "").length();
	    String symbol = endZero ? "0" : "#";
    	StringBuffer pattern = new StringBuffer(before + "#");

    	pattern.append(length > 0 ? "." : Symbol.Empty);
    	for(int i=0; i<length; i++){
    		pattern.append(symbol);
    	}
    	pattern.append(after);
    	return pattern.toString();
    }

}
