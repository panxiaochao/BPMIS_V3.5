package bpmis.pxc.system.controller.core.salary;

import bpmis.pxc.system.hicharts.pojo.HighArea;
import bpmis.pxc.system.service.HichartsService;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.pxcbpmisframework.core.common.qbc.HqlQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( { "/hicharts" })
public class SalaryHicharts {
	private static final Logger logger = Logger.getLogger(SalaryHicharts.class);
	@Resource
	private HichartsService hichartsService;

	@RequestMapping( { "/hipie" })
	@ResponseBody
	public Map<?, ?> getHiPie(String sorttype, HttpServletRequest request) {
		logger.info("=== SalaryHicharts === hipie ===");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");

		HighArea harea = this.hichartsService.getHiPieList(starttime, endtime,
				sorttype);

		Map<Object, Object> map = HqlQuery.getHashMapsObj();
		if ("in".equals(sorttype)) {
			map.put("title", "类别收入统计");
		} else {
			map.put("title", "类别支出统计");
		}
		map.put("subtitle", "查询时间：<b>" + starttime + "</b> 到 <b>" + endtime
				+ "</b>");
		map.put("list", harea);

		return map;
	}

	public static void main(String[] args) {
	}
}
