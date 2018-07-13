package SQLBean;

import java.io.Serializable;

public class ClassBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer clno;
	private String department;
	private Integer tno;
	private Integer sum;
	
	private String tname;
	
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Integer getClno() {
		return clno;
	}
	public void setClno(Integer clno) {
		this.clno = clno;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getTno() {
		return tno;
	}
	public void setTno(Integer tno) {
		this.tno = tno;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	
}
