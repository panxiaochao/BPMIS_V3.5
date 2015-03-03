package bpmis.pxc.system.service.impl;

import bpmis.pxc.system.hicharts.pojo.HiChartsBase;
import bpmis.pxc.system.hicharts.pojo.HighArea;
import bpmis.pxc.system.service.HichartsService;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.pxcbpmisframework.core.common.dao.SystemDao;
import org.springframework.stereotype.Service;

@Service("hichartsService")
public class HichartsServiceImpl implements HichartsService {
	@Resource
	public SystemDao systemDao;

	public HighArea getHiPieList(String starttime, String endtime, String type) {
		HighArea harea = null;
		String sql = "select s.id, s.s_sortname, sum(t.s_salarymoney) from s_sort s left join s_salarytable t on s.id = t.s_sortId where s.s_sorttype = '"
				+

				type
				+ "' and t.s_tradetime >= '"
				+ starttime
				+ "' and t.s_tradetime <= '"
				+ endtime
				+ "' group by s.id, s.s_sortname";
		System.out.println("================sql:" + sql);
		List<?> list = this.systemDao.findByQuerySql(sql);
		if ((list != null) && (list.size() > 0)) {
			harea = new HighArea();
			ArrayList<HiChartsBase> datalist = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				HiChartsBase base = new HiChartsBase();
				base.setName(obj[1].toString());
				base.setY(Float.valueOf(obj[2].toString()).floatValue());
				datalist.add(base);
			}
			harea.setName("账单类别[" + type + "]");
			harea.setData(datalist);
		}
		return harea;
	}
}
