package bpmis.pxc.system.controller.core;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统杂类
 * 
 * @author panxiaochao
 * @ClassName SystemController
 * @Description TODO
 * @date 2014-7-15
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SystemController.class);

	/**
	 * 跳转
	 */
	@RequestMapping(value = "/to/{jsp}")
	public String redirect(@PathVariable String jsp) {
		// TODO Auto-generated method stub
		logger.info("***跳转：" + jsp);
		String[] jspstr = jsp.split("_");
		if (jspstr.length > 0) {
			String toUrl = "";
			for (String str : jspstr) {
				toUrl += str + "/";
			}
			return toUrl.substring(0, toUrl.length() - 1);
		}
		return jsp;
	}

}
