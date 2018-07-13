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
import SQLBean.LUserBean;
import SQLBean.PagingBean;
import SQLBean.QxBean;

public class UserServlet extends HttpServlet {
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
				sql.delUser(request.getParameter("username"));
				paginglist1(request, response);
				request.getRequestDispatcher("data/userlist.jsp").forward(request, response);
			}else if (order.equals("add")) {
				List<QxBean> qxList = sql.qxlist();
				request.setAttribute("qxList", qxList);
				request.getRequestDispatcher("data/useradd.jsp").forward(request, response);
			}else if (order.equals("addsave")) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				int qxno = Integer.parseInt(request.getParameter("qxno"));
				LUserBean userBean = new LUserBean();
				userBean.setUsername(username);
				userBean.setPassword(password);
				userBean.setQxno(qxno);
				sql.addUser(userBean);
				request.setAttribute("userBean", userBean);
				paginglist(request, response);
				request.getRequestDispatcher("data/userlist.jsp").forward(request, response);
			}else if (order.equals("update")) {
				String username = request.getParameter("username");
				LUserBean  userBean = sql.findUserById(username);
				List<QxBean> qxList = sql.qxlist();
				request.setAttribute("qxList", qxList);
				request.setAttribute("userBean", userBean);
				request.getRequestDispatcher("data/userupdate.jsp").forward(request, response);
			}else if (order.equals("updatesave")) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				int qxno = Integer.parseInt(request.getParameter("qxno"));
				LUserBean userBean = new LUserBean();
				userBean.setUsername(username);
				userBean.setPassword(password);
				userBean.setQxno(qxno);
				sql.updateUser(userBean);
				paginglist(request, response);
				request.getRequestDispatcher("/data/userlist.jsp").forward(request, response);
			}else if(order.equals("userlist")){
				paginglist1(request, response);
				request.getRequestDispatcher("data/userlist.jsp").forward(request, response);
			}else if(order.equals("list")){
				paginglist(request, response);
				request.getRequestDispatcher("data/userlist.jsp").forward(request, response);
			}else if(order.equals("login")){
				String password = request.getParameter("aword");
				String username = request.getParameter("username");
				LUserBean user = sql.userlogin(username);
				if(user.getUsername()==null){
					request.setAttribute("msg", "用户不存在，请先注册！");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}else {
					//保存登录信息
					session.setAttribute("user", user);
					request.getRequestDispatcher("/index.jsp").forward(request, response);
					if(!user.getUsername().equals(username)){
						request.setAttribute("msg", "用户名错误！");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}
					if (!user.getPassword().equals(password)) {
						request.setAttribute("msg", "用户密码错误，请重新输入！");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}
				}
			}else if(order.equals("exit")){
				session.invalidate();
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else if(order.equals("adduser")){
				List<QxBean> qxList = sql.qxlist();
				request.setAttribute("qxList", qxList);
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}else if(order.equals("register")){
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				int qxno = Integer.parseInt(request.getParameter("qxno"));
				LUserBean userBean = new LUserBean();
				userBean.setUsername(username);
				userBean.setPassword(password);
				userBean.setQxno(qxno);
				sql.addUser(userBean);
				request.setAttribute("msg", "用户注册成功");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
	}

	public void paginglist(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.barsum("select count(*) cont from l_user"));
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
		List<LUserBean> userlist = sql.userlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("userlist", userlist);
		request.setAttribute("pager", page);
	}
	public void paginglist1(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//实例化javabean，取参数
		PagingBean page = new PagingBean();
		//总记录条数，计算总页数
		page.setPagebarsum(sql.pagebarsum("l_user"));
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
		List<LUserBean> userlist = sql.userlist(page.getPagebarsize(), page.getStarlocal());
		request.setAttribute("userlist", userlist);
		request.setAttribute("pager", page);
	}

}
