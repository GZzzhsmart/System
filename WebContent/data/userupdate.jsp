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
    
    <title>修改用户</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>  
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
    <script type="text/javascript">
	    function check(){
			if(form1.username.value==""){
				alert("请输入用户姓名");
				form1.username.focus();
				return false;
			}
			return true;
		}
    </script>
  </head>
  
  <body>
     <div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 修改用户</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="<%=basePath%>userservlet?order=updatesave" method="post" onsubmit="return check();">
       <div class="form-group">
        <div class="label">
          <label for="sitename">姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="username" size="50" value="${userBean.username }" />          
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">密码：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="password" size="50" value="${userBean.password }" />          
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">权限：</label>
        </div>
        <div class="field">
         	<select name="qxno" class="input w50" >
            <option value="">请选择权限</option>
            <c:forEach items="${qxList}" var="b">
				<c:if test="${b.qxno == userBean.qxno}">
					<option value="${b.qxno}" style="width:260px;height=35px;border-bottom-style: solid;" selected="selected">${b.qxname}</option>
				</c:if>
				<c:if test="${b.qxno != userBean.qxno}">
					<option value="${b.qxno}" style="width:260px;height=35px;border-bottom-style: solid;">${b.qxname}</option>
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
