package bpmis.pxc.system.service.impl;

import bpmis.pxc.system.pojo.TBLogger;
import bpmis.pxc.system.pojo.TUser;
import bpmis.pxc.system.service.LoginService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.pxcbpmisframework.core.common.dao.SystemDao;
import org.pxcbpmisframework.core.util.BrowserUtils;
import org.pxcbpmisframework.core.util.ContextHolderUtils;
import org.pxcbpmisframework.core.util.DataToolsUtils;
import org.pxcbpmisframework.core.util.IpUtils;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource
	public SystemDao systemDao;

	public <T> int LoadPojoSize(Class<T> obj) {
		return this.systemDao.loadAll(obj);
	}

	public void addLogger(String logcontent, String loglevel, String operatetype) {
		HttpServletRequest request = ContextHolderUtils.getRequest();
		String broswer = BrowserUtils.getUserAgent(request);

		TUser u = ContextHolderUtils.getSessionUser();
		TBLogger logger = new TBLogger();
		if (u == null) {
			logger.setAccount("Guest");
		} else {
			logger.setUserid(u.getId());
			logger.setAccount(u.getAccount());
		}
		logger.setLoglevel(loglevel);
		logger.setLogcontent(logcontent);
		logger.setBroswertype(broswer);
		logger.setOperatetime(DataToolsUtils.SimpleFormatTime());
		logger.setOperatetype(operatetype);
		logger.setAdrip(IpUtils.getServerHostIp());
		save(logger);
	}

	public TUser checkUserExits(String username, String pwd) {
		return this.systemDao.checkUserExits(username.trim(), pwd.trim());
	}

	public <T> void delete(T entity) {
		this.systemDao.delete(entity);
	}

	public <T> void save(T entity) {
		this.systemDao.save(entity);
	}

	public <T> void update(T entity) {
		this.systemDao.update(entity);
	}
}
