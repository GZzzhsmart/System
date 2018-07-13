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
    
    <title>修改学生</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>  
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
    <script type="text/javascript">
	    function check(){
			if(form1.sname.value==""){
				alert("请输入学生姓名");
				form1.sname.focus();
				return false;
			}
			return true;
		}
    </script>
  </head>
  
  <body>
     <div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 修改学生</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="<%=basePath%>studentservlet?order=updatesave" method="post" onsubmit="return check();">
       <div class="form-group">
        <div class="label">
          <label for="sitename">编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="sno" size="50" value="${studentbean.sno }"/>          
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="sname" size="50" value="${studentbean.sname }" />          
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">性别：</label>
        </div>
        <div class="field">
        	<c:if test="${studentbean.sex=='男'}">
				 <input type="radio"  name="sex" value="男"  placeholder="输入性别" checked="checked"/>男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="女"  placeholder="输入性别" />女
			</c:if>
			<c:if test="${studentbean.sex=='女'}">
				 <input type="radio"  name="sex" value="女"  placeholder="输入性别" />男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="女"  placeholder="输入性别" checked="checked"/>女
			</c:if>  
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">班级：</label>
        </div>
        <div class="field">
         	<select name="clno" class="input w50" >
            <option value="">请选择班级</option>
            <c:forEach items="${classList}" var="b">
				<c:if test="${b.clno == studentbean.clno}">
					<option value="${b.clno}" style="width:260px;height=35px;border-bottom-style: solid;" selected="selected">${b.department}</option>
				</c:if>
				<c:if test="${b.clno != studentbean.clno}">
					<option value="${b.clno}" style="width:260px;height=35px;border-bottom-style: solid;">${b.department}</option>
				</c:if>
			</c:forEach>
          </select>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">生日：</label>
        </div>
        <div class="field">
           <fmt:parseDate var="dateObj" value="${studentbean.birth}" type="DATE" pattern="yyyy-MM-dd HH:ss:mm"/>
     	  <fmt:formatDate var="reTime" value='${dateObj}' pattern='yyyy-MM-dd HH:ss:mm' />
          <input type="text" class="input w50" name="birth" size="50" value="${reTime }" />          
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
