package SQLBase;

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


public interface SQLSentence {

	//学生表
	public abstract StudentBean studentLogin(Integer sno);
	public abstract List<StudentBean> studentlist();
	public abstract void studentdel(int sno);
	public abstract void studentupdate(StudentBean studentbean);
	public abstract void studentadd(StudentBean studentbean);
	public abstract List<StudentBean> studentlist(int pagesize, int startpage);
	public abstract StudentBean findstudent(Integer sno);
	
	
	//管理员表
	public abstract AdminBean Login(Integer ano);
	public abstract void addmanager(AdminBean manager);
	public abstract void managerupdate(AdminBean manager);
	public abstract void delmanager(int ano);
	public abstract AdminBean findmanagerById(Integer ano);
	public abstract List<AdminBean> managerlist();
	public abstract List<AdminBean> managerlist(int pagesize,int startpage);
	
	//班级表
	public abstract void addClass(ClassBean classBean);
	public abstract void updateClass(ClassBean classBean);
	public abstract void delClass(int clno);
	public abstract ClassBean findClassById(Integer clno);
	public abstract List<ClassBean> classlist();
	public abstract List<ClassBean> listclass();
	public abstract List<ClassBean> classlist(int pagesize,int startpage);
	
	//权限表
	public abstract void addQx(QxBean qxBean);
	public abstract void Qxupdate(QxBean manager);
	public abstract void delQx(int qxno);
	public abstract QxBean findQxById(Integer qxno);
	public abstract List<QxBean> qxlist();
	public abstract List<QxBean> qxlist(int pagesize,int startpage);

	//教师表
	public abstract TeacherBean teacherlogin(Integer tno);
	public abstract void addTeacher(TeacherBean teacherBean);
	public abstract void updateTeacher(TeacherBean teacherBean);
	public abstract void delTeacher(int tno);
	public abstract TeacherBean findTeacherById(Integer tno);
	public abstract List<TeacherBean> teacherlist();
	public abstract List<TeacherBean> teacherlist(int pagesize,int startpage);
	
	//课程表
	public abstract void addCourse(CourseBean courseBean);
	public abstract void updateCourse(CourseBean courseBean);
	public abstract void delCourse(int cno);
	public abstract CourseBean findCourseById(Integer cno);
	public abstract List<CourseBean> courselist();
	public abstract List<CourseBean> courselist(int pagesize,int startpage);
	
	//用户表
	public abstract LUserBean userlogin(String username);
	public abstract void addUser(LUserBean userBean);
	public abstract void updateUser(LUserBean userBean);
	public abstract void delUser(String username);
	public abstract LUserBean findUserById(String username);
	public abstract List<LUserBean> userlist();
	public abstract List<LUserBean> userlist(int pagesize,int startpage);
	
	//成绩表
	public abstract void addScore(ScoreBean scoreBean);
	public abstract void updateScore(ScoreBean scoreBean);
	public abstract void delScore(Integer sno);
	public abstract ScoreBean findScoreById(Integer sno);
	public abstract List<ScoreBean> scorelist();
	public abstract List<ScoreBean> scorelist(int pagesize,int startpage);
	
	//请假表
	public abstract void addLeave(LeaveBean leaveBean);
	public abstract void updateLeave(LeaveBean leaveBean);
	public abstract void delLeave(Integer lon);
	public abstract LeaveBean findLeaveById(Integer lon);
	public abstract List<LeaveBean> leavelist();
	public abstract List<LeaveBean> leavelist(int pagesize,int startpage);
	
	//出勤表
	public abstract void addOut(OutBean outBean);
	public abstract void updateOut(OutBean outBean);
	public abstract void delOut(Integer one);
	public abstract OutBean findOutById(Integer one);
	public abstract List<OutBean> outlist();
	public abstract List<OutBean> outlist(int pagesize,int startpage);
	
	//打卡表
	public abstract void addCard(CardBean cardBean);
	public abstract void updateCard(CardBean cardBean);
	public abstract void delCard(Integer cardid);
	public abstract CardBean findCardById(Integer cardid);
	public abstract List<CardBean> cardlist();
	public abstract List<CardBean> cardlist(int pagesize,int startpage);
}
