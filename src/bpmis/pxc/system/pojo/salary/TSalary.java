package bpmis.pxc.system.pojo.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Persister;
import org.pxcbpmisframework.core.common.entity.IdEntity;

/**
 * 
 * @author Panxiaochao 交易明细表
 */
@Entity
@Table(name = "s_salarytable")
public class TSalary extends IdEntity implements java.io.Serializable {
	private String serialnumber; // 流水号
	private String tradetime; // 交易时间
	private String trdetype; // 收入方式，资金流向; in 收入/ out 支出
	private String createtime; // 创建时间
	private String summary; // 摘要
	private float salarymoney;
	private float pay;
	private float store;
	private String sortId; // 交易类别
	private String sortname;
	private float balance; // 余额
	private String bank; // 银行ID
	private String bankname;
	private String borrecord; // 借款记录 0：无 1：有
	private String userid;
	private String remark;

	@Column(name = "s_balance", precision = 18, scale = 3)
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	@Column(name = "s_summary", length = 150)
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "s_bank", length = 32)
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "s_tradetime", length = 50)
	public String getTradetime() {
		return tradetime;
	}

	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}

	@Column(name = "s_createtime", length = 50)
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "s_remark", length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "s_trdetype", length = 4)
	public String getTrdetype() {
		return trdetype;
	}

	public void setTrdetype(String trdetype) {
		this.trdetype = trdetype;
	}

	@Column(name = "s_userid", length = 32)
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "s_serialnumber", length = 50)
	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	@Column(name = "s_borrecord", length = 4)
	public String getBorrecord() {
		return borrecord;
	}

	public void setBorrecord(String borrecord) {
		this.borrecord = borrecord;
	}

	@Column(name = "s_bankname", length = 50)
	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	@Column(name = "s_salarymoney", precision = 18, scale = 3)
	public float getSalarymoney() {
		return salarymoney;
	}

	public void setSalarymoney(float salarymoney) {
		this.salarymoney = salarymoney;
	}

	@Column(name = "s_sortId", length = 32)
	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	@Column(name = "s_sortname", length = 50)
	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	@Transient
	public float getPay() {
		return pay;
	}

	public void setPay(float pay) {
		this.pay = pay;
	}

	@Transient
	public float getStore() {
		return store;
	}

	public void setStore(float store) {
		this.store = store;
	}

}
