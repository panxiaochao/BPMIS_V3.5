package bpmis.pxc.system.hicharts.pojo;

import java.util.ArrayList;

/**
 * 
 * @Title: HighArea.java
 * @Description: TODO(饼图类)
 * @author panxiaochao
 */
public class HighArea {
	/**
	 * name 必须：为哪个类别
	 */
	private String name;
	/**
	 * data 格式 ['城中街道', 86]
	 */
	private ArrayList<?> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<?> getData() {
		return data;
	}

	public void setData(ArrayList<?> data) {
		this.data = data;
	}

}
