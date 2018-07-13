package SQLBean;

import java.io.Serializable;

public class QxBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer qxno;
	private String qxname;
	public Integer getQxno() {
		return qxno;
	}
	public void setQxno(Integer qxno) {
		this.qxno = qxno;
	}
	public String getQxname() {
		return qxname;
	}
	public void setQxname(String qxname) {
		this.qxname = qxname;
	}
	
}
