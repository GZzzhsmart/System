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
import SQLBean.OutBean;
import SQLBean.PagingBean;
import SQLBean.StudentBean;
import SQLBean.TeacherBean;

public class OutServlet extends HttpServlet {
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
				sql.delOut(Integer.parseInt(request.getParameter("ono")));
				paginglist1(request, response);
				request.getRequestDispatcher("data/outlist.jsp").forward(request, response);
			}else if (order.equals("add")) {
				List<StudentBean> studentList = sql.studentlist();
				List<TeacherBean> teacherList = sql.teacherlist();
				List<CourseBean> courseList = sql.courselist();
				request.setAttribute("teacherList", teacherList);
				request.setAttribute("studentList", studentList);
				request.setAttribute("courseList", courseList);
				request.getRequestDispatcher("data/outadd.jsp").forward(request, response);
			}else if (order.equals("addsave")) {
				int sno = Integer.parseInt(request.getParameter("sno"));
				int tno = Integer.parseInt(request.getParameter("tno"));
				int cno = Integer.parseInt(request.getParameter("cno"));
				String state = request.getParameter("state");
				String otime = request.getParameter("otime");
				OutBean outBean = new OutBean();
				outBean.setSno(sno);
				outBean.setTno(tno);
				outBean.setCno(cno);
				outBean.setState(state);
				outBean.setOtime(otime);
				sql.addOut(outBean);
				request.setAttribute("outBean", outBean);
				paginglist(request, response);
				request.getRequestDispatcher("data/outlist.jsp").forward(request, response);
			}else if (order.equals("update")) {
				int ono = Integer.parseInt(request.getParameter("ono"));
				OutBean  outBean = sql.findOutById(ono);
				List<StudentBean> studentList = sql.studentlist();
				List<TeacherBean> teacherList = sql.teacherlist();
				List<CourseBean> courseList = sql.courselist();
				request.setAttribute("teacherList", teacherList);
				request.setAttribute("studentList", studentList);
				request.setAttribute("courseList", courseList);
				request.setAttribute("outBean", outBean);
				request.getRequestDispatcher("data/outupdate.jsp").forward(request, response);
			}else if (order.equals("updatesave")) {
				int ono = Integer.parseInt(request.getParameter("ono"));
				int sno = Integer.parseInt(request.getParameter("sno"));
				int tno = Integer.parseInt(request.getParameter("tno"));
				int cno = Integer.parseInt(request.getParameter("cno"));
				String state = request.getParameter("state");
				String otime = request.getParameter("otime");
				OutBean outBean = new OutBean();
				outBean.setOno(ono);
				outBean.setSno(sno);
				outBean.setTno(tno);
				outBean.setCno(cno);
				outBean.setState(state);
				outBean.setOtime(otime);
				sql.updateOut(outBean);
				paginglist(request, response);
				request.getRequestDispatcher("/data/outlist.jsp").forward(request, response);
			}else if(order.equals("outlist")){
				paginglist1(request, response);
				request.getRequestDispatcher("data/outlist.jsp").forward(request, response);
			}else if(order.equals("list")){
				paginglist(request, response);
				request.getRequestDispatcher("data/outlist.jsp").forward(request, response);
			}else if(order.equals("listout")){
				paginglist1(request, response);
				request.getRequestDispatcher("/datainfo/outlist.jsp").forward(request, response);
			}else if(order.equals("list1")){
				paginglist(request, response);
				request.getRequestDispatcher("/datainfo/outlist.jsp").forward(request, response);
			}
		}
	}

	public void paginglist(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.barsum("select count(*) cont from out"));
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
		List<OutBean> outlist = sql.outlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("outlist", outlist);
		request.setAttribute("pager", page);
	}
	public void paginglist1(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.pagebarsum("out"));
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
		List<OutBean> outlist = sql.outlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("outlist", outlist);
		request.setAttribute("pager", page);
	}

}
