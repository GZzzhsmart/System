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
    
    <title>修改课程</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>  
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
    <script type="text/javascript">
	    function check(){
			if(form1.cname.value==""){
				alert("请输入课程名称");
				form1.cname.focus();
				return false;
			}
			return true;
		}
    </script>
  </head>
  
  <body>
     <div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 修改课程</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="<%=basePath%>courseservlet?order=updatesave" method="post" onsubmit="return check();">
      <input type="hidden" name="cno" value="${coursebean.cno}">
       <div class="form-group">
        <div class="label">
          <label for="sitename">名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="cname" size="50" value="${coursebean.cname }" />          
        </div>
      </div>
      
       <div class="form-group">
        <div class="label">
          <label for="sitename">教师：</label>
        </div>
        <div class="field">
         	<select name="tno" class="input w50" >
            <option value="">请选择教师</option>
            <c:forEach items="${teacherlist}" var="b">
				<c:if test="${b.tno == coursebean.tno}">
					<option value="${b.tno}" style="width:260px;height=35px;border-bottom-style: solid;" selected="selected">${b.tname}</option>
				</c:if>
				<c:if test="${b.tno != coursebean.tno}">
					<option value="${b.cno}" style="width:260px;height=35px;border-bottom-style: solid;">${b.tname}</option>
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
