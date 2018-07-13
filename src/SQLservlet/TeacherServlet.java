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
import SQLBean.CourseBean;
import SQLBean.PagingBean;
import SQLBean.TeacherBean;

public class TeacherServlet extends HttpServlet {
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
				sql.delTeacher(Integer.parseInt(request.getParameter("tno")));
				paginglist1(request, response);
				request.getRequestDispatcher("data/teacherlist.jsp").forward(request, response);
			}else if (order.equals("add")) {
				List<CourseBean> courseList = sql.courselist();
				request.setAttribute("courseList", courseList);
				request.getRequestDispatcher("data/teacheradd.jsp").forward(request, response);
			}else if (order.equals("addsave")) {
				String tname = request.getParameter("tname");
				int cno = Integer.parseInt(request.getParameter("cno"));
				TeacherBean teacherBean = new TeacherBean();
				teacherBean.setTname(tname);
				teacherBean.setCno(cno);
				sql.addTeacher(teacherBean);
				request.setAttribute("teacherBean", teacherBean);
				paginglist(request, response);
				request.getRequestDispatcher("data/teacherlist.jsp").forward(request, response);
			}else if (order.equals("update")) {
				String tno = request.getParameter("tno");
				TeacherBean  teacherbean = sql.findTeacherById(Integer.parseInt(tno));
				List<CourseBean> courseList = sql.courselist();
				request.setAttribute("courseList", courseList);
				request.setAttribute("teacherbean", teacherbean);
				request.getRequestDispatcher("data/teacherupdate.jsp").forward(request, response);
			}else if (order.equals("updatesave")) {
				String tname = request.getParameter("tname");
				int cno = Integer.parseInt(request.getParameter("cno"));
				int tno = Integer.parseInt(request.getParameter("tno"));
				TeacherBean teacherBean = new TeacherBean();
				teacherBean.setTno(tno);
				teacherBean.setTname(tname);
				teacherBean.setCno(cno);
				sql.updateTeacher(teacherBean);
				paginglist(request, response);
				request.getRequestDispatcher("/data/teacherlist.jsp").forward(request, response);
			}else if(order.equals("teacherlist")){
				paginglist1(request, response);
				request.getRequestDispatcher("data/teacherlist.jsp").forward(request, response);
			}else if(order.equals("list")){
				paginglist(request, response);
				request.getRequestDispatcher("data/teacherlist.jsp").forward(request, response);
			}
		}
	}

	public void paginglist(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.barsum("select count(*) cont from teacher"));
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
		List<TeacherBean> teacherlist = sql.teacherlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("teacherlist", teacherlist);
		request.setAttribute("pager", page);
	}
	public void paginglist1(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.pagebarsum("teacher"));
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
		List<TeacherBean> teacherlist = sql.teacherlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("teacherlist", teacherlist);
		request.setAttribute("pager", page);
	}

}
