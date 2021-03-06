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
   
    <title>班级列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
  </head>
  
   <body>
   <form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 班级列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath }/classservlet?order=add"> 添加</a> </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th style="text-align:left; padding-left:20px;">班级编号</th>
    	<th>班级名称</th>
    	<th>任课教师</th>
    	<th>班级人数</th>
        <th width="310">操作</th>
      </tr>
    
      <c:forEach items="${classlist}" var="y">
        <tr>
          <td style="text-align:left; padding-left:20px;">${y.clno }</td>
          <td align='center'>${y.department }</td>
          <td align='center'>${y.tname }</td>
          <td align='center'>${y.sum }</td>
          <td>
	          <div class="button-group"> 
	          <a class="button border-main" href="<%=basePath%>classservlet?clno=${y.clno}&order=update"><span class="icon-edit">
	          </span> 修改</a> 
	          <a class="button border-red" href="<%=basePath%>classservlet?clno=${y.clno}&order=delete" onclick="javascript:if(confirm('删除确认')){return true;}else{return false;}"><span class="icon-trash-o">
	          </span> 删除</a> 
	          </div>
          </td>
        </tr>
   	</c:forEach>
      <tr>
        <td colspan="8"><div class="pagelist"> 
        <span >总记录数&nbsp;&nbsp;${pager.pagebarsum }</span>
		<span >页码&nbsp;&nbsp;${pager.currentpage }/${pager.sumpage }页</span>
       	<a href="<%=basePath %>classservlet?order=list&currenpage=1&handle=firstpage">首   页</a>
		<a href="<%=basePath %>classservlet?order=list&currenpage=${pager.currentpage }&handle=uppage">上一页</a> <span class="current">1</span>
		<a href="<%=basePath %>classservlet?order=list&currenpage=${pager.currentpage }&handle=downpage" >下一页</a>
		<a href="<%=basePath %>classservlet?order=list&currenpage=${pager.sumpage }&handle=lastpage">尾  页</a> 
        </div>
        </td>
      </tr>
    </table>
  </div>
</form>
</body>
</html>