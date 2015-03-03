package bpmis.pxc.system.pojo.salary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.pxcbpmisframework.core.common.entity.IdEntity;

/**
 * 
 * @Title: TCredit.java
 * @Package bpmis.pxc.system.pojo.salary
 * @Description: TODO(信用卡)
 * @author panxiaochao
 * @date 2015-1-19 上午10:59:59
 * @version V1.0
 */
@Entity
@Table(name = "s_credit")
public class TCredit extends IdEntity implements java.io.Serializable {
	private String serialnumber; // 流水号
	private String credittime; // 交易时间
	private String createtime; // 创建时间
	private float creditmoney;// 交易金额
	private String creditsummary;// 交易说明
	private String yearmoth;
	private String bankid; // 外键
	private String userid;

	private String bankname;

	@Column(name = "s_serialnumber", length = 50)
	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	@Column(name = "s_credittime", length = 32)
	public String getCredittime() {
		return credittime;
	}

	public void setCredittime(String credittime) {
		this.credittime = credittime;
	}

	@Column(name = "s_createtime", length = 32)
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "s_creditmoney", precision = 18, scale = 3)
	public float getCreditmoney() {
		return creditmoney;
	}

	public void setCreditmoney(float creditmoney) {
		this.creditmoney = creditmoney;
	}

	@Column(name = "s_creditsummary", length = 100)
	public String getCreditsummary() {
		return creditsummary;
	}

	public void setCreditsummary(String creditsummary) {
		this.creditsummary = creditsummary;
	}

	@Column(name = "s_bankid", length = 32)
	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	@Column(name = "s_yearmoth", length = 10)
	public String getYearmoth() {
		return yearmoth;
	}

	public void setYearmoth(String yearmoth) {
		this.yearmoth = yearmoth;
	}

	@Column(name = "s_bankname", length = 50)
	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	@Column(name = "s_userid", length = 32)
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
