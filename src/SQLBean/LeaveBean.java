package SQLBean;

import java.io.Serializable;
import java.util.Date;

public class LeaveBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer lon;
	private Integer sno;
	private String cause;
	private String ltime;
	private Integer day;
	private Integer tno;
	private String lstate;
	
	private String sname;
	private String tname;
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Integer getLon() {
		return lon;
	}
	public void setLon(Integer lon) {
		this.lon = lon;
	}
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getLtime() {
		return ltime;
	}
	public void setLtime(String ltime) {
		this.ltime = ltime;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getTno() {
		return tno;
	}
	public void setTno(Integer tno) {
		this.tno = tno;
	}
	public String getLstate() {
		return lstate;
	}
	public void setLstate(String lstate) {
		this.lstate = lstate;
	}
	
	
}
