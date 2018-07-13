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
import SQLBean.AdminBean;
import SQLBean.PagingBean;
import SQLBean.StudentBean;

public class AdminServlet extends HttpServlet{

	SQLSentenceimp sql = new SQLSentenceimp();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		HttpSession session = request.getSession(); 
		request.setCharacterEncoding("utf-8");
		if(request.getParameter("order")!=null){
			String order = request.getParameter("order");
			if(order.equals("adminlist")){
				list(request, response);
				request.getRequestDispatcher("data/adminlist.jsp").forward(request, response);
			}else if(order.equals("list")){
				paginglist(request, response);
				request.getRequestDispatcher("data/adminlist.jsp").forward(request, response);
			}else if (order.equals("update")){
				String ano = request.getParameter("ano");
				AdminBean admin = sql.findmanagerById(Integer.parseInt(ano));
				request.setAttribute("admin", admin);
				list(request, response);
				request.getRequestDispatcher("data/adminupdate.jsp").forward(request, response);
			}else if(order.equals("updateSave")){
				String ano = request.getParameter("ano");
				String aword = request.getParameter("aword");
				AdminBean adminBean = new AdminBean();
				adminBean.setAno(Integer.parseInt(ano));
				adminBean.setAword(aword);
				sql.managerupdate(adminBean);
				paginglist(request, response);
				request.getRequestDispatcher("/data/adminlist.jsp").forward(request,response);
			}else if(order.equals("add")){
				request.getRequestDispatcher("/data/adminadd.jsp").forward(request,response);
			}else if(order.equals("addSave")){
				Integer ano = Integer.parseInt(request.getParameter("ano"));
				String aword = request.getParameter("aword");
				AdminBean adminBean = new AdminBean();
				adminBean.setAno(ano);
				adminBean.setAword(aword);
				sql.addmanager(adminBean);
				list(request, response);
				request.getRequestDispatcher("/data/adminlist.jsp").forward(request,response);
			}else if(order.equals("delete")){
				sql.delmanager(Integer.parseInt(request.getParameter("ano")));
				list(request, response);
				request.getRequestDispatcher("/data/adminlist.jsp").forward(request,response);
			}
		}
	}
	
	public void paginglist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.barsum("select count(*) cont from admin"));
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
		List<AdminBean> adminlist = sql.managerlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("adminlist", adminlist);
		request.setAttribute("pager", page);
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.pagebarsum("admin"));
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
		List<AdminBean> adminlist = sql.managerlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("adminlist", adminlist);
		request.setAttribute("pager", page);
	}
}
