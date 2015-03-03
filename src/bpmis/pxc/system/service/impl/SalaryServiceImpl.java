package bpmis.pxc.system.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.pxcbpmisframework.core.common.dao.SystemDao;
import org.pxcbpmisframework.core.common.qbc.CriteriaQuery;
import org.pxcbpmisframework.core.common.qbc.HqlQuery;
import org.pxcbpmisframework.core.json.ComboBox;
import org.pxcbpmisframework.core.util.BpmisUtils;
import org.pxcbpmisframework.core.util.BrowserUtils;
import org.pxcbpmisframework.core.util.ContextHolderUtils;
import org.pxcbpmisframework.core.util.DataToolsUtils;
import org.pxcbpmisframework.core.util.DecimalFormatUtils;
import org.pxcbpmisframework.core.util.IpUtils;
import org.pxcbpmisframework.core.util.oConvertUtils;
import org.springframework.stereotype.Service;

import bpmis.pxc.system.pojo.TBLogger;
import bpmis.pxc.system.pojo.TUser;
import bpmis.pxc.system.pojo.salary.TBank;
import bpmis.pxc.system.pojo.salary.TCredit;
import bpmis.pxc.system.pojo.salary.TSalary;
import bpmis.pxc.system.pojo.salary.TSort;
import bpmis.pxc.system.service.SalaryService;

@Service("salaryService")
public class SalaryServiceImpl implements SalaryService {
	@Resource
	public SystemDao systemDao;

	public <T> void delete(T entity) {
		// TODO Auto-generated method stub
		systemDao.delete(entity);
	}

	public <T> Serializable save(T entity, String type) {
		// TODO Auto-generated method stub
		// 定制信用卡
		if ("credit".equals(type)) {
			TCredit tc = (TCredit) entity;
			TBank tb = (TBank) systemDao.getClassById(TBank.class, tc
					.getBankid());
			tc.setBankname(tb.getCbankname() + "[" + tb.getBanktype() + "]");
		}

		// 定制账单
		if ("salary".equals(type)) {
			TSalary tc = (TSalary) entity;
			TBank tb = (TBank) systemDao
					.getClassById(TBank.class, tc.getBank());
			TSort ts = (TSort) systemDao.getClassById(TSort.class, tc
					.getSortId());

			tc.setBankname(tb.getCbankname() + "[" + tb.getBanktype() + "]");
			tc.setSortname(ts.getSortname());
			return systemDao.save(tc);
		}
		return systemDao.save(entity);
	}

	public <T> void update(T entity) {
		// TODO Auto-generated method stub
		systemDao.update(entity);
	}

	public CriteriaQuery getList(Class<?> obj, CriteriaQuery cq, String type) {
		// TODO Auto-generated method stub
		List<?> list = null;
		CriteriaQuery cqr = systemDao.getPageList2(obj, cq);
		// 定制账单
		if ("salary".equals(type)) {
			list = cqr.getReaults();
			if (list != null)
				for (int i = 0; i < list.size(); i++) {
					TSalary tc = (TSalary) list.get(i);
					if (tc.getTrdetype().equals("in"))
						tc.setStore(tc.getSalarymoney());
					else
						tc.setPay(tc.getSalarymoney());
				}
		}
		return cqr;
	}

	public void deleteAll(Class<?> clas, String[] split) {
		// TODO Auto-generated method stub
		systemDao.deleteAll(clas, split, null);
	}

	public void addLogger(String logcontent, String loglevel, String operatetype) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ContextHolderUtils.getRequest();
		String broswer = BrowserUtils.getUserAgent(request);

		TUser u = ContextHolderUtils.getSessionUser();
		TBLogger logger = new TBLogger();
		if (u == null)
			logger.setAccount("Guest");
		else {
			logger.setUserid(u.getId());
			logger.setAccount(u.getAccount());
		}
		logger.setLoglevel(loglevel);
		logger.setLogcontent(logcontent);
		logger.setBroswertype(broswer);
		logger.setOperatetime(DataToolsUtils.SimpleFormatTime());
		logger.setOperatetype(operatetype);
		logger.setAdrip(IpUtils.getServerHostIp());

