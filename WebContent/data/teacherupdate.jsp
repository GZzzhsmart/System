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
    
    <title>修改教师</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>  
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
    <script type="text/javascript">
	    function check(){
			if(form1.tname.value==""){
				alert("请输入教师姓名");
				form1.tname.focus();
				return false;
			}
			return true;
		}
    </script>
  </head>
  
  <body>
     <div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 修改教师</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="<%=basePath%>teacherservlet?order=updatesave" method="post" onsubmit="return check();">
      <input type="hidden" name="tno" value="${teacherbean.tno}">
       <div class="form-group">
        <div class="label">
          <label for="sitename">姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="tname" size="50" value="${teacherbean.tname }" />          
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
				<c:if test="${b.cno == teacherbean.cno}">
					<option value="${b.cno}" style="width:260px;height=35px;border-bottom-style: solid;" selected="selected">${b.cname}</option>
				</c:if>
				<c:if test="${b.cno != teacherbean.cno}">
					<option value="${b.cno}" style="width:260px;height=35px;border-bottom-style: solid;">${b.cname}</option>
				</c:if>
			</c:forEach>
          </select>
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
