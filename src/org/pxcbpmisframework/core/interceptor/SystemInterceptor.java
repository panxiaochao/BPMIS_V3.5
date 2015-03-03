package org.pxcbpmisframework.core.interceptor;

import bpmis.pxc.system.manager.Client;
import bpmis.pxc.system.manager.ClientManager;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SystemInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = Logger
			.getLogger(SystemInterceptor.class);
	private List<String> excludeUrls;
	private String mappingURL;

	public List<String> getExcludeUrls() {
		return this.excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public String getMappingURL() {
		return this.mappingURL;
	}

	public void setMappingURL(String mappingURL) {
		this.mappingURL = mappingURL;
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out
				.println("\nstart:-----------------------------------------------------------");
		System.out.println("==============>1、执行顺序(preHandle)");
		HttpSession session = request.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());
		String requestUri = request.getRequestURI();

		System.out.println("URL：" + requestUri);

		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", Long.valueOf(startTime));

		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = ((Long) request.getAttribute("startTime")).longValue();

		long endTime = System.currentTimeMillis();

		System.out.println("==============> 2、执行顺序(postHandle)");
		System.out.println("完成请求处理耗时：" + (endTime - startTime) + " 毫秒");
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = ((Long) request.getAttribute("startTime")).longValue();
		long endTime = System.currentTimeMillis();
		System.out.println("==============3、执行顺序(afterCompletion)");
		System.out.println("完成视图渲染耗时：" + (endTime - startTime) + " 毫秒");
		System.out
				.println("end:-----------------------------------------------------------\n");
	}

	public void forward(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/webpage/login.jsp").forward(request,
				response);
	}

	public static void main(String[] args) {
		List<String> excludeUrls = new ArrayList();
	}
}
