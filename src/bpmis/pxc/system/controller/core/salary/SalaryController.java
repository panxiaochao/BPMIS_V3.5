package bpmis.pxc.system.controller.core.salary;

import bpmis.pxc.system.controller.core.BaseController;
import bpmis.pxc.system.pojo.TUser;
import bpmis.pxc.system.pojo.salary.TBank;
import bpmis.pxc.system.pojo.salary.TCredit;
import bpmis.pxc.system.pojo.salary.TDebit;
import bpmis.pxc.system.pojo.salary.TSalary;
import bpmis.pxc.system.pojo.salary.TSort;
import bpmis.pxc.system.service.SalaryService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.pxcbpmisframework.core.common.qbc.CriteriaQuery;
import org.pxcbpmisframework.core.common.qbc.HqlQuery;
import org.pxcbpmisframework.core.constant.Globals;
import org.pxcbpmisframework.core.json.AjaxJson;
import org.pxcbpmisframework.core.util.BpmisUtils;
import org.pxcbpmisframework.core.util.ContextHolderUtils;
import org.pxcbpmisframework.core.util.DataToolsUtils;
import org.pxcbpmisframework.core.util.DecimalFormatUtils;
import org.pxcbpmisframework.core.util.JSONHelper;
import org.pxcbpmisframework.core.util.oConvertUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping( { "/salary" })
public class SalaryController extends BaseController {
	private static final Logger logger = Logger
			.getLogger(SalaryController.class);
	@Resource
	private SalaryService salaryService;

	@RequestMapping( { "/salarylist" })
	@ResponseBody
	public Map<?, ?> getList(HttpServletRequest request) {
		TUser user = ContextHolderUtils.getSessionUser();
		String pageNum = request.getParameter("page");
		String rows = request.getParameter("rows");

		String selectText = request.getParameter("selectText");
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");

		CriteriaQuery cq = new CriteriaQuery();

		Map<Object, Object> maporder = HqlQuery.getHashMapsObj();
		maporder.put("desc", "createtime");

		Map<Object, String[]> mapparms = HqlQuery.getHashMapsObjs();
		mapparms.put("userid", new String[] { HqlQuery.Restrictions_eq,
				user.getId() });
		if ((!"all".equals(selectText)) && (selectText != null)) {
			mapparms.put("bank", new String[] { HqlQuery.Restrictions_like,
					"%" + selectText + "%" });
		}
		if ((!"".equals(starttime)) && (starttime != null)
				&& (!"".equals(endtime)) && (endtime != null)) {
			mapparms.put("tradetime", new String[] {
					HqlQuery.Restrictions_between, starttime, endtime });
		}
		cq.setMapparms(mapparms);

		cq.setMaporder(maporder);
		if (!oConvertUtils.isEmpty(pageNum)) {
			cq.setCurrentPage(oConvertUtils.getInt(pageNum));
			cq.setPageSize(oConvertUtils.getInt(rows));
		}
		cq = this.salaryService.getList(TSalary.class, cq, "salary");
		Map<Object, Object> map = new HashMap();
		map.put("rows", cq.getReaults());
		map.put("total", Integer.valueOf(cq.getTotalRecords()));
		return map;
	}

	@RequestMapping( { "/salarybank/{id}" })
	@ResponseBody
	public List<?> getSalaryBank(@PathVariable String id) {
		String isEdit = "false";
		if (!id.equals("null")) {
			isEdit = "true";
		}
		if (id.equals("salary")) {
			isEdit = "salary";
		}
		List<?> list = this.salaryService.getSalaryBank(isEdit, id);
		return list;
	}

	@RequestMapping( { "/sorttype/{type}" })
	@ResponseBody
	public List<?> getSortType(@PathVariable String type) {
		return this.salaryService.getSortType(type);
	}

