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
import SQLBean.ScoreBean;
import SQLBean.StudentBean;

public class ScoreServlet extends HttpServlet {
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
				sql.delScore(Integer.parseInt(request.getParameter("sno")));
				paginglist1(request, response);
				request.getRequestDispatcher("data/scorelist.jsp").forward(request, response);
			}else if (order.equals("add")) {
				List<StudentBean> studentList = sql.studentlist();
				List<CourseBean> courseList = sql.courselist();
				request.setAttribute("courseList", courseList);
				request.setAttribute("studentList", studentList);
				request.getRequestDispatcher("data/scoreadd.jsp").forward(request, response);
			}else if (order.equals("addsave")) {
				int grade = Integer.parseInt(request.getParameter("grade"));
				int sno = Integer.parseInt(request.getParameter("sno"));
				int cno = Integer.parseInt(request.getParameter("cno"));
				ScoreBean scoreBean = new ScoreBean();
				scoreBean.setSno(sno);
				scoreBean.setCno(cno);
				scoreBean.setGrade(grade);
				sql.addScore(scoreBean);
				request.setAttribute("scoreBean", scoreBean);
				paginglist(request, response);
				request.getRequestDispatcher("data/scorelist.jsp").forward(request, response);
			}else if (order.equals("update")) {
				int sno = Integer.parseInt(request.getParameter("sno"));
				ScoreBean  scorebean = sql.findScoreById(sno);
				List<StudentBean> studentList = sql.studentlist();
				List<CourseBean> courseList = sql.courselist();
				request.setAttribute("courseList", courseList);
				request.setAttribute("studentList", studentList);
				request.setAttribute("scorebean", scorebean);
				request.getRequestDispatcher("data/scoreupdate.jsp").forward(request, response);
			}else if (order.equals("updatesave")) {
				int grade = Integer.parseInt(request.getParameter("grade"));
				int sno = Integer.parseInt(request.getParameter("sno"));
				int cno = Integer.parseInt(request.getParameter("cno"));
				ScoreBean scoreBean = new ScoreBean();
				scoreBean.setSno(sno);
				scoreBean.setCno(cno);
				scoreBean.setGrade(grade);
				sql.updateScore(scoreBean);
				paginglist(request, response);
				request.getRequestDispatcher("/data/scorelist.jsp").forward(request, response);
			}else if(order.equals("scorelist")){
				paginglist1(request, response);
				request.getRequestDispatcher("data/scorelist.jsp").forward(request, response);
			}else if(order.equals("list")){
				paginglist(request, response);
				request.getRequestDispatcher("data/scorelist.jsp").forward(request, response);
			}
		}
	}

	public void paginglist(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.barsum("select count(*) cont from score"));
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
		List<ScoreBean> scorelist = sql.scorelist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("scorelist", scorelist);
		request.setAttribute("pager", page);
	}
	public void paginglist1(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.pagebarsum("score"));
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
		List<ScoreBean> scorelist = sql.scorelist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("scorelist", scorelist);
		request.setAttribute("pager", page);
	}

}
