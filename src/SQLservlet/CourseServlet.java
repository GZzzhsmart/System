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

public class CourseServlet extends HttpServlet {
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
				sql.delCourse(Integer.parseInt(request.getParameter("cno")));
				paginglist1(request, response);
				request.getRequestDispatcher("data/courselist.jsp").forward(request, response);
			}else if (order.equals("add")) {
				List<TeacherBean> teacherList = sql.teacherlist();
				request.setAttribute("teacherList", teacherList);
				request.getRequestDispatcher("data/courseadd.jsp").forward(request, response);
			}else if (order.equals("addsave")) {
				String cname = request.getParameter("cname");
				int tno = Integer.parseInt(request.getParameter("tno"));
				CourseBean courseBean = new CourseBean();
				courseBean.setCname(cname);
				courseBean.setTno(tno);
				sql.addCourse(courseBean);
				request.setAttribute("courseBean", courseBean);
				paginglist(request, response);
				request.getRequestDispatcher("data/courselist.jsp").forward(request, response);
			}else if (order.equals("update")) {
				String cno = request.getParameter("cno");
				CourseBean  coursebean = sql.findCourseById(Integer.parseInt(cno));
				List<TeacherBean> teacherlist = sql.teacherlist();
				request.setAttribute("teacherlist", teacherlist);
				request.setAttribute("coursebean", coursebean);
				request.getRequestDispatcher("data/courseupdate.jsp").forward(request, response);
			}else if (order.equals("updatesave")) {
				String cname = request.getParameter("cname");
				int cno = Integer.parseInt(request.getParameter("cno"));
				int tno = Integer.parseInt(request.getParameter("tno"));
				CourseBean courseBean = new CourseBean();
				courseBean.setCname(cname);
				courseBean.setTno(tno);
				courseBean.setCno(cno);
				sql.updateCourse(courseBean);
				paginglist(request, response);
				request.getRequestDispatcher("/data/courselist.jsp").forward(request, response);
			}else if(order.equals("courselist")){
				paginglist1(request, response);
				request.getRequestDispatcher("data/courselist.jsp").forward(request, response);
			}else if(order.equals("list")){
				paginglist(request, response);
				request.getRequestDispatcher("data/courselist.jsp").forward(request, response);
			}
		}
	}

	public void paginglist(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.barsum("select count(*) cont from course"));
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
		List<CourseBean> courselist = sql.courselist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("courselist", courselist);
		request.setAttribute("pager", page);
	}
	public void paginglist1(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.pagebarsum("course"));
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
		List<CourseBean> courselist = sql.courselist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("courselist", courselist);
		request.setAttribute("pager", page);
	}

}
