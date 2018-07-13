<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增出勤</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>  
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
  </head>
  
  <body>
     <div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 添加请假</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="<%=basePath%>outservlet?order=addsave" method="post" >
      <div class="form-group">
        <div class="label">
          <label for="sitename">学生：</label>
        </div>
        <div class="field">
          <select name="sno" class="input w50">
            <option value="">请选择学生</option>
            <c:forEach items="${studentList}" var="b">
            	<option value="${b.sno}">${b.sname}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">教师：</label>
        </div>
        <div class="field">
          <select name="tno" class="input w50">
            <option value="">请选择教师</option>
            <c:forEach items="${teacherList}" var="b">
            	<option value="${b.tno}">${b.tname}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">课程：</label>
        </div>
        <div class="field">
          <select name="cno" class="input w50">
            <option value="">请选择课程</option>
            <c:forEach items="${courseList}" var="b">
            	<option value="${b.cno}">${b.cname}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">状态：</label>
        </div>
        <div class="field">
         <div class="button-group radio">
          <input type="radio" name="state" value="出勤" checked="checked" style="height=35px">出勤
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="state" value="未出勤" style="width:30px;height=30px">未出勤
        </div>
       	</div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">时间：</label>
        </div>
        <div class="field">
          <input type="date"  class="input w50" name="otime"  size="50" placeholder="请选择出勤时间"  />          
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 添加</button>   
        </div>
      </div>      
    </form>
  </div>
</div>
</body>
</html>
