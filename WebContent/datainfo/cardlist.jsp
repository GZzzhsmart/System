<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   
    <title>打卡列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
  </head>
  
   <body>
   <form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 打卡列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <table class="table table-hover text-center">
      <tr>
        <th>编号</th>
    	<th>学号</th>
    	<th>学生</th>
    	<th>课程</th>
    	<th>教师</th>
    	<th>开始时间</th>
    	<th>结束时间</th>
      </tr>
    
      <c:forEach items="${cardlist}" var="y">
        <tr>
          <td align='center'>${y.cardid }</td>
          <td align='center'>${y.sno }</td>
          <td align='center'>${y.sname }</td>
          <td align='center'>${y.cname }</td>
          <td align='center'>${y.tname }</td>
          <td align='center'>${y.startTime }</td>
          <td align='center'>${y.endTime }</td>
        </tr>
   	</c:forEach>
      <tr>
        <td colspan="8"><div class="pagelist"> 
        <span >总记录数&nbsp;&nbsp;${pager.pagebarsum }</span>
		<span >页码&nbsp;&nbsp;${pager.currentpage }/${pager.sumpage }页</span>
       	<a href="<%=basePath %>cardservlet?order=list1&currenpage=1&handle=firstpage">首   页</a>
		<a href="<%=basePath %>cardservlet?order=list1&currenpage=${pager.currentpage }&handle=uppage">上一页</a> <span class="current">1</span>
		<a href="<%=basePath %>cardservlet?order=list1&currenpage=${pager.currentpage }&handle=downpage" >下一页</a>
		<a href="<%=basePath %>cardservlet?order=list1&currenpage=${pager.sumpage }&handle=lastpage">尾  页</a> 
        </div>
        </td>
      </tr>
    </table>
  </div>
</form>
</body>
</html>