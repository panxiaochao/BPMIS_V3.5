package bpmis.pxc.system.controller.core;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	private static final Logger logger = Logger.getLogger(TestController.class);

	@PostConstruct
	public void init() {
		logger.info("-----------------------   PostConstruct init()....");
	}

	@PreDestroy
	public void dostory() {
		logger.info("-----------------------   PreDestroy dostory....");
	}

	@RequestMapping(value = "/test")
	public String test(HttpServletRequest request) {
		System.out.println("----------->go test");
		logger.info("测试呢！");
		System.out.println("----------->end test");
		return "login";
	}
}
