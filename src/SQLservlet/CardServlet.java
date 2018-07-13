package SQLservlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SQLBase.SQLSentenceimp;
import SQLBean.CardBean;
import SQLBean.CourseBean;
import SQLBean.OutBean;
import SQLBean.PagingBean;
import SQLBean.StudentBean;
import SQLBean.TeacherBean;

public class CardServlet extends HttpServlet {
	SQLSentenceimp sql = new SQLSentenceimp();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		request.setCharacterEncoding("utf-8");
		if(request.getParameter("order")!=null){
			String order = request.getParameter("order");
			if (order.equals("delete")) {
				sql.delCard(Integer.parseInt(request.getParameter("cardid")));
				paginglist1(request, response);
				request.getRequestDispatcher("data/cardlist.jsp").forward(request, response);
			}else if (order.equals("add")) {
				List<StudentBean> studentList = sql.studentlist();
				List<TeacherBean> teacherList = sql.teacherlist();
				List<CourseBean> courseList = sql.courselist();
				request.setAttribute("teacherList", teacherList);
				request.setAttribute("studentList", studentList);
				request.setAttribute("courseList", courseList);
				request.getRequestDispatcher("data/cardadd.jsp").forward(request, response);
			}else if (order.equals("addsave")) {
				int sno = Integer.parseInt(request.getParameter("sno"));
				int tno = Integer.parseInt(request.getParameter("tno"));
				int cno = Integer.parseInt(request.getParameter("cno"));
				String startTime = request.getParameter("startTime");
				String endTime = request.getParameter("endTime");
				CardBean cardBean = new CardBean();
				cardBean.setSno(sno);
				cardBean.setTno(tno);
				cardBean.setCno(cno);
				cardBean.setEndTime(endTime);
				cardBean.setStartTime(startTime);
				sql.addCard(cardBean);
				request.setAttribute("cardBean", cardBean);
				paginglist(request, response);
				request.getRequestDispatcher("data/cardlist.jsp").forward(request, response);
			}else if (order.equals("update")) {
				int cardid = Integer.parseInt(request.getParameter("cardid"));
				CardBean cardBean = sql.findCardById(cardid);
				List<StudentBean> studentList = sql.studentlist();
				List<TeacherBean> teacherList = sql.teacherlist();
				List<CourseBean> courseList = sql.courselist();
				request.setAttribute("teacherList", teacherList);
				request.setAttribute("studentList", studentList);
				request.setAttribute("courseList", courseList);
				request.setAttribute("cardBean", cardBean);
				request.getRequestDispatcher("data/cardupdate.jsp").forward(request, response);
			}else if (order.equals("updatesave")) {
				int cardid = Integer.parseInt(request.getParameter("cardid"));
				int sno = Integer.parseInt(request.getParameter("sno"));
				int tno = Integer.parseInt(request.getParameter("tno"));
				int cno = Integer.parseInt(request.getParameter("cno"));
				String startTime = request.getParameter("startTime");
				String endTime = request.getParameter("endTime");
				CardBean cardBean = new CardBean();
				cardBean.setCardid(cardid);
				cardBean.setSno(sno);
				cardBean.setTno(tno);
				cardBean.setCno(cno);
				cardBean.setEndTime(endTime);
				cardBean.setStartTime(startTime);
				sql.updateCard(cardBean);
				paginglist(request, response);
				request.getRequestDispatcher("/data/cardlist.jsp").forward(request, response);
			}else if(order.equals("cardlist")){
				paginglist1(request, response);
				request.getRequestDispatcher("data/cardlist.jsp").forward(request, response);
			}else if(order.equals("list")){
				paginglist(request, response);
				request.getRequestDispatcher("data/cardlist.jsp").forward(request, response);
			}else if(order.equals("list1")){
				paginglist(request, response);
				request.getRequestDispatcher("datainfo/cardlist.jsp").forward(request, response);
			}else if(order.equals("addlist")){
				StudentBean student = (StudentBean)request.getAttribute("student");
				List<StudentBean> studentList = sql.studentlist();
				List<TeacherBean> teacherList = sql.teacherlist();
				List<CourseBean> courseList = sql.courselist();
				request.setAttribute("teacherList", teacherList);
				request.setAttribute("studentList", studentList);
				request.setAttribute("courseList", courseList);
				request.setAttribute("student", student);
				request.getRequestDispatcher("datainfo/cardadd.jsp").forward(request, response);
			}else if(order.equals("listcard")){
				paginglist1(request, response);
				request.getRequestDispatcher("datainfo/cardlist.jsp").forward(request, response);
			}else if(order.equals("addcard")){
				//学生添加打卡记录同时新增考勤表
				int tno = Integer.parseInt(request.getParameter("tno"));
				int cno = Integer.parseInt(request.getParameter("cno"));
				int sno = Integer.parseInt(request.getParameter("sno"));
				String startTime = request.getParameter("startTime");
				String endTime = request.getParameter("endTime");
				CardBean cardBean = new CardBean();
				OutBean outBean = new OutBean();
				cardBean.setSno(sno);
				cardBean.setTno(tno);
				cardBean.setCno(cno);
				cardBean.setEndTime(endTime);
				cardBean.setStartTime(startTime);
				sql.addCard(cardBean);
				request.setAttribute("cardBean", cardBean);
				request.setAttribute("outBean", outBean);
				outBean.setSno(sno);
				outBean.setTno(tno);
				outBean.setCno(cno);
				outBean.setState("出勤");
				outBean.setOtime(new Date().toLocaleString().toString());
				sql.addOut(outBean);
				paginglist(request, response);
				request.getRequestDispatcher("datainfo/cardlist.jsp").forward(request, response);
			}
		}
	}

	public void paginglist(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.barsum("select count(*) cont from card"));
		//当前页
		String currenpage = request.getParameter("currenpage");
		//操作
		String handle = request.getParameter("handle");
		if(currenpage==null || currenpage.equals("")){
			//当前页为第一页
			page.setCurrentpage(1);
		}else {
			page.setCurrentpage(Integer.parseInt(currenpage));
		}
		if(handle==null || handle.equals("")){
			//当前页的操作
			page.setHandle("firstpage");
		}else {
			page.setHandle(handle);
		}
		List<CardBean> cardlist = sql.cardlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("cardlist", cardlist);
		request.setAttribute("pager", page);
	}
	public void paginglist1(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.pagebarsum("card"));
		//当前页
		String currenpage = request.getParameter("currenpage");
		//操作
		String handle = request.getParameter("handle");
		if(currenpage==null || currenpage.equals("")){
			//当前页为第一页
			page.setCurrentpage(1);
		}else {
			page.setCurrentpage(Integer.parseInt(currenpage));
		}
		if(handle==null || handle.equals("")){
			//当前页的操作
			page.setHandle("firstpage");
		}else {
			page.setHandle(handle);
		}
		List<CardBean> cardlist = sql.cardlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("cardlist", cardlist);
		request.setAttribute("pager", page);
	}

}
