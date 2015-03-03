package bpmis.pxc.system.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.pxcbpmisframework.core.common.qbc.CriteriaQuery;

import bpmis.pxc.system.pojo.salary.TSalary;

public interface SalaryService {
	public <T> Serializable save(T entity, String type);

	public <T> void update(T entity);

	public <T> void delete(T entity);

	public CriteriaQuery getList(Class<?> obj, CriteriaQuery cq, String type);

	public void deleteAll(Class<?> class1, String[] split);

	public void addLogger(String logcontent, String loglevel, String operatetype);

	public <T> Object getClassById(Class<T> clazz, String id);

	public List<?> getPbank(boolean isEdit, String id);

	public List<?> getCredit(boolean isEdit, String id);

	public List<?> getSalaryBank(String isEdit, String id);

	public List<?> getSortType(String type);

	public float setTotalSal(String id, String trdetype, String bank,
			float money);

	public Map<String, String> setSalary(Map<Object, Object> map);

	public List<?> getBankTotal();
}
