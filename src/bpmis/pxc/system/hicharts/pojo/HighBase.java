package bpmis.pxc.system.hicharts.pojo;

import java.util.ArrayList;

/**
 * 
 * @Title: HighArea.java
 * @Package cn.com.pubinfo.highcharts.pojo
 * @Description: TODO(柱状图和趋势图类)
 * @author panxiaochao
 */
public class HighBase {
	/**
	 * name 必须：为哪个类别
	 */
	private String name;
	/**
	 * data 格式 直接数字就行
	 */
	private double[] data;
	private boolean visible = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public double[] getData() {
		return data;
	}

	public void setData(double[] data) {
		this.data = data;
	}
}
