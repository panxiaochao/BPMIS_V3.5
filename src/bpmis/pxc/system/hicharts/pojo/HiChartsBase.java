package bpmis.pxc.system.hicharts.pojo;

public class HiChartsBase {
	/**
	 * name 必须：为哪个类别
	 */
	private String name;
	/**
	 * data 格式 直接数字就行
	 */
	private double y;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
