package org.pxcbpmisframework.core.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class MyWebBinding implements WebBindingInitializer {

	private static final Logger logger = Logger.getLogger(MyWebBinding.class);

	public void initBinder(WebDataBinder databinder, WebRequest webrequest) {
		// TODO Auto-generated method stub
		logger.info("---MyWebBinding---");
	}

}
