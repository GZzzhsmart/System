package SQLBean;

import java.io.Serializable;

public class LUserBean implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private String username;
	
	private String password;
	
	private Integer qxno;

	private String qxname;
	
	public String getQxname() {
		return qxname;
	}

	public void setQxname(String qxname) {
		this.qxname = qxname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getQxno() {
		return qxno;
	}

	public void setQxno(Integer qxno) {
		this.qxno = qxno;
	}
	
	
}
