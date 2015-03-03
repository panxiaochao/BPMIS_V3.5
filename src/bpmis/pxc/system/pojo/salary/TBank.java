package bpmis.pxc.system.pojo.salary;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.pxcbpmisframework.core.common.entity.IdEntity;

@Entity
@Table(name = "s_bank")
public class TBank extends IdEntity implements Serializable {
	private String bankname;
	private String banktype;
	private String cbankname;
	private String cbanktype;
	private String pbankid;
	private String bankflag;
	private String pstate;
	private String userid;
	private float bankvalue;

	@Column(name = "s_bankflag", length = 4)
	public String getBankflag() {
		return this.bankflag;
	}

	public void setBankflag(String bankflag) {
		this.bankflag = bankflag;
	}

	@Column(name = "s_bankname", length = 20)
	public String getBankname() {
		return this.bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	@Column(name = "s_banktype", length = 10)
	public String getBanktype() {
		return this.banktype;
	}

	public void setBanktype(String banktype) {
		this.banktype = banktype;
	}

	@Column(name = "s_cbankname", length = 20)
	public String getCbankname() {
		return this.cbankname;
	}

	public void setCbankname(String cbankname) {
		this.cbankname = cbankname;
	}

	@Column(name = "s_cbanktype", length = 10)
	public String getCbanktype() {
		return this.cbanktype;
	}

	public void setCbanktype(String cbanktype) {
		this.cbanktype = cbanktype;
	}

	@Column(name = "s_pbankid", length = 32)
	public String getPbankid() {
		return this.pbankid;
	}

	public void setPbankid(String pbankid) {
		this.pbankid = pbankid;
	}

	@Column(name = "s_pstate", length = 4)
	public String getPstate() {
		return this.pstate;
	}

	public void setPstate(String pstate) {
		this.pstate = pstate;
	}

	@Column(name = "s_userid", length = 32)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "s_bankvalue", precision = 18)
	public float getBankvalue() {
		return this.bankvalue;
	}

	public void setBankvalue(float bankvalue) {
		this.bankvalue = bankvalue;
	}
}
