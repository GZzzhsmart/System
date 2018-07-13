package SQLBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import SQLBean.AdminBean;
import SQLBean.CardBean;
import SQLBean.ClassBean;
import SQLBean.CourseBean;
import SQLBean.LUserBean;
import SQLBean.LeaveBean;
import SQLBean.OutBean;
import SQLBean.QxBean;
import SQLBean.ScoreBean;
import SQLBean.StudentBean;
import SQLBean.TeacherBean;
import SQLCon.DBConn;

public class SQLSentenceimp implements SQLSentence{
	
	Connection con =DBConn.openDB();
	public int barsum(String sql){
		System.out.println(sql);
		int bar=0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				bar = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("查找记录数出错！");
		}
		return bar;
	}
	public int pagebarsum(String tablename) {
		int barsum=0;
		try {
			Statement st = con.createStatement();
			String sql ="select count(*) cnt from "+tablename;
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				barsum = rs.getInt(1);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			System.out.println("查询城市的记录总数出错！");
		}
		return barsum;
	}









	public void checkpwd(String ano, String aword ,String tablename,String id,String pwd) {
		try {
			Statement st = con.createStatement();
			String sql = "update "+tablename+" set ";
			sql+=id+"='"+aword+"'";
			sql+=" where "+pwd+"='"+ano+"'";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("更新密码出错！");
		}
	}

	

	public boolean oldpwd(String ano) {
		boolean isok = false;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select aword from admin where ano="+ano);
			if(rs.next()){
				isok=true;
			}
		} catch (SQLException e) {
			System.out.println("管理员密码查询出错！");
		}
		return isok;
	}
	
	public void studentadd(StudentBean studentbean) {
		try {
			Statement st = con.createStatement();
			String sql = "insert into student values(";
			sql+="'"+studentbean.getSno()+"',";
			sql+="'"+studentbean.getSname()+"',";
			sql+="'"+studentbean.getSex()+"',";
			sql+="'"+studentbean.getClno()+"',";
			sql+="to_date('"+studentbean.getBirth()+"','yyyy/mm/dd HH24:MI:SS')";
			sql+=")";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("学生新增出错！");
		}
		
	}

	public void studentdel(int sno) {
		try {
			Statement st = con.createStatement();
			String sql = "delete student where sno="+sno;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("删除学生出现错误！");
		}
		
	}

	//登录检查
	public StudentBean studentLogin(Integer sno) {
		StudentBean studentbean= new StudentBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from student where sno="+sno;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				studentbean.setSno(rs.getInt("sno"));;
				studentbean.setClno(rs.getInt("clno"));
				studentbean.setSname(rs.getString("sname"));
				studentbean.setSex(rs.getString("sex"));
				studentbean.setBirth(rs.getString("birth"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studentbean;
	}
		
	public List<StudentBean> studentlist() {
		List<StudentBean> studentlist = new ArrayList<StudentBean>();
		try {
			Statement st =  con.createStatement();
			String sql ="select * from student s  LEFT JOIN class c  on s.CLNO = c.CLNO";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				StudentBean studentbean = new StudentBean();
				studentbean.setSno(rs.getInt("sno"));;
				studentbean.setClno(rs.getInt("clno"));
				studentbean.setSname(rs.getString("sname"));
				studentbean.setSex(rs.getString("sex"));
				studentbean.setBirth(rs.getString("birth"));
				studentbean.setDepartment(rs.getString("department"));
				studentlist.add(studentbean);
			}
		} catch (Exception e) {
			System.out.println("查询学生列表出错！");
		}
		return studentlist;
	}

	//分页
	public List<StudentBean> studentlist(int pagesize, int startpage) {
		List<StudentBean> studentlist = new ArrayList<StudentBean>();
		try {
			Statement st =  con.createStatement();
			String sql ="select * from (select rownum rn,s.*,c.department from student s,class c where c.clno = s.clno) where rn>=("+startpage+"+1) and rn<=("+startpage+"+"+pagesize+")";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				StudentBean studentbean = new StudentBean();
				studentbean.setSno(rs.getInt("sno"));;
				studentbean.setClno(rs.getInt("clno"));
				studentbean.setSname(rs.getString("sname"));
				studentbean.setSex(rs.getString("sex"));
				studentbean.setBirth(rs.getString("birth"));
				studentbean.setDepartment(rs.getString("department"));
				studentlist.add(studentbean);
			}
		} catch (Exception e) {
			System.out.println("查询分页学生列表出错！");
		}
		return studentlist;
	}
	
	public StudentBean findstudent(Integer sno) {
		StudentBean studentbean = new StudentBean();
		try {
			Statement st =  con.createStatement();
			String sql ="select * from student where sno="+sno;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				studentbean.setSno(rs.getInt("sno"));;
				studentbean.setClno(rs.getInt("clno"));
				studentbean.setSname(rs.getString("sname"));
				studentbean.setSex(rs.getString("sex"));
				studentbean.setBirth(rs.getString("birth"));
			}
		} catch (Exception e) {
			System.out.println("查询学生列表出错！");
		}
		return studentbean;
	}


	
	public void studentupdate(StudentBean studentbean) {
		try {
			Statement st = con.createStatement();
			String sql = "update student set ";
			sql+="sno='"+studentbean.getSno()+"',";
			sql+="sname='"+studentbean.getSname()+"',";
			sql+="sex='"+studentbean.getSex()+"',";
			sql+="clno='"+studentbean.getClno()+"',";
			sql+="birth = to_date('"+studentbean.getBirth()+"','yyyy/mm/dd HH24:MI:SS') ";
			sql+="where sno="+studentbean.getSno()+"";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("学生修改出错！");
		}
	}
	
	
	
	//管理员
	public void addmanager(AdminBean manager) {
		try {
			Statement stmt = con.createStatement();
			String sql = "insert into admin values(";
			sql += "'"+manager.getAno() +"',";
			sql += "'"+manager.getAword()+"'";
			sql += ")";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//删除
	public void delmanager(int ano) {
		try {
			Statement stmt = con.createStatement();
			String sql = "delete admin where ano="+ano;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//查找
	public AdminBean findmanagerById(Integer ano) {
		AdminBean manager= new AdminBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from admin where ano="+ano;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				manager.setAno(rs.getInt("ano"));
				manager.setAword(rs.getString("aword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return manager;
	}
	
	//查询
	public List<AdminBean> managerlist() {
		List<AdminBean> list = new ArrayList<AdminBean>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from admin order by ano";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				AdminBean manager = new AdminBean();
				manager.setAno(rs.getInt("ano"));
				manager.setAword(rs.getString("aword"));
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//分页
	public List<AdminBean> managerlist(int pagesize, int startpage) {
		List<AdminBean> list = new ArrayList<AdminBean>();
		try {
			Statement st = con.createStatement();
			String sql = "select * from (select rownum rn,m.* from admin m) where rn>=("+startpage+"+1) and rn<=("+startpage+"+"+pagesize+")";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				AdminBean manager = new AdminBean();
				manager.setAno(rs.getInt("ano"));
				manager.setAword(rs.getString("aword"));
				list.add(manager);
			}
		} catch (Exception e) {
			System.out.println("查询省份城市列表出错！");
			e.printStackTrace();
		}
		return list;
	}
	

	
	//管理员登录检查
	public AdminBean Login(Integer ano) {
		AdminBean classBean= new AdminBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from admin where ano="+ano;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				classBean.setAno(rs.getInt("ano"));
				classBean.setAword(rs.getString("aword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classBean;
	}
	
	
	public void managerupdate(AdminBean manager) {
		try {
			Statement st = con.createStatement();
			String sql = "update admin set ";
			sql+="ano='"+manager.getAno()+"',";
			sql+="aword='"+manager.getAword()+"'";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("管理员修改出错！");
		}
		
	}

	//权限表
	
	public void addQx(QxBean qxBean) {
		try {
			Statement st = con.createStatement();
			String sql = "insert into qx values(";
			sql+="'"+qxBean.getQxno()+"',";
			sql+="'"+qxBean.getQxname()+"'";
			sql+=")";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("权限新增出错！");
		}
	}
	
	public void Qxupdate(QxBean manager) {
		try {
			Statement st = con.createStatement();
			String sql = "update qx set ";
			sql+="qxno='"+manager.getQxno()+"',";
			sql+="qxname='"+manager.getQxname()+"'";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("权限修改出错！");
		}
		
	}
	
	public void delQx(int qxno) {
		try {
			Statement stmt = con.createStatement();
			String sql = "delete qx where qxno="+qxno;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public QxBean findQxById(Integer qxno) {
		QxBean manager= new QxBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from qx where qxno="+qxno;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				manager.setQxno(rs.getInt("qxno"));
				manager.setQxname(rs.getString("qxname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return manager;
	}
	
	public List<QxBean> qxlist() {
		List<QxBean> list = new ArrayList<QxBean>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from qx order by qxno";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				QxBean manager = new QxBean();
				manager.setQxno(rs.getInt("qxno"));
				manager.setQxname(rs.getString("qxname"));
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<QxBean> qxlist(int pagesize, int startpage) {
		List<QxBean> list = new ArrayList<QxBean>();
		try {
			Statement st = con.createStatement();
			String sql = "select * from (select rownum rn,m.* from qx m) where rn>=("+startpage+"+1) and rn<=("+startpage+"+"+pagesize+")";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				QxBean manager = new QxBean();
				manager.setQxno(rs.getInt("qxno"));
				manager.setQxname(rs.getString("qxname"));
				list.add(manager);
			}
		} catch (Exception e) {
			System.out.println("查询权限列表出错！");
			e.printStackTrace();
		}
		return list;
	}
	

	
	
	public void addClass(ClassBean classBean) {
		try {
			Statement st = con.createStatement();
			String sql = "insert into class values(";
			sql+="'"+classBean.getClno()+"',";
			sql+="'"+classBean.getDepartment()+"',";
			sql+="'"+classBean.getTno()+"',";
			sql+="'"+classBean.getSum()+"'";
			sql+=")";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("班级新增出错！");
		}
		
	}
	
	public void updateClass(ClassBean classBean) {
		try {
			Statement stmt = con.createStatement();
			String sql = "update class set clno='";
			sql += classBean.getClno() +"',department='";
			sql += classBean.getDepartment() +"',tno='";
			sql += classBean.getTno() +"',sum='";
			sql += classBean.getSum()+"' ";
			sql += " where clno="+classBean.getClno();
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delClass(int clno) {
		try {
			Statement stmt = con.createStatement();
			String sql = "delete class where clno="+clno;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ClassBean findClassById(Integer clno) {
		ClassBean classBean = new ClassBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from class where clno="+clno;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				classBean.setClno(rs.getInt("clno"));
				classBean.setDepartment(rs.getString("department"));
				classBean.setTno(rs.getInt("tno"));
				classBean.setSum(rs.getInt("sum"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classBean;
	}
	
	public List<ClassBean> classlist() {
		List<ClassBean> list = new ArrayList<ClassBean>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from class c left join teacher t on c.tno = t.cno";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ClassBean classBean = new ClassBean();
				classBean.setClno(rs.getInt("clno"));
				classBean.setDepartment(rs.getString("department"));
				classBean.setTno(rs.getInt("tno"));
				classBean.setSum(rs.getInt("sum"));
				classBean.setTname(rs.getString("tname"));
				list.add(classBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ClassBean> listclass() {
		List<ClassBean> list = new ArrayList<ClassBean>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from class ";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ClassBean classBean = new ClassBean();
				classBean.setClno(rs.getInt("clno"));
				classBean.setDepartment(rs.getString("department"));
				classBean.setTno(rs.getInt("tno"));
				classBean.setSum(rs.getInt("sum"));
				list.add(classBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<ClassBean> classlist(int pagesize, int startpage) {
		List<ClassBean> list = new ArrayList<ClassBean>();
		try {
			Statement st = con.createStatement();
			String sql = "select * from (select rownum rn,c.*,t.tname from class c,teacher t where t.tno=c.tno) where rn>=("+startpage+"+1) and rn<=("+pagesize+"+"+startpage+")";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				ClassBean classBean = new ClassBean();
				classBean.setClno(rs.getInt("clno"));
				classBean.setDepartment(rs.getString("department"));
				classBean.setTno(rs.getInt("tno"));
				classBean.setSum(rs.getInt("sum"));
				classBean.setTname(rs.getString("tname"));
				list.add(classBean);
			}
		} catch (Exception e) {
			System.out.println("查询班级列表出错！");
			e.printStackTrace();
		}
		return list;
	}
	
	//教师表
	
	public TeacherBean teacherlogin(Integer tno) {
		TeacherBean teacherBean= new TeacherBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from teacher where tno="+tno;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				teacherBean.setTno(rs.getInt("tno"));
				teacherBean.setTname(rs.getString("tname"));
				teacherBean.setCno(rs.getInt("cno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacherBean;
	}
		
	
	public void addTeacher(TeacherBean teacherBean) {
		try {
			Statement st = con.createStatement();
			String sql = "insert into teacher values(";
			sql+="tno.nextval,'"+teacherBean.getTname()+"',";
			sql+="'"+teacherBean.getCno()+"'";
			sql+=")";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("教师新增出错！");
		}
	}
	
	public void updateTeacher(TeacherBean teacherBean) {
		try {
			Statement st = con.createStatement();
			String sql = "update teacher set ";
			sql+="tname='"+teacherBean.getTname()+"',";
			sql+="cno='"+teacherBean.getCno()+"'";
			sql+="where tno="+teacherBean.getTno()+"";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("教师修改出错！");
		}
		
	}
	
	public void delTeacher(int tno) {
		try {
			Statement stmt = con.createStatement();
			String sql = "delete teacher where tno="+tno;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public TeacherBean findTeacherById(Integer tno) {
		TeacherBean manager= new TeacherBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from teacher where tno="+tno;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				manager.setTno(rs.getInt("tno"));
				manager.setTname(rs.getString("tname"));
				manager.setCno(rs.getInt("cno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return manager;
	}
	
	public List<TeacherBean> teacherlist() {
		List<TeacherBean> list = new ArrayList<TeacherBean>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from teacher s left join COURSE c on c.cno = s.cno";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				TeacherBean manager= new TeacherBean();
				manager.setTno(rs.getInt("tno"));
				manager.setTname(rs.getString("tname"));
				manager.setCno(rs.getInt("cno"));
				manager.setCname(rs.getString("cname"));
				list.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<TeacherBean> teacherlist(int pagesize, int startpage) {
		List<TeacherBean> list = new ArrayList<TeacherBean>();
		try {
			Statement st = con.createStatement();
			String sql = "select * from (select rownum rn,s.*,c.cname from teacher s,course c where s.cno=c.cno) where rn>=("+startpage+"+1) and rn<=("+pagesize+"+"+startpage+")";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				TeacherBean manager= new TeacherBean();
				manager.setTno(rs.getInt("tno"));
				manager.setTname(rs.getString("tname"));
				manager.setCno(rs.getInt("cno"));
				manager.setCname(rs.getString("cname"));
				list.add(manager);
			}
		} catch (Exception e) {
			System.out.println("查询教师列表出错！");
			e.printStackTrace();
		}
		return list;
	}
	

	//课程表
	
	public void addCourse(CourseBean courseBean) {
		try {
			Statement st = con.createStatement();
			String sql = "insert into course values(";
			sql+="cno.nextval,";
			sql+="'"+courseBean.getCname()+"',";
			sql+="'"+courseBean.getTno()+"'";
			sql+=")";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("课程表新增出错！");
		}
		
	}
	
	public void updateCourse(CourseBean courseBean) {
		try {
			Statement stmt = con.createStatement();
			String sql = "update course set cname='";
			sql += courseBean.getCname() +"',tno='";
			sql += courseBean.getTno() +"'";
			sql += " where cno="+courseBean.getCno();
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delCourse(int cno) {
		try {
			Statement stmt = con.createStatement();
			String sql = "delete course where cno="+cno;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public CourseBean findCourseById(Integer cno) {
		CourseBean classBean = new CourseBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from course where cno="+cno;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				classBean.setCno(rs.getInt("cno"));
				classBean.setCname(rs.getString("cname"));
				classBean.setTno(rs.getInt("tno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classBean;
	}
	
	public List<CourseBean> courselist() {
		List<CourseBean> list = new ArrayList<CourseBean>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from course c left join teacher t on t.tno = c.tno";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				CourseBean classBean = new CourseBean();
				classBean.setCno(rs.getInt("cno"));
				classBean.setCname(rs.getString("cname"));
				classBean.setTno(rs.getInt("tno"));
				classBean.setTname(rs.getString("tname"));
				list.add(classBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<CourseBean> courselist(int pagesize, int startpage) {
		List<CourseBean> list = new ArrayList<CourseBean>();
		try {
			Statement st = con.createStatement();
			String sql = "select * from (select rownum rn,c.*,s.tname from course c,teacher s where s.tno=c.tno) where rn>=("+startpage+"+1) and rn<=("+pagesize+"+"+startpage+")";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				CourseBean classBean = new CourseBean();
				classBean.setCno(rs.getInt("cno"));
				classBean.setCname(rs.getString("cname"));
				classBean.setTno(rs.getInt("tno"));
				classBean.setTname(rs.getString("tname"));
				list.add(classBean);
			}
		} catch (Exception e) {
			System.out.println("查询教师列表出错！");
			e.printStackTrace();
		}
		return list;
	}
	
	//用户表
	
	public LUserBean userlogin(String username) {
		LUserBean userBean= new LUserBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select l.*,q.qxname from l_user l left join qx q on l.qxno = q.qxno where l.username ='"+username+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				userBean.setUsername(rs.getString("username"));
				userBean.setPassword(rs.getString("password"));
				userBean.setQxno(rs.getInt("qxno"));
				userBean.setQxname(rs.getString("qxname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userBean;
	}
	
	public void addUser(LUserBean userBean) {
		try {
			Statement st = con.createStatement();
			String sql = "insert into l_user values(";
			sql+="'"+userBean.getUsername()+"',";
			sql+="'"+userBean.getPassword()+"',";
			sql+="'"+userBean.getQxno()+"'";
			sql+=")";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("用户表新增出错！");
		}
	}
	
	public void updateUser(LUserBean userBean) {
		try {
			Statement stmt = con.createStatement();
			String sql = "update l_user set username='";
			sql += userBean.getUsername() +"',password='";
			sql += userBean.getPassword() +"',qxno='";
			sql += userBean.getQxno() +"'";
			sql += " where username='"+userBean.getUsername()+"'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delUser(String username) {
		try {
			Statement stmt = con.createStatement();
			String sql = "delete l_user where username="+username;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public LUserBean findUserById(String username) {
		LUserBean userBean = new LUserBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from l_user where username="+username;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				userBean.setUsername(rs.getString("username"));
				userBean.setPassword(rs.getString("password"));
				userBean.setQxno(rs.getInt("qxno"));
				userBean.setQxname(rs.getString("qxname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userBean;
	}
	
	public List<LUserBean> userlist() {
		List<LUserBean> list = new ArrayList<LUserBean>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from l_user l left join qx x on x.qxno = l.qxno";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				LUserBean userBean = new LUserBean();
				userBean.setUsername(rs.getString("username"));
				userBean.setPassword(rs.getString("password"));
				userBean.setQxno(rs.getInt("qxno"));
				userBean.setQxname(rs.getString("qxname"));
				list.add(userBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<LUserBean> userlist(int pagesize, int startpage) {
		List<LUserBean> list = new ArrayList<LUserBean>();
		try {
			Statement st = con.createStatement();
			String sql = "select * from (select rownum rn,l.*,x.qxname from l_user l,qx x where x.qxno=l.qxno) where rn>=("+startpage+"+1) and rn<=("+pagesize+"+"+startpage+")";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				LUserBean userBean = new LUserBean();
				userBean.setUsername(rs.getString("username"));
				userBean.setPassword(rs.getString("password"));
				userBean.setQxno(rs.getInt("qxno"));
				userBean.setQxname(rs.getString("qxname"));
				list.add(userBean);
			}
		} catch (Exception e) {
			System.out.println("查询用户列表出错！");
			e.printStackTrace();
		}
		return list;
	}
	
	//成绩表
	
	public void addScore(ScoreBean scoreBean) {
		try {
			Statement st = con.createStatement();
			String sql = "insert into score values(";
			sql+="'"+scoreBean.getSno()+"',";
			sql+="'"+scoreBean.getCno()+"',";
			sql+="'"+scoreBean.getGrade()+"'";
			sql+=")";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("成绩表新增出错！");
		}
		
	}
	
	public void updateScore(ScoreBean scoreBean) {
		try {
			Statement stmt = con.createStatement();
			String sql = "update score set sno='";
			sql += scoreBean.getSno() +"',cno='";
			sql += scoreBean.getCno() +"',grade='";
			sql += scoreBean.getGrade() +"'";
			sql += " where sno='"+scoreBean.getSno()+"'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delScore(Integer sno) {
		try {
			Statement stmt = con.createStatement();
			String sql = "delete score where sno="+sno;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ScoreBean findScoreById(Integer sno) {
		ScoreBean scoreBean = new ScoreBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from score where sno="+sno;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				scoreBean.setSno(rs.getInt("sno"));
				scoreBean.setCno(rs.getInt("cno"));
				scoreBean.setGrade(rs.getInt("grade"));
				scoreBean.setSname(rs.getString("sname"));
				scoreBean.setCname(rs.getString("cname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scoreBean;
	}
	
	public List<ScoreBean> scorelist() {
		List<ScoreBean> list = new ArrayList<ScoreBean>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from score s left join student t on s.sno = t.sno left join COURSE c on c.cno = s.cno";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				ScoreBean scoreBean = new ScoreBean();
				scoreBean.setSno(rs.getInt("sno"));
				scoreBean.setCno(rs.getInt("cno"));
				scoreBean.setGrade(rs.getInt("grade"));
				scoreBean.setSname(rs.getString("sname"));
				scoreBean.setCname(rs.getString("cname"));
				list.add(scoreBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ScoreBean> scorelist(int pagesize, int startpage) {
		List<ScoreBean> list = new ArrayList<ScoreBean>();
		try {
			Statement st = con.createStatement();
			String sql = "select * from (select rownum rn,o.*,s.sname,c.cname from score o,student s,course c where o.sno = s.sno and o.cno = c.cno) where rn>=("+startpage+"+1) and rn<=("+pagesize+"+"+startpage+")";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				ScoreBean scoreBean = new ScoreBean();
				scoreBean.setSno(rs.getInt("sno"));
				scoreBean.setCno(rs.getInt("cno"));
				scoreBean.setGrade(rs.getInt("grade"));
				scoreBean.setSname(rs.getString("sname"));
				scoreBean.setCname(rs.getString("cname"));
				list.add(scoreBean);
			}
		} catch (Exception e) {
			System.out.println("查询成绩列表出错！");
			e.printStackTrace();
		}
		return list;
	}
	
	//请假表
	
	public void addLeave(LeaveBean leaveBean) {
		try {
			Statement st = con.createStatement();
			String sql = "insert into leave values(";
			sql+="lon.nextval,";
			sql+="'"+leaveBean.getSno()+"',";
			sql+="'"+leaveBean.getCause()+"',";
			sql+="to_date('"+leaveBean.getLtime()+"','yyyy/mm/dd HH24:MI:SS'),";
			sql+="'"+leaveBean.getDay()+"',";
			sql+="'"+leaveBean.getTno()+"',";
			sql+="'"+leaveBean.getLstate()+"'";
			sql+=")";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("请假表新增出错！");
		}
		
	}
	
	public void updateLeave(LeaveBean leaveBean) {
		try {
			Statement stmt = con.createStatement();
			String sql = "update leave set ";
			sql+="sno='"+leaveBean.getSno()+"',";
			sql+="cause='"+leaveBean.getCause()+"',";
			sql+="day='"+leaveBean.getDay()+"',";
			sql+="tno='"+leaveBean.getTno()+"',";
			sql+="lstate='"+leaveBean.getLstate()+"',";
			sql+="ltime = to_date('"+leaveBean.getLtime()+"','yyyy/mm/dd HH24:MI:SS') ";
			sql+="where lon="+leaveBean.getLon()+"";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delLeave(Integer lon) {
		try {
			Statement stmt = con.createStatement();
			String sql = "delete leave where lon="+lon;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public LeaveBean findLeaveById(Integer lon) {
		LeaveBean leaveBean = new LeaveBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from leave where lon="+lon;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				leaveBean.setLon(rs.getInt("lon"));
				leaveBean.setSno(rs.getInt("sno"));
				leaveBean.setCause(rs.getString("cause"));
				leaveBean.setLtime(rs.getString("ltime"));
				leaveBean.setDay(rs.getInt("day"));
				leaveBean.setTno(rs.getInt("tno"));
				leaveBean.setLstate(rs.getString("lstate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return leaveBean;
	}
	
	public List<LeaveBean> leavelist() {
		List<LeaveBean> list = new ArrayList<LeaveBean>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from leave l left join student s on s.sno = l.sno left join teacher t on t.tno = l.tno";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				LeaveBean leaveBean = new LeaveBean();
				leaveBean.setLon(rs.getInt("lon"));
				leaveBean.setSno(rs.getInt("sno"));
				leaveBean.setCause(rs.getString("cause"));
				leaveBean.setLtime(rs.getString("ltime"));
				leaveBean.setDay(rs.getInt("day"));
				leaveBean.setTno(rs.getInt("tno"));
				leaveBean.setLstate(rs.getString("lstate"));
				leaveBean.setSname(rs.getString("sname"));
				leaveBean.setTname(rs.getString("tname"));
				list.add(leaveBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<LeaveBean> leavelist(int pagesize, int startpage) {
		List<LeaveBean> list = new ArrayList<LeaveBean>();
		try {
			Statement st = con.createStatement();
			String sql = "select * from (select rownum rn,l.*,s.sname,t.tname from leave l,student s,teacher t where l.sno = s.sno and l.tno = t.tno) where rn>=("+startpage+"+1) and rn<=("+pagesize+"+"+startpage+")";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				LeaveBean leaveBean = new LeaveBean();
				leaveBean.setLon(rs.getInt("lon"));
				leaveBean.setSno(rs.getInt("sno"));
				leaveBean.setCause(rs.getString("cause"));
				leaveBean.setLtime(rs.getString("ltime"));
				leaveBean.setDay(rs.getInt("day"));
				leaveBean.setTno(rs.getInt("tno"));
				leaveBean.setLstate(rs.getString("lstate"));
				leaveBean.setSname(rs.getString("sname"));
				leaveBean.setTname(rs.getString("tname"));
				list.add(leaveBean);
			}
		} catch (Exception e) {
			System.out.println("查询请假列表出错！");
			e.printStackTrace();
		}
		return list;
	}
	
	//出勤表
	
	public void addOut(OutBean outBean) {
		try {
			Statement st = con.createStatement();
			String sql = "insert into out values(";
			sql+="ono.nextval,";
			sql+="'"+outBean.getSno()+"',";
			sql+="'"+outBean.getCno()+"',";
			sql+="to_date('"+outBean.getOtime()+"','yyyy/mm/dd HH24:MI:SS'),";
			sql+="'"+outBean.getState()+"',";
			sql+="'"+outBean.getTno()+"'";
			sql+=")";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("出勤表新增出错！");
		}
		
	}
	
	public void updateOut(OutBean outBean) {
		try {
			Statement stmt = con.createStatement();
			String sql = "update out set ";
			sql+="sno='"+outBean.getSno()+"',";
			sql+="cno='"+outBean.getCno()+"',";
			sql+="state='"+outBean.getState()+"',";
			sql+="tno='"+outBean.getTno()+"',";
			sql+="otime = to_date('"+outBean.getOtime()+"','yyyy/mm/dd HH24:MI:SS') ";
			sql+="where ono="+outBean.getOno()+"";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delOut(Integer one) {
		try {
			Statement stmt = con.createStatement();
			String sql = "delete out where ono="+one;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public OutBean findOutById(Integer one) {
		OutBean outBean = new OutBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from out where ono="+one;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				outBean.setOno(rs.getInt("ono"));
				outBean.setSno(rs.getInt("sno"));
				outBean.setCno(rs.getInt("cno"));
				outBean.setOtime(rs.getString("otime"));
				outBean.setState(rs.getString("state"));
				outBean.setTno(rs.getInt("tno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return outBean;
	}
	
	public List<OutBean> outlist() {
		List<OutBean> list = new ArrayList<OutBean>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from out o left join student s on s.sno = o.sno left join course c on c.cno = o.cno left join teacher t on t.tno = o.tno";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				OutBean outBean = new OutBean();
				outBean.setOno(rs.getInt("ono"));
				outBean.setSno(rs.getInt("sno"));
				outBean.setCno(rs.getInt("cno"));
				outBean.setOtime(rs.getString("otime"));
				outBean.setState(rs.getString("state"));
				outBean.setTno(rs.getInt("tno"));
				outBean.setSname(rs.getString("sname"));
				outBean.setCname(rs.getString("cname"));
				outBean.setTname(rs.getString("tname"));
				list.add(outBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<OutBean> outlist(int pagesize, int startpage) {
		List<OutBean> list = new ArrayList<OutBean>();
		try {
			Statement st = con.createStatement();
			String sql = "select * from (select rownum rn,o.*,s.sname,t.tname,c.cname from out o,student s,teacher t,course c where o.sno = s.sno and o.tno = t.tno and o.cno = c.cno) where rn>=("+startpage+"+1) and rn<=("+pagesize+"+"+startpage+")";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				OutBean outBean = new OutBean();
				outBean.setOno(rs.getInt("ono"));
				outBean.setSno(rs.getInt("sno"));
				outBean.setCno(rs.getInt("cno"));
				outBean.setOtime(rs.getString("otime"));
				outBean.setState(rs.getString("state"));
				outBean.setTno(rs.getInt("tno"));
				outBean.setSname(rs.getString("sname"));
				outBean.setCname(rs.getString("cname"));
				outBean.setTname(rs.getString("tname"));
				list.add(outBean);
			}
		} catch (Exception e) {
			System.out.println("查询出勤列表出错！");
			e.printStackTrace();
		}
		return list;
	}
	
	//打卡表
	
	public void addCard(CardBean cardBean) {
		try {
			Statement st = con.createStatement();
			String sql = "insert into card values(";
			sql+="card_id.nextval,";
			sql+="'"+cardBean.getSno()+"',";
			sql+="'"+cardBean.getTno()+"',";
			sql+="'"+cardBean.getCno()+"',";
			sql+="to_date('"+cardBean.getStartTime()+"','yyyy/mm/dd HH24:MI:SS'), ";
			sql+="to_date('"+cardBean.getEndTime()+"','yyyy/mm/dd HH24:MI:SS') ";
			sql+=")";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			System.out.println("打卡表新增出错！");
		}
	}
	
	public void updateCard(CardBean cardBean) {
		try {
			Statement stmt = con.createStatement();
			String sql = "update card set ";
			sql+="sno='"+cardBean.getSno()+"',";
			sql+="cno='"+cardBean.getCno()+"',";
			sql+="tno='"+cardBean.getTno()+"',";
			sql+="start_time = to_date('"+cardBean.getStartTime()+"','yyyy/mm/dd HH24:MI:SS'), ";
			sql+="end_time = to_date('"+cardBean.getEndTime()+"','yyyy/mm/dd HH24:MI:SS') ";
			sql+="where card_id='"+cardBean.getCardid()+"'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void delCard(Integer cardid) {
		try {
			Statement stmt = con.createStatement();
			String sql = "delete card where card_id="+cardid;
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public CardBean findCardById(Integer cardid) {
		CardBean cardBean = new CardBean();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from card where card_id="+cardid;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				cardBean.setCardid(rs.getInt("card_id"));
				cardBean.setCno(rs.getInt("cno"));
				cardBean.setSno(rs.getInt("sno"));
				cardBean.setTno(rs.getInt("tno"));
				cardBean.setStartTime(rs.getString("start_time"));
				cardBean.setEndTime(rs.getString("end_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cardBean;
	}
	
	
	
	
	public List<CardBean> cardlist() {
		List<CardBean> list = new ArrayList<CardBean>();
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from card o left join STUDENT s on s.sno = o.sno left join COURSE c on c.cno = o.cno left join teacher t on t.tno = o.tno";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				CardBean cardBean = new CardBean();
				cardBean.setCardid(rs.getInt("card_id"));
				cardBean.setCno(rs.getInt("cno"));
				cardBean.setSno(rs.getInt("sno"));
				cardBean.setTno(rs.getInt("tno"));
				cardBean.setStartTime(rs.getString("start_time"));
				cardBean.setEndTime(rs.getString("end_time"));
				cardBean.setCname(rs.getString("cname"));
				cardBean.setSname(rs.getString("sname"));
				cardBean.setTname(rs.getString("tname"));
				list.add(cardBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<CardBean> cardlist(int pagesize, int startpage) {
		List<CardBean> list = new ArrayList<CardBean>();
		try {
			Statement st = con.createStatement();
			String sql = "select * from (select rownum rn,o.*,s.sname,t.tname,c.cname from card o,student s,teacher t,COURSE c where o.sno = s.sno and o.tno = t.tno and o.cno = c.cno) where rn>=("+startpage+"+1) and rn<=("+pagesize+"+"+startpage+")";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				CardBean cardBean = new CardBean();
				cardBean.setCardid(rs.getInt("card_id"));
				cardBean.setCno(rs.getInt("cno"));
				cardBean.setSno(rs.getInt("sno"));
				cardBean.setTno(rs.getInt("tno"));
				cardBean.setStartTime(rs.getString("start_time"));
				cardBean.setEndTime(rs.getString("end_time"));
				cardBean.setCname(rs.getString("cname"));
				cardBean.setSname(rs.getString("sname"));
				cardBean.setTname(rs.getString("tname"));
				list.add(cardBean);
			}
		} catch (Exception e) {
			System.out.println("查询打卡列表出错！");
			e.printStackTrace();
		}
		return list;
	}
}
	
	
