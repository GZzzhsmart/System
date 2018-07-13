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
   
    <title>请假列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
  </head>
  
   <body>
   <form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 请假列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath }/leaveservlet?order=add"> 添加</a> </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th>请假编号</th>
    	<th>学号</th>
    	<th>请假学生</th>
    	<th>请假理由</th>
    	<th>请假时间</th>
    	<th>请假天数</th>
    	<th>任课教师</th>
    	<th>请假状态</th>
        <th width="80">操作</th>
      </tr>
    
      <c:forEach items="${leavelist}" var="y">
        <tr>
          <td align='center'>${y.lon }</td>
          <td align='center'>${y.sno }</td>
          <td align='center'>${y.sname }</td>
          <td align='center'>${y.cause }</td>
          <td align='center'>${y.ltime }</td>
          <td align='center'>${y.day }</td>
          <td align='center'>${y.tname }</td>
          <td align='center'>${y.lstate }</td>
          <td>
	          <div class="button-group"> 
	          <a class="button border-main" href="<%=basePath%>leaveservlet?lon=${y.lon}&order=update"><span class="icon-edit">
	          </span> 修改</a> 
	          <a class="button border-red" href="<%=basePath%>leaveservlet?lon=${y.lon}&order=delete" onclick="javascript:if(confirm('删除确认')){return true;}else{return false;}"><span class="icon-trash-o">
	          </span> 删除</a> 
	          </div>
          </td>
        </tr>
   	</c:forEach>
      <tr>
        <td colspan="8"><div class="pagelist"> 
        <span >总记录数&nbsp;&nbsp;${pager.pagebarsum }</span>
		<span >页码&nbsp;&nbsp;${pager.currentpage }/${pager.sumpage }页</span>
       	<a href="<%=basePath %>leaveservlet?order=list&currenpage=1&handle=firstpage">首   页</a>
		<a href="<%=basePath %>leaveservlet?order=list&currenpage=${pager.currentpage }&handle=uppage">上一页</a> <span class="current">1</span>
		<a href="<%=basePath %>leaveservlet?order=list&currenpage=${pager.currentpage }&handle=downpage" >下一页</a>
		<a href="<%=basePath %>leaveservlet?order=list&currenpage=${pager.sumpage }&handle=lastpage">尾  页</a> 
        </div>
        </td>
      </tr>
    </table>
  </div>
</form>
</body>
</html>