		systemDao.save(logger);
	}

	public <T> Object getClassById(Class<T> clazz, String id) {
		// TODO Auto-generated method stub
		return systemDao.getClassById(clazz, id);
	}

	public List<?> getPbank(boolean isEdit, String id) {
		// TODO Auto-generated method stub
		String hql = "from TBank where pstate = '1'";
		List<?> list = systemDao.findByQueryHql(hql);
		List<ComboBox> temp = new ArrayList<ComboBox>();

		if (!isEdit)
			for (int i = 0, len = list.size(); i < len; i++) {
				TBank bank = (TBank) list.get(i);
				ComboBox cb = new ComboBox();
				cb.setId(bank.getId());
				cb.setText(bank.getBankname() + "[" + bank.getBanktype() + "]");
				if (i == 0)
					cb.setSelected(true);
				temp.add(cb);
			}
		else {
			for (int i = 0, len = list.size(); i < len; i++) {
				TBank bank = (TBank) list.get(i);
				ComboBox cb = new ComboBox();
				cb.setId(bank.getId());
				cb.setText(bank.getBankname() + "[" + bank.getBanktype() + "]");

				if (id.equals(bank.getId()))
					cb.setSelected(true);
				temp.add(cb);
			}
		}

		return temp;
	}

	public List<?> getCredit(boolean isEdit, String id) {
		// TODO Auto-generated method stub
		TUser user = ContextHolderUtils.getSessionUser();
		String hql = "from TBank where pstate = '0' and bankflag = '2' and userid = '"
				+ user.getId() + "'";
		List<?> list = systemDao.findByQueryHql(hql);
		List<ComboBox> temp = new ArrayList<ComboBox>();
		if (!isEdit)
			for (int i = 0, len = list.size(); i < len; i++) {
				TBank bank = (TBank) list.get(i);
				ComboBox cb = new ComboBox();
				cb.setId(bank.getId());
				cb
						.setText(bank.getCbankname() + "[" + bank.getBanktype()
								+ "]");
				if (i == 0)
					cb.setSelected(true);
				temp.add(cb);
			}
		else {
			for (int i = 0, len = list.size(); i < len; i++) {
				TBank bank = (TBank) list.get(i);
				ComboBox cb = new ComboBox();
				cb.setId(bank.getId());
				cb
						.setText(bank.getCbankname() + "[" + bank.getBanktype()
								+ "]");

				if (id.equals(bank.getId()))
					cb.setSelected(true);
				temp.add(cb);
			}
		}

		return temp;
	}

	public List<?> getSalaryBank(String isEdit, String id) {
		// TODO Auto-generated method stub
		TUser user = ContextHolderUtils.getSessionUser();
		String hql = "from TBank where pstate = '0' and bankflag = '1' and userid = '"
				+ user.getId() + "'";
		List<?> list = systemDao.findByQueryHql(hql);
		List<ComboBox> temp = new ArrayList<ComboBox>();
		if (isEdit.equals("false"))
			for (int i = 0, len = list.size(); i < len; i++) {
				TBank bank = (TBank) list.get(i);
				ComboBox cb = new ComboBox();
				cb.setId(bank.getId());
				cb
						.setText(bank.getCbankname() + "[" + bank.getBanktype()
								+ "]");
				temp.add(cb);
			}
		if (isEdit.equals("true"))
			for (int i = 0, len = list.size(); i < len; i++) {
				TBank bank = (TBank) list.get(i);
				ComboBox cb = new ComboBox();
				cb.setId(bank.getId());
				cb
						.setText(bank.getCbankname() + "[" + bank.getBanktype()
								+ "]");

				if (id.equals(bank.getId()))
					cb.setSelected(true);
				temp.add(cb);
			}
		if (isEdit.equals("salary")) {
			temp.add(new ComboBox("all", "全部", false));
			for (int i = 0, len = list.size(); i < len; i++) {
				TBank bank = (TBank) list.get(i);
				ComboBox cb = new ComboBox();
				cb.setId(bank.getId());
				cb
						.setText(bank.getCbankname() + "[" + bank.getBanktype()
								+ "]");
				temp.add(cb);
			}
		}
		return temp;
	}

	public List<?> getSortType(String type) {
		// TODO Auto-generated method stub
		String hql = "from TSort where sorttype = '" + type + "'";
		List<?> list = systemDao.findByQueryHql(hql);
		List<ComboBox> temp = new ArrayList<ComboBox>();
		for (int i = 0, len = list.size(); i < len; i++) {
			TSort sort = (TSort) list.get(i);
			ComboBox cb = new ComboBox();
			cb.setId(sort.getId());
			cb.setText(sort.getSortname() + "["
					+ sort.getSorttype().toUpperCase() + "]");
			if (i == 0)
				cb.setSelected(true);
			temp.add(cb);
		}
		return temp;
	}

	public float setTotalSal(String id, String trdetype, String bankid,
			float money) {
		// TODO Auto-generated method stub
		String hql = "from TBank where userid = '" + id + "' and id = '"
				+ bankid + "'";
		List<?> list = systemDao.findByQueryHql(hql);
		float total = money;
		if (list != null && list.size() > 0) {
			TBank banksal = (TBank) list.get(0);
			total = banksal.getBankvalue();
			if ("in".equals(trdetype))
				total += money;
			else
				total -= money;

			if (total <= 0) { // 没有就为0
				total = 0;
				banksal.setBankvalue(total);
			} else
				banksal.setBankvalue(total);
			systemDao.update(banksal);
		}
		return total;
	}

	public Map<String, String> setSalary(Map<Object, Object> map) {
		// TODO Auto-generated method stub
		TSalary obj = new TSalary();
		float money = Float.valueOf(DecimalFormatUtils
				.SimpleFormat_2(oConvertUtils.getDouble(map.get("money")
						.toString(), 0)));
		float balance = setTotalSal(map.get("userid").toString(), "out", map
				.get("bankid").toString(), money);
		obj.setBalance(balance);
		obj.setBank(map.get("bankid").toString());
		obj.setTrdetype("out");
		obj.setSerialnumber(BpmisUtils.getSerialNumber());
		obj.setTradetime(DataToolsUtils.SimpleFormatTime("yyyy-MM-dd"));
		obj.setCreatetime(DataToolsUtils.SimpleFormatTime());
		obj.setSalarymoney(money);
		obj.setSummary(map.get("summary").toString());
		obj.setBorrecord("1");
		obj.setUserid(map.get("userid").toString());
		//
		String hql = "from TSort where sortname like '%借钱%' and sorttype = 'out'";
		List<?> list = systemDao.findByQueryHql(hql);
		if (list != null && list.size() > 0) {
			TSort ts = (TSort) list.get(0);
			obj.setSortId(ts.getId());
		} else {
			TSort ts = new TSort();
			ts.setSortname("生活费：借钱");
			ts.setSorttype("out");
			Serializable id = save(ts, "");
			obj.setSortId(id.toString());
		}
		Serializable id = save(obj, "salary");

		Map<String, String> rmap = HqlQuery.getHashMapsStr();
		rmap.put("salaryid", id.toString());
		rmap.put("salnum", obj.getSerialnumber());
		return rmap;
	}

	public List<?> getBankTotal() {
		// TODO Auto-generated method stub
		String sql = "SELECT t.s_bankvalue as total, t.s_cbankname as name FROM s_bank t where t.s_pstate = '0' and t.s_bankvalue != 0 order by t.s_bankvalue desc";
		List<?> list = systemDao.findByQuerySql(sql);
		return list;
	}
}
