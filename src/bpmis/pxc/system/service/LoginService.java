package bpmis.pxc.system.service;

import bpmis.pxc.system.pojo.TUser;

public interface LoginService {
	public <T> int LoadPojoSize(Class<T> obj);

	public <T> void save(T entity);

	public <T> void update(T entity);

	public <T> void delete(T entity);

	public TUser checkUserExits(String username, String passwordMd5);

	public void addLogger(String logcontent, String loglevel, String operatetype);
}
