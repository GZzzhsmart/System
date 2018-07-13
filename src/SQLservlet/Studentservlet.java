package SQLservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SQLBase.SQLSentenceimp;
import SQLBean.ClassBean;
import SQLBean.PagingBean;
import SQLBean.StudentBean;

public class Studentservlet extends HttpServlet {
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
			if(order.equals("delete")) {
				sql.studentdel(Integer.parseInt(request.getParameter("sno")));
				paginglist1(request, response);
				request.getRequestDispatcher("data/studentselect.jsp").forward(request, response);
			}else if (order.equals("add")) {
				List<ClassBean> classList = sql.listclass();
				request.setAttribute("classList", classList);
				request.getRequestDispatcher("data/studentadd.jsp").forward(request, response);
			}else if (order.equals("addsave")) {
				String sname = request.getParameter("sname");
				String sex = request.getParameter("sex");
				int clno = Integer.parseInt(request.getParameter("clno"));
				String birth = request.getParameter("birth");
				int sno = Integer.parseInt(request.getParameter("sno"));
				StudentBean studentbean = new StudentBean();
				studentbean.setSno(sno);
				studentbean.setSname(sname);
				studentbean.setSex(sex);
				studentbean.setClno(clno);
				studentbean.setBirth(birth);
				sql.studentadd(studentbean);
				request.setAttribute("studentbean", studentbean);
				StudentBean bean = sql.findstudent(sno);
				session.setAttribute("bean", bean);
				paginglist(request, response);
				request.getRequestDispatcher("data/studentselect.jsp").forward(request, response);
			}else if (order.equals("update")) {
				String sno = request.getParameter("sno");
				StudentBean  studentbean = sql.findstudent(Integer.parseInt(sno));
				List<ClassBean> classList = sql.listclass();
				request.setAttribute("classList", classList);
				request.setAttribute("studentbean", studentbean);
				request.getRequestDispatcher("data/studentupdate.jsp").forward(request, response);
			}else if (order.equals("updatesave")) {
				String sname = request.getParameter("sname");
				String sex = request.getParameter("sex");
				String birth = request.getParameter("birth");
				int clno = Integer.parseInt(request.getParameter("clno"));
				int sno = Integer.parseInt(request.getParameter("sno"));
				StudentBean studentbean = new StudentBean();
				studentbean.setSno(sno);
				studentbean.setSname(sname);
				studentbean.setBirth(birth);
				studentbean.setSex(sex);
				studentbean.setClno(clno);
				sql.studentupdate(studentbean);
				paginglist(request, response);
				request.getRequestDispatcher("/data/studentselect.jsp").forward(request, response);
			}else if(order.equals("studentlist")){
				paginglist1(request, response);
				request.getRequestDispatcher("data/studentselect.jsp").forward(request, response);
			}else if(order.equals("list")){
				paginglist(request, response);
				request.getRequestDispatcher("data/studentselect.jsp").forward(request, response);
			}
		}
	}

	public void paginglist(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.barsum("select count(*) cont from student"));
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
		List<StudentBean> studentlist = sql.studentlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("studentlist", studentlist);
		request.setAttribute("pager", page);
	}
	public void paginglist1(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.pagebarsum("student"));
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
		List<StudentBean> studentlist = sql.studentlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("studentlist", studentlist);
		request.setAttribute("pager", page);
	}

}
