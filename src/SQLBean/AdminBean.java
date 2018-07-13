package SQLBean;

import java.io.Serializable;

public class AdminBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer ano;
	private String aword;
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public String getAword() {
		return aword;
	}
	public void setAword(String aword) {
		this.aword = aword;
	}
	
}
