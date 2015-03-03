package bpmis.pxc.system.pojo.salary;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.pxcbpmisframework.core.common.entity.IdEntity;

@Entity
@Table(name = "s_bank_salary")
public class TBankSal extends IdEntity implements Serializable {
	private String userid;
	private String bankid;
	private float total;

	@Column(name = "s_userid", length = 32)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "s_bankid", length = 32)
	public String getBankid() {
		return this.bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	@Column(name = "s_total", precision = 18, scale = 3)
	public float getTotal() {
		return this.total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
}
