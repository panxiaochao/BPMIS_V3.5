package org.pxcbpmisframework.core.common.datasource;

/**
 * 
 * @Title: DataSourceType.java
 * @Package org.pxcbpmisframework.core.common.datasource
 * @Description: TODO(数据源列举，其实几点几的版本以整数显示，如3.5版本就写35)
 * @author panxiaochao
 * @date 2015-2-25 下午04:28:21
 * @version V1.0
 */
public enum DataSourceType {
	dataSource_bpmis35, dataSource_bpmis30;

	public static void main(String[] args) {
		System.out.println(DataSourceType.values()[0]);
	}
}
