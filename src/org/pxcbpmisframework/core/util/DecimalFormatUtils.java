package org.pxcbpmisframework.core.util;

import java.text.DecimalFormat;

/**
 * 
 * @Title: DecimalFormatUtils.java
 * @Package org.pxcbpmisframework.core.util
 * @Description: TODO(数据格式化)
 * @author panxiaochao
 * @date 2014-10-27 上午09:25:04
 * @version V1.0
 */
public class DecimalFormatUtils {

	
	/**
	 * 默认3位
	 * @param value
	 * @return
	 */
	public static String SimpleFormat_2(double value){
		return SimpleFormat(value,"##.00");
	}
	
	/**
	 * 自定义格式化
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static String SimpleFormat(double value, String pattern) {
		DecimalFormat format = new DecimalFormat(pattern);
		return format.format(value);
	}
	
	public static void main(String[] args) {
		float num = 123;
		System.out.println(DecimalFormatUtils.SimpleFormat_2(num));
	}
}
