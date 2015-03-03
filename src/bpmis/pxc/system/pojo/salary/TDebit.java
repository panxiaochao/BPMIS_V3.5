package bpmis.pxc.system.pojo.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.pxcbpmisframework.core.common.entity.IdEntity;

/**
 * 
 * @Title: TDebit.java
 * @Package bpmis.pxc.system.pojo.salary
 * @Description: TODO(借款表)
 * @author panxiaochao
 * @date 2015-1-15 下午03:49:45
 * @version V1.0
 */
@Entity
@Table(name = "s_debit")
public class TDebit extends IdEntity implements java.io.Serializable {
	private String serialnumber; // 流水号
	private String debittime; // 借款时间
	private String createtime; // 创建时间
	private String repaytime;// 还款时间
	private float debitmoney;// 借款金额
	private String debitstate;// 借款状态
	private String debituser;// 借款人
	private String debitsummary;// 借款说明
	private String salaryid; // 外键
	private String salnum;// 资金流水号
	private String userid;

	@Column(name = "s_serialnumber", length = 50)
	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	@Column(name = "s_debittime", length = 32)
	public String getDebittime() {
		return debittime;
	}

	public void setDebittime(String debittime) {
		this.debittime = debittime;
	}

	@Column(name = "s_createtime", length = 32)
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "s_repaytime", length = 32)
	public String getRepaytime() {
		return repaytime;
	}

	public void setRepaytime(String repaytime) {
		this.repaytime = repaytime;
	}

	@Column(name = "s_debitmoney", precision = 18, scale = 3)
	public float getDebitmoney() {
		return debitmoney;
	}

	public void setDebitmoney(float debitmoney) {
		this.debitmoney = debitmoney;
	}

	@Column(name = "s_debitstate", length = 4)
	public String getDebitstate() {
		return debitstate;
	}

	public void setDebitstate(String debitstate) {
		this.debitstate = debitstate;
	}

	@Column(name = "s_debituser", length = 20)
	public String getDebituser() {
		return debituser;
	}

	public void setDebituser(String debituser) {
		this.debituser = debituser;
	}

	@Column(name = "s_debitsummary", length = 100)
	public String getDebitsummary() {
		return debitsummary;
	}

	public void setDebitsummary(String debitsummary) {
		this.debitsummary = debitsummary;
	}

	@Column(name = "s_salaryid", length = 32)
	public String getSalaryid() {
		return salaryid;
	}

	public void setSalaryid(String salaryid) {
		this.salaryid = salaryid;
	}

	@Column(name = "s_salnum", length = 50)
	public String getSalnum() {
		return salnum;
	}

	public void setSalnum(String salnum) {
		this.salnum = salnum;
	}

	@Column(name = "s_userid", length = 32)
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
