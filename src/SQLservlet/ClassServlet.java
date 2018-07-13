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
import SQLBean.ClassBean;
import SQLBean.PagingBean;
import SQLBean.TeacherBean;

public class ClassServlet extends HttpServlet {
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
				sql.delClass(Integer.parseInt(request.getParameter("clno")));
				paginglist1(request, response);
				request.getRequestDispatcher("/data/classlist.jsp").forward(request, response);
			}else if (order.equals("add")) {
				List<TeacherBean> teacherList = sql.teacherlist();
				request.setAttribute("teacherList", teacherList);
				request.getRequestDispatcher("/data/classadd.jsp").forward(request, response);
			}else if (order.equals("addsave")) {
				String department = request.getParameter("department");
				int clno = Integer.parseInt(request.getParameter("clno"));
				int tno = Integer.parseInt(request.getParameter("tno"));
				int sum = Integer.parseInt(request.getParameter("sum"));
				ClassBean classBean = new ClassBean();
				classBean.setClno(clno);
				classBean.setDepartment(department);
				classBean.setTno(tno);
				classBean.setSum(sum);
				sql.addClass(classBean);
				request.setAttribute("classBean", classBean);
				paginglist(request, response);
				request.getRequestDispatcher("/data/classlist.jsp").forward(request, response);
			}else if (order.equals("update")) {
				String clno = request.getParameter("clno");
				ClassBean  classbean = sql.findClassById(Integer.parseInt(clno));
				List<TeacherBean> teacherList = sql.teacherlist();
				request.setAttribute("teacherList", teacherList);
				request.setAttribute("classbean", classbean);
				request.getRequestDispatcher("/data/classupdate.jsp").forward(request, response);
			}else if (order.equals("updatesave")) {
				String department = request.getParameter("department");
				int clno = Integer.parseInt(request.getParameter("clno"));
				int tno = Integer.parseInt(request.getParameter("tno"));
				int sum = Integer.parseInt(request.getParameter("sum"));
				ClassBean classBean = new ClassBean();
				classBean.setClno(clno);
				classBean.setDepartment(department);
				classBean.setTno(tno);
				classBean.setSum(sum);
				sql.updateClass(classBean);
				paginglist(request, response);
				request.getRequestDispatcher("/data/classlist.jsp").forward(request, response);
			}else if(order.equals("classlist")){
				paginglist1(request, response);
				request.getRequestDispatcher("/data/classlist.jsp").forward(request, response);
			}else if(order.equals("list")){
				paginglist(request, response);
				request.getRequestDispatcher("/data/teacherlist.jsp").forward(request, response);
			}
		}
	}

	public void paginglist(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.barsum("select count(*) cont from class"));
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
		List<ClassBean> classlist = sql.classlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("classlist", classlist);
		request.setAttribute("pager", page);
	}
	public void paginglist1(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.pagebarsum("class"));
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
		List<ClassBean> classlist = sql.classlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("classlist", classlist);
		request.setAttribute("pager", page);
	}

}
