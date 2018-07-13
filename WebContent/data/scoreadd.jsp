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
    
    <title>新增成绩</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>  
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
    <script type="text/javascript">
    	function check(){
    		if(form1.grade.value==""){
    			alert("请输入学生成绩");
    			form1.grade.focus();
    			return false;
    		}
    		return true;
    	}
    	
    </script>
  </head>
  
  <body>
     <div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 添加成绩</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="<%=basePath%>scoreservlet?order=addsave" method="post" onsubmit="return check();">
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
          <label for="sitename">成绩：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="grade" size="50" placeholder="请输入学生成绩"  />          
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