	@RequestMapping(value = { "savesalary" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson SavePojo_Salary(HttpServletRequest req, TSalary obj) {
		AjaxJson ajx = new AjaxJson();
		TUser user = ContextHolderUtils.getSessionUser();
		float money = Float.valueOf(req.getParameter("money")).floatValue();
		money = Float.valueOf(DecimalFormatUtils.SimpleFormat_2(money))
				.floatValue();

		float balance = this.salaryService.setTotalSal(user.getId(), obj
				.getTrdetype(), obj.getBank(), money);
		obj.setBalance(balance);
		obj.setSerialnumber(BpmisUtils.getSerialNumber());
		obj.setCreatetime(DataToolsUtils.SimpleFormatTime());
		obj.setSalarymoney(money);
		obj.setBorrecord("0");
		obj.setUserid(user.getId());
		this.salaryService.save(obj, "salary");
		ajx.setMsg("添加账单成功！");
		return ajx;
	}

	@RequestMapping( { "/salsort" })
	@ResponseBody
	public Map<?, ?> getList_sort(HttpServletRequest request) {
		String pageNum = request.getParameter("page");
		String rows = request.getParameter("rows");

		String selectText = request.getParameter("selectText");
		CriteriaQuery cq = new CriteriaQuery();

		Map<Object, String[]> mapparms = HqlQuery.getHashMapsObjs();
		if ((!"all".equals(selectText)) && (selectText != null)) {
			mapparms.put("sorttype", new String[] { HqlQuery.Restrictions_eq,
					selectText });
		}
		cq.setMapparms(mapparms);

		Map<Object, Object> maporder = HqlQuery.getHashMapsObj();
		maporder.put("desc", "sorttype");
		cq.setMaporder(maporder);
		if (!oConvertUtils.isEmpty(pageNum)) {
			cq.setCurrentPage(oConvertUtils.getInt(pageNum));
			cq.setPageSize(oConvertUtils.getInt(rows));
		}
		cq = this.salaryService.getList(TSort.class, cq, "");
		Map<Object, Object> map = new HashMap();
		map.put("rows", cq.getReaults());
		map.put("total", Integer.valueOf(cq.getTotalRecords()));
		return map;
	}

	@RequestMapping(value = { "savesort" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson SavePojo_Sort(TSort obj) {
		AjaxJson ajx = new AjaxJson();
		this.salaryService.save(obj, "");
		ajx.setMsg("添加账单类别成功！");
		return ajx;
	}

	@RequestMapping(value = { "/updatesort" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson updatePojo_sort(HttpServletRequest req, TSort obj) {
		AjaxJson ajx = new AjaxJson();
		try {
			this.salaryService.update(obj);
			ajx.setMsg("修改类别成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.salaryService.addLogger("类别update失败！",
					Globals.Logger_Leavel_ERROR, Globals.Logger_Type_DELETE);
			ajx.setMsg("类别update失败！");
			ajx.setSuccess(false);
		}
		return ajx;
	}

	@RequestMapping( { "/salbank" })
	@ResponseBody
	public Map<?, ?> getList_bank(HttpServletRequest request) {
		String pageNum = request.getParameter("page");
		String rows = request.getParameter("rows");
		CriteriaQuery cq = new CriteriaQuery();

		Map<Object, Object> maporder = HqlQuery.getHashMapsObj();

		cq.setMaporder(maporder);

		Map<Object, String[]> mapparms = HqlQuery.getHashMapsObjs();
		mapparms.put("pstate", new String[] { HqlQuery.Restrictions_eq, "1" });
		cq.setMapparms(mapparms);
		if (!oConvertUtils.isEmpty(pageNum)) {
			cq.setCurrentPage(oConvertUtils.getInt(pageNum));
			cq.setPageSize(oConvertUtils.getInt(rows));
		}
		cq = this.salaryService.getList(TBank.class, cq, "");
		Map<Object, Object> map = new HashMap();
		map.put("rows", cq.getReaults());
		map.put("total", Integer.valueOf(cq.getTotalRecords()));
		return map;
	}

	@RequestMapping( { "/csalbank" })
	@ResponseBody
	public Map<?, ?> getList_cbank(HttpServletRequest request) {
		TUser user = ContextHolderUtils.getSessionUser();
		String pageNum = request.getParameter("page");
		String rows = request.getParameter("rows");
		CriteriaQuery cq = new CriteriaQuery();

		Map<Object, Object> maporder = HqlQuery.getHashMapsObj();

		cq.setMaporder(maporder);

		Map<Object, String[]> mapparms = HqlQuery.getHashMapsObjs();
		mapparms.put("pstate", new String[] { HqlQuery.Restrictions_eq, "0" });
		mapparms.put("userid", new String[] { HqlQuery.Restrictions_eq,
				user.getId() });
		cq.setMapparms(mapparms);
		if (!oConvertUtils.isEmpty(pageNum)) {
			cq.setCurrentPage(oConvertUtils.getInt(pageNum));
			cq.setPageSize(oConvertUtils.getInt(rows));
		}
		cq = this.salaryService.getList(TBank.class, cq, "");
		Map<Object, Object> map = new HashMap();
		map.put("rows", cq.getReaults());
		map.put("total", Integer.valueOf(cq.getTotalRecords()));
		return map;
	}

	@RequestMapping(value = { "savebank/{type}" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson SavePojo_Bank(@PathVariable String type, TBank obj,
			HttpServletRequest req) {
		AjaxJson ajx = new AjaxJson();
		if (type.equals("c")) {
			TUser user = ContextHolderUtils.getSessionUser();
			float money = Float.valueOf(req.getParameter("money")).floatValue();
			money = Float.valueOf(DecimalFormatUtils.SimpleFormat_2(money))
					.floatValue();
			TBank temp = (TBank) this.salaryService.getClassById(TBank.class,
					obj.getPbankid());
			obj.setBankname(temp.getBankname());
			obj.setBanktype(temp.getBanktype());
			obj.setUserid(user.getId());
			obj.setBankvalue(money);
		}
		this.salaryService.save(obj, "");
		ajx.setMsg("添加银行卡种类成功！");
		return ajx;
	}

	@RequestMapping(value = { "/updatebank/{type}" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson updatePojo_bank(HttpServletRequest req, TBank obj,
			@PathVariable String type) {
		AjaxJson ajx = new AjaxJson();
		try {
			if (type.equals("cbank")) {
				TUser user = ContextHolderUtils.getSessionUser();
				float money = Float.valueOf(req.getParameter("money"))
						.floatValue();
				money = Float.valueOf(DecimalFormatUtils.SimpleFormat_2(money))
						.floatValue();
				TBank temp = (TBank) this.salaryService.getClassById(
						TBank.class, obj.getPbankid());
				obj.setBankname(temp.getBankname());
				obj.setBanktype(temp.getBanktype());
				obj.setUserid(user.getId());
				obj.setBankvalue(money);
			}
			this.salaryService.update(obj);
			ajx.setMsg("修改银行卡成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.salaryService.addLogger("银行卡update失败！",
					Globals.Logger_Leavel_ERROR, Globals.Logger_Type_DELETE);
			ajx.setMsg("银行卡update失败！");
			ajx.setSuccess(false);
		}
		return ajx;
	}

	@RequestMapping( { "/pbank/{id}" })
	@ResponseBody
	public List<?> getBank(@PathVariable String id) {
		boolean isEdit = false;
		if (!id.equals("null")) {
			isEdit = true;
		}
		List<?> list = this.salaryService.getPbank(isEdit, id);
		return list;
	}

	@RequestMapping( { "/creditlist" })
	@ResponseBody
	public Map<?, ?> getList_credit(HttpServletRequest request) {
		TUser user = ContextHolderUtils.getSessionUser();
		String pageNum = request.getParameter("page");
		String rows = request.getParameter("rows");
		CriteriaQuery cq = new CriteriaQuery();

		Map<Object, Object> maporder = HqlQuery.getHashMapsObj();
		maporder.put("desc", "createtime");
		cq.setMaporder(maporder);

		Map<Object, String[]> mapparms = HqlQuery.getHashMapsObjs();
		mapparms.put("userid", new String[] { HqlQuery.Restrictions_eq,
				user.getId() });
		cq.setMapparms(mapparms);
		if (!oConvertUtils.isEmpty(pageNum)) {
			cq.setCurrentPage(oConvertUtils.getInt(pageNum));
			cq.setPageSize(oConvertUtils.getInt(rows));
		}
		cq = this.salaryService.getList(TCredit.class, cq, "credit");
		Map<Object, Object> map = new HashMap();
		map.put("rows", cq.getReaults());
		map.put("total", Integer.valueOf(cq.getTotalRecords()));
		return map;
	}

	@RequestMapping(value = { "savecredit/{type}" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson SavePojo_Credit(@PathVariable String type, TCredit obj,
			HttpServletRequest req) {
		AjaxJson ajx = new AjaxJson();
		if (type.equals("s")) {
			TUser user = ContextHolderUtils.getSessionUser();
			float money = Float.valueOf(req.getParameter("money")).floatValue();
			money = Float.valueOf(DecimalFormatUtils.SimpleFormat_2(money))
					.floatValue();
			obj.setSerialnumber(BpmisUtils.getSerialNumber());
			obj.setUserid(user.getId());
			obj.setCreditmoney(money);
			obj.setCreatetime(DataToolsUtils.SimpleFormatTime());
			obj.setYearmoth(BpmisUtils.getYearMounth(obj.getCredittime()));
		}
		this.salaryService.save(obj, "credit");
		ajx.setMsg("添加信用卡记录成功！");
		return ajx;
	}

	@RequestMapping( { "/credit/{id}" })
	@ResponseBody
	public List<?> getCredit(@PathVariable String id) {
		boolean isEdit = false;
		if (!id.equals("null")) {
			isEdit = true;
		}
		List<?> list = this.salaryService.getCredit(isEdit, id);
		return list;
	}

	@RequestMapping( { "/debitlist" })
	@ResponseBody
	public Map<?, ?> getList_debit(HttpServletRequest request) {
		TUser user = ContextHolderUtils.getSessionUser();
		String pageNum = request.getParameter("page");
		String rows = request.getParameter("rows");
		CriteriaQuery cq = new CriteriaQuery();

		Map<Object, Object> maporder = HqlQuery.getHashMapsObj();
		maporder.put("desc", "createtime");
		cq.setMaporder(maporder);

		Map<Object, String[]> mapparms = HqlQuery.getHashMapsObjs();
		mapparms.put("userid", new String[] { HqlQuery.Restrictions_eq,
				user.getId() });
		cq.setMapparms(mapparms);
		if (!oConvertUtils.isEmpty(pageNum)) {
			cq.setCurrentPage(oConvertUtils.getInt(pageNum));
			cq.setPageSize(oConvertUtils.getInt(rows));
		}
		cq = this.salaryService.getList(TDebit.class, cq, "debit");
		Map<Object, Object> map = new HashMap();
		map.put("rows", cq.getReaults());
		map.put("total", Integer.valueOf(cq.getTotalRecords()));
		return map;
	}

	@RequestMapping(value = { "savedebit" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson SavePojo_Debit(HttpServletRequest req, TDebit obj) {
		AjaxJson ajx = new AjaxJson();
		TUser user = ContextHolderUtils.getSessionUser();
		float money = Float.valueOf(req.getParameter("money")).floatValue();
		money = Float.valueOf(DecimalFormatUtils.SimpleFormat_2(money))
				.floatValue();
		Map<Object, Object> map = HqlQuery.getHashMapsObj();
		map.put("userid", user.getId());
		map.put("bankid", req.getParameter("bank"));
		map.put("money", Float.valueOf(money));
		map.put("summary", obj.getDebituser() + "，借钱 " + money + " 原因："
				+ obj.getDebitsummary());

		Map<String, String> rmap = this.salaryService.setSalary(map);
		obj.setSerialnumber(BpmisUtils.getSerialNumber());
		obj.setCreatetime(DataToolsUtils.SimpleFormatTime());
		obj.setDebitmoney(money);
		obj.setDebitstate("1");
		obj.setUserid(user.getId());
		obj.setSalaryid((String) rmap.get("salaryid"));
		obj.setSalnum((String) rmap.get("salnum"));
		this.salaryService.save(obj, "");
		ajx.setMsg("添加借款记录成功！");
		return ajx;
	}

	@RequestMapping(value = { "repayment" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson RepayMent(HttpServletRequest req) {
		AjaxJson ajx = new AjaxJson();
		String id = req.getParameter("id");
		TDebit obj = (TDebit) this.salaryService.getClassById(TDebit.class, id);
		obj.setDebitstate("2");
		obj.setRepaytime(DataToolsUtils.SimpleFormatTime());
		this.salaryService.update(obj);
		ajx.setMsg("添加借款记录成功！");
		return ajx;
	}

	@RequestMapping(value = { "/getbanktotal" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public String getBankTotal() {
		List<?> list = this.salaryService.getBankTotal();
		return JSONHelper.collectionTojson(list);
	}

	@RequestMapping(value = { "delete/{type}" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public AjaxJson deletePojo(HttpServletRequest req, @PathVariable String type) {
		AjaxJson ajx = new AjaxJson();
		try {
			String ids = req.getParameter("id");
			if (type.equals("sort")) {
				this.salaryService.deleteAll(TSort.class, ids.split(";"));
			}
			if (type.equals("bank")) {
				this.salaryService.deleteAll(TBank.class, ids.split(";"));
			}
			if (type.equals("credit")) {
				this.salaryService.deleteAll(TCredit.class, ids.split(";"));
			}
			if (type.equals("salary")) {
				this.salaryService.deleteAll(TSalary.class, ids.split(";"));
			}
			if (type.equals("debit")) {
				this.salaryService.deleteAll(TDebit.class, ids.split(";"));
			}
			ajx.setMsg("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			this.salaryService.addLogger("账单删除失败！",
					Globals.Logger_Leavel_ERROR, Globals.Logger_Type_DELETE);
			ajx.setMsg("删除失败！");
			ajx.setSuccess(false);
		}
		return ajx;
	}

	@RequestMapping( { "/pojo/{type}/{id}" })
	public ModelAndView getSinglePojo(@PathVariable String type,
			@PathVariable String id, ModelMap map) {
		if (type.equals("sort")) {
			TSort obj = (TSort) this.salaryService
					.getClassById(TSort.class, id);
			map.put("pojo", obj);
			ToUrl = "commen/salary/sortedit";
		}
		if ((type.equals("bank")) || (type.equals("cbank"))) {
			TBank obj = (TBank) this.salaryService
					.getClassById(TBank.class, id);
			map.put("pojo", obj);
			if (type.equals("bank")) {
				ToUrl = "commen/salary/bankedit";
			} else {
				ToUrl = "commen/salary/cbankedit";
			}
		}
		return new ModelAndView(ToUrl, map);
	}
}
