package bpmis.pxc.system.controller.core;

import javax.servlet.http.HttpServletRequest;

import org.pxcbpmisframework.core.json.AjaxJson;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

/**
 * 总类
 * 
 * @author panxiaochao
 * 
 */
public class BaseController {
	// public AjaxJson SavePojo(HttpServletRequest req, Object obj);
	protected static String ToUrl = "error/404";

	public String redirect(String jsp) {
		return "login";
	}

	public AjaxJson deletePojo(HttpServletRequest req, String type) {
		return null;
	}

	public ModelAndView getSinglePojo(String type, String id, ModelMap map) {
		return null;
	}
}
