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
    
    <title>修改请假</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>  
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
    <script type="text/javascript">
	    function check(){
			if(form1.cause.value==""){
				alert("请输入请假理由");
				form1.cause.focus();
				return false;
			}
			return true;
		}
    </script>
  </head>
  
  <body>
     <div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 修改请假</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="<%=basePath%>leaveservlet?order=updatesave" method="post" onsubmit="return check();">
       <input type="hidden" name="lon" value="${leaveBean.lon}">
       <div class="form-group">
        <div class="label">
          <label for="sitename">请假学生：</label>
        </div>
        <div class="field">
         	<select name="sno" class="input w50" >
            <option value="">请选择学生</option>
            <c:forEach items="${studentList}" var="b">
				<c:if test="${b.sno == leaveBean.sno}">
					<option value="${b.sno}" style="width:260px;height=35px;border-bottom-style: solid;" selected="selected">${b.sname}</option>
				</c:if>
				<c:if test="${b.sno != leaveBean.sno}">
					<option value="${b.sno}" style="width:260px;height=35px;border-bottom-style: solid;">${b.sname}</option>
				</c:if>
			</c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">任课教师：</label>
        </div>
        <div class="field">
         	<select name="tno" class="input w50" >
            <option value="">请选择教师</option>
            <c:forEach items="${teacherList}" var="b">
				<c:if test="${b.tno == leaveBean.tno}">
					<option value="${b.tno}" style="width:260px;height=35px;border-bottom-style: solid;" selected="selected">${b.tname}</option>
				</c:if>
				<c:if test="${b.tno != leaveBean.tno}">
					<option value="${b.tno}" style="width:260px;height=35px;border-bottom-style: solid;">${b.tname}</option>
				</c:if>
			</c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">请假理由：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="cause" value="${leaveBean.cause }"  size="50" placeholder="请输入请假理由"  />          
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">请假天数：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="day" value="${leaveBean.day }" size="50" placeholder="请输入请假天数"  />          
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">请假状态：</label>
        </div>
        <div class="field">
        	<c:if test="${leaveBean.lstate=='已批准'}">
				 <input type="radio"  name="lstate" value="已批准"   checked="checked"/>已批准&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="lstate" value="未批准"   />未批准
			</c:if>
			<c:if test="${leaveBean.lstate=='未批准'}">
				 <input type="radio"  name="lstate" value="未批准"   />已批准&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="lstate" value="未批准"  checked="checked"/>未批准
			</c:if>  
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">请假时间：</label>
        </div>
        <div class="field">
          <fmt:parseDate var="dateObj" value="${leaveBean.ltime}" type="DATE" pattern="yyyy-MM-dd HH:ss:mm"/>
     	  <fmt:formatDate var="reTime" value='${dateObj}' pattern='yyyy-MM-dd HH:ss:mm' />
     	  <input type="text"  class="input w50" name="ltime" value="${reTime}"  size="50" placeholder="请选择请假时间"  />
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
