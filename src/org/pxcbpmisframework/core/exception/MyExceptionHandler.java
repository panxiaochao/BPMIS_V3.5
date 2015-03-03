package org.pxcbpmisframework.core.exception;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.pxcbpmisframework.core.common.service.SystemService;
import org.pxcbpmisframework.core.util.ExceptionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyExceptionHandler implements HandlerExceptionResolver {
	private static final Logger logger = Logger
			.getLogger(MyExceptionHandler.class);
	@Resource
	private SystemService systemService;

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		String exceptionMessage = ExceptionUtil.getExceptionMessage(ex);
		logger.error(exceptionMessage);

		return new ModelAndView("error/error");
	}
}
