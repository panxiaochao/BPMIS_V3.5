package org.pxcbpmisframework.core.interceptor;

import bpmis.pxc.system.manager.Client;
import bpmis.pxc.system.manager.ClientManager;
import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class WebAusFilter implements Filter {
	private static final Logger logger = Logger.getLogger(WebAusFilter.class);
	private static final long serialVersionUID = 1L;

	public void destroy() {
		logger.info("=========  BPMIS ShutDown! =========");
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());

		System.out.println("**************>client: " + client);

		logger.info("Request URl：" + request.getRequestURL());

		String path = request.getRequestURI();
		System.out.println("**************>URL: " + path);
		if ((path.endsWith("login.jsp")) || (path.endsWith("/bpmis3.5/"))) {
			chain.doFilter(req, res);
			return;
		}
		if (client == null) {
			forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		logger.info("=========  BPMIS Start! =========");
	}

	public void forward(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/webpage/error.jsp").forward(request,
				response);
	}
}
