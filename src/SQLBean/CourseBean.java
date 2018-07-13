package SQLBean;

import java.io.Serializable;

public class CourseBean implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Integer cno;
	
	private String cname;
	
	private Integer tno;

	private String tname;
	
	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Integer getCno() {
		return cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getTno() {
		return tno;
	}

	public void setTno(Integer tno) {
		this.tno = tno;
	}
	
}
