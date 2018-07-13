<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改打卡</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>  
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
   
  </head>
  
  <body>
     <div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 修改打卡</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="<%=basePath%>cardservlet?order=updatesave" method="post">
       <input type="hidden" name="cardid" value="${cardBean.cardid}">
       <div class="form-group">
        <div class="label">
          <label for="sitename">学生：</label>
        </div>
        <div class="field">
         	<select name="sno" class="input w50" >
            <option value="">请选择学生</option>
            <c:forEach items="${studentList}" var="b">
				<c:if test="${b.sno == cardBean.sno}">
					<option value="${b.sno}" style="width:260px;height=35px;border-bottom-style: solid;" selected="selected">${b.sname}</option>
				</c:if>
				<c:if test="${b.sno != cardBean.sno}">
					<option value="${b.sno}" style="width:260px;height=35px;border-bottom-style: solid;">${b.sname}</option>
				</c:if>
			</c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">教师：</label>
        </div>
        <div class="field">
         	<select name="tno" class="input w50" >
            <option value="">请选择教师</option>
            <c:forEach items="${teacherList}" var="b">
				<c:if test="${b.tno == cardBean.tno}">
					<option value="${b.tno}" style="width:260px;height=35px;border-bottom-style: solid;" selected="selected">${b.tname}</option>
				</c:if>
				<c:if test="${b.tno != cardBean.tno}">
					<option value="${b.tno}" style="width:260px;height=35px;border-bottom-style: solid;">${b.tname}</option>
				</c:if>
			</c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">课程：</label>
        </div>
        <div class="field">
         	<select name="cno" class="input w50" >
            <option value="">请选择课程</option>
            <c:forEach items="${courseList}" var="b">
				<c:if test="${b.cno == cardBean.cno}">
					<option value="${b.cno}" style="width:260px;height=35px;border-bottom-style: solid;" selected="selected">${b.cname}</option>
				</c:if>
				<c:if test="${b.cno != cardBean.cno}">
					<option value="${b.cno}" style="width:260px;height=35px;border-bottom-style: solid;">${b.cname}</option>
				</c:if>
			</c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">开始时间：</label>
        </div>
        <div class="field">
          <fmt:parseDate var="dateObj" value="${cardBean.startTime}" type="DATE" pattern="yyyy-MM-dd HH:ss:mm"/>
     	  <fmt:formatDate var="reTime" value='${dateObj}' pattern='yyyy-MM-dd HH:ss:mm' />
          <input type="text"  class="input w50" name="startTime" value="${reTime }" size="50" placeholder="请选择开始时间"  />     
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">结束时间：</label>
        </div>
        <div class="field">
        	 <fmt:parseDate var="dateObj" value="${cardBean.endTime}" type="DATE" pattern="yyyy-MM-dd HH:ss:mm"/>
     	  <fmt:formatDate var="reTime" value='${dateObj}' pattern='yyyy-MM-dd HH:ss:mm' />
          <input type="text"  class="input w50" name="endTime" value="${reTime }" size="50" placeholder="请选择结束时间"  />  
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 修改</button>   
        </div>
      </div>      
    </form>
  </div>
</div>
</body>
</html>
