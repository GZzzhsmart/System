package SQLservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SQLBase.SQLSentenceimp;
import SQLBean.LeaveBean;
import SQLBean.PagingBean;
import SQLBean.StudentBean;
import SQLBean.TeacherBean;

public class LeaveServlet extends HttpServlet {
	SQLSentenceimp sql = new SQLSentenceimp();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = getServletContext();
		HttpSession session = request.getSession(); 
		request.setCharacterEncoding("utf-8");
		if(request.getParameter("order")!=null){
			String order = request.getParameter("order");
			if (order.equals("delete")) {
				sql.delLeave(Integer.parseInt(request.getParameter("lon")));
				paginglist1(request, response);
				request.getRequestDispatcher("data/leavelist.jsp").forward(request, response);
			}else if (order.equals("add")) {
				List<StudentBean> studentList = sql.studentlist();
				List<TeacherBean> teacherList = sql.teacherlist();
				request.setAttribute("teacherList", teacherList);
				request.setAttribute("studentList", studentList);
				request.getRequestDispatcher("data/leaveadd.jsp").forward(request, response);
			}else if (order.equals("addsave")) {
				int sno = Integer.parseInt(request.getParameter("sno"));
				int tno = Integer.parseInt(request.getParameter("tno"));
				String cause = request.getParameter("cause");
				String ltime = request.getParameter("ltime");
				String lstate = request.getParameter("lstate");
				int day = Integer.parseInt(request.getParameter("day"));
				LeaveBean leaveBean = new LeaveBean();
				leaveBean.setSno(sno);
				leaveBean.setTno(tno);
				leaveBean.setCause(cause);
				leaveBean.setLtime(ltime);
				leaveBean.setDay(day);
				leaveBean.setLstate(lstate);
				sql.addLeave(leaveBean);
				request.setAttribute("leaveBean", leaveBean);
				paginglist(request, response);
				request.getRequestDispatcher("data/leavelist.jsp").forward(request, response);
			}else if (order.equals("update")) {
				int lon = Integer.parseInt(request.getParameter("lon"));
				LeaveBean  leaveBean = sql.findLeaveById(lon);
				List<StudentBean> studentList = sql.studentlist();
				List<TeacherBean> teacherList = sql.teacherlist();
				request.setAttribute("teacherList", teacherList);
				request.setAttribute("studentList", studentList);
				request.setAttribute("leaveBean", leaveBean);
				request.getRequestDispatcher("data/leaveupdate.jsp").forward(request, response);
			}else if (order.equals("updatesave")) {
				int lon = Integer.parseInt(request.getParameter("lon"));
				int sno = Integer.parseInt(request.getParameter("sno"));
				int tno = Integer.parseInt(request.getParameter("tno"));
				String cause = request.getParameter("cause");
				String ltime = request.getParameter("ltime");
				String lstate = request.getParameter("lstate");
				int day = Integer.parseInt(request.getParameter("day"));
				LeaveBean leaveBean = new LeaveBean();
				leaveBean.setLon(lon);
				leaveBean.setSno(sno);
				leaveBean.setTno(tno);
				leaveBean.setCause(cause);
				leaveBean.setLtime(ltime);
				leaveBean.setDay(day);
				leaveBean.setLstate(lstate);
				sql.updateLeave(leaveBean);
				paginglist(request, response);
				request.getRequestDispatcher("/data/leavelist.jsp").forward(request, response);
			}else if(order.equals("leavelist")){
				paginglist1(request, response);
				request.getRequestDispatcher("data/leavelist.jsp").forward(request, response);
			}else if(order.equals("list")){
				paginglist(request, response);
				request.getRequestDispatcher("data/leavelist.jsp").forward(request, response);
			}
		}
	}

	public void paginglist(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.barsum("select count(*) cont from leave"));
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
		List<LeaveBean> leavelist = sql.leavelist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("leavelist", leavelist);
		request.setAttribute("pager", page);
	}
	public void paginglist1(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.pagebarsum("leave"));
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
		List<LeaveBean> leavelist = sql.leavelist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("leavelist", leavelist);
		request.setAttribute("pager", page);
	}

}
