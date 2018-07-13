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
import SQLBean.PagingBean;
import SQLBean.QxBean;

public class QxServlet extends HttpServlet{

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
			if(order.equals("qxlist")){
				list(request, response);
				request.getRequestDispatcher("data/qxlist.jsp").forward(request, response);
			}else if(order.equals("list")){
				paginglist(request, response);
				request.getRequestDispatcher("data/qxlist.jsp").forward(request, response);
			}else if(order.equals("delete")){
				sql.delQx(Integer.parseInt(request.getParameter("qxno")));
				list(request, response);
				request.getRequestDispatcher("/data/qxlist.jsp").forward(request,response);
			}else if(order.equals("add")){
				request.getRequestDispatcher("/data/qxadd.jsp").forward(request,response);
			}else if(order.equals("addSave")){
				Integer qxno = Integer.parseInt(request.getParameter("qxno"));
				String qxname = request.getParameter("qxname");
				QxBean qxBean = new QxBean();
				qxBean.setQxno(qxno);
				qxBean.setQxname(qxname);
				sql.addQx(qxBean);
				list(request, response);
				request.getRequestDispatcher("/data/qxlist.jsp").forward(request,response);
			}else if(order.equals("update")){
				String qxno = request.getParameter("qxno");
				QxBean qx = sql.findQxById(Integer.parseInt(qxno));
				request.setAttribute("qx", qx);
				list(request, response);
				request.getRequestDispatcher("/data/qxupdate.jsp").forward(request, response);
			}else if(order.equals("updateSave")){
				Integer qxno = Integer.parseInt(request.getParameter("qxno"));
				String qxname = request.getParameter("qxname");
				QxBean qxBean = new QxBean();
				qxBean.setQxno(qxno);
				qxBean.setQxname(qxname);
				sql.Qxupdate(qxBean);
				list(request, response);
				request.getRequestDispatcher("/data/qxlist.jsp").forward(request,response);
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
		List<QxBean> qxlist = sql.qxlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("qxlist", qxlist);
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
		List<QxBean> qxlist = sql.qxlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("qxlist", qxlist);
		request.setAttribute("pager", page);
	}
}
