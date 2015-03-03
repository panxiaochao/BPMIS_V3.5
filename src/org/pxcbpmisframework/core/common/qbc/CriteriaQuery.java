package org.pxcbpmisframework.core.common.qbc;

import java.util.List;
import java.util.Map;
import org.pxcbpmisframework.core.page.Page;

public class CriteriaQuery {
	private boolean isAsc = false;
	private int currentPage = 1;
	private int pageSize = 10;
	private int totalPages;
	private int totalRecords;
	private String myAction;
	private String myForm;
	private String filed;
	private Map<Object, Object> maporder = null;
	private Map<Object, Object> mapparm = null;
	private Map<Object, String[]> mapparms = null;
	private List<?> reaults;
	private Class<?> entityClass;
	private Page page;

	public CriteriaQuery() {
	}

	public CriteriaQuery(boolean isAsc, String filed) {
		this.isAsc = isAsc;
		this.filed = filed;
	}

	public CriteriaQuery(String filed) {
		this.filed = filed;
	}

	public List<?> getReaults() {
		return this.reaults;
	}

	public void setReaults(List<?> reaults) {
		this.reaults = reaults;
	}

	public Page getPage() {
		return this.page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public boolean isAsc() {
		return this.isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public String getFiled() {
		return this.filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return this.totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRecords() {
		return this.totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public String getMyAction() {
		return this.myAction;
	}

	public void setMyAction(String myAction) {
		this.myAction = myAction;
	}

	public String getMyForm() {
		return this.myForm;
	}

	public void setMyForm(String myForm) {
		this.myForm = myForm;
	}

	public Class<?> getEntityClass() {
		return this.entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	public Map<Object, Object> getMaporder() {
		return this.maporder;
	}

	public void setMaporder(Map<Object, Object> maporder) {
		this.maporder = maporder;
	}

	public Map<Object, Object> getMapparm() {
		return this.mapparm;
	}

	public void setMapparm(Map<Object, Object> mapparm) {
		this.mapparm = mapparm;
	}

	public Map<Object, String[]> getMapparms() {
		return this.mapparms;
	}

	public void setMapparms(Map<Object, String[]> mapparms) {
		this.mapparms = mapparms;
	}
}
