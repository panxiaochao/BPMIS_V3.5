package org.pxcbpmisframework.core.common.dao.impl;

import bpmis.pxc.system.pojo.TUser;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.pxcbpmisframework.core.common.dao.SystemDao;
import org.pxcbpmisframework.core.util.PasswordUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("systemDao")
@Transactional
public class ExtendDaoImpl extends SystemDaoImpl implements SystemDao {
	public TUser checkUserExits(String username, String pwd) {
		pwd = PasswordUtil.PasswordMd5(pwd);
		String query = "from TUser u where u.account = :username and u.pwd = :passowrd";
		Query queryObject = getSession().createQuery(query);
		queryObject.setParameter("username", username);
		queryObject.setParameter("passowrd", pwd);
		List<TUser> users = queryObject.list();
		if ((users != null) && (users.size() > 0)) {
			return (TUser) users.get(0);
		}
		return null;
	}
}
