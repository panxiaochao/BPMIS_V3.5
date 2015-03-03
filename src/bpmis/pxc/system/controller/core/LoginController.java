package bpmis.pxc.system.controller.core;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.pxcbpmisframework.core.constant.Globals;
import org.pxcbpmisframework.core.json.AjaxJson;
import org.pxcbpmisframework.core.util.BpmisUtils;
import org.pxcbpmisframework.core.util.ContextHolderUtils;
import org.pxcbpmisframework.core.util.DataToolsUtils;
import org.pxcbpmisframework.core.util.IpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bpmis.pxc.system.manager.Client;
import bpmis.pxc.system.manager.ClientManager;
import bpmis.pxc.system.pojo.TUser;
import bpmis.pxc.system.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(LoginController.class);
	@Resource
	private LoginService loginService;
	private String message = null;

	/**
	 * 首先检查用户名是否正确
	 * 
	 * @param username
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/checkuserlogin", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson checkuserlogin(String username, String pwd,
			HttpServletRequest req) {
		System.out.println(req.getParameter("username"));
		HttpSession session = ContextHolderUtils.getSession();
		AjaxJson ajx = new AjaxJson();
		try {
			int users = loginService.LoadPojoSize(TUser.class);
			if (users == 0) {
				TUser user = BpmisUtils.InitUser();
				loginService.save(user);
				ajx.setMsg("初始化成功，账号：panxiaochao/123456 ！");
				ajx.setSuccess(false);
			} else {
				TUser u = loginService.checkUserExits(username, pwd);
				if (u != null) {
					message = "用户: " + u.getAccount() + "登录成功";
					// ContextHolderUtils.getSession().setAttribute(Globals.USER_SESSION,
					// "");
					// 最后登陆时间
					if (null != u.getLogintime())
						u.setLasttime(u.getLogintime());
					else
						u.setLasttime(DataToolsUtils.SimpleFormatTime());
					u.setLogintime(DataToolsUtils.SimpleFormatTime());
					loginService.update(u);
					//				
					ajx.setMsg("登录成功！");
					Client client = new Client();
					client.setIp(IpUtils.getIpAddr(req));
					client.setLogindatetime(new Date());
					client.setUser(u);
					ClientManager.getInstance().addClinet(session.getId(),
							client);
					// 添加登陆日志
					loginService.addLogger(message, Globals.Logger_Leavel_INFO,
							Globals.Logger_Type_LOGIN);
					//
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("url", "login/main");
					ajx.setMap(map);
				} else {
					ajx.setMsg("用户名或密码错误！");
					ajx.setSuccess(false);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e);
			loginService.addLogger(e.getMessage(), Globals.Logger_Leavel_ERROR,
					Globals.Logger_Type_LOGIN);
			ajx.setMsg("登录验证发生错误！");
			ajx.setSuccess(false);
		}
		return ajx;
	}

	/**
	 * 登录
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main(ModelMap map) {
		return new ModelAndView("main/main");
	}
	
	/**
	 * 登录
	 */
	@RequestMapping(value = "/index")
	public ModelAndView login(ModelMap map) {
		return new ModelAndView("login");
	}

	/**
	 * 退出系统
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = ContextHolderUtils.getSession();
		TUser user = ContextHolderUtils.getSessionUser();

		loginService.addLogger("用户：" + user.getAccount() + "已退出",
				Globals.Logger_Leavel_INFO, Globals.Logger_Type_EXIT);
		ClientManager.getInstance().removeClinet(session.getId());
		return "redirect:/login/index";
	}

	/**
	 * 退出系统
	 * 
	 * @param user
	 * @param req
	 * @return
	 */

	public ModelAndView error_logout(HttpServletRequest request) {
		HttpSession session = ContextHolderUtils.getSession();
		loginService.addLogger("超时退出或者错误退出", Globals.Logger_Leavel_INFO,
				Globals.Logger_Type_EXIT);
		ClientManager.getInstance().removeClinet(session.getId());
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	/**
	 * 跳转
	 */
	@RequestMapping(value = "/redirect/{jsp}")
	public String redirect(@PathVariable String jsp) {
		if (jsp.split("_").length > 1) {
			return jsp.split("_")[0] + "/" + jsp.split("_")[1];
		}
		return jsp;
	}
}
