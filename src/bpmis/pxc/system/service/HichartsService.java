package bpmis.pxc.system.service;

import bpmis.pxc.system.hicharts.pojo.HighArea;

public interface HichartsService {

	public HighArea getHiPieList(String starttime, String endtime, String type);

}
