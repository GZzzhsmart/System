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
    
    <title>新增请假</title>
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
  <div class="panel-head"><strong><span class="icon-key"></span> 添加请假</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="<%=basePath%>leaveservlet?order=addsave" method="post" onsubmit="return check();">
      <div class="form-group">
        <div class="label">
          <label for="sitename">请假学生：</label>
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
          <label for="sitename">任课教师：</label>
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
          <label for="sitename">请假理由：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="cause" size="50" placeholder="请输入请假理由"  />          
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">请假天数：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="day" size="50" placeholder="请输入请假天数"  />          
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">请假状态：</label>
        </div>
        <div class="field">
         <div class="button-group radio">
          <input type="radio" name="lstate" value="已批准" checked="checked" style="height=35px">已批准
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="lstate" value="未批准" style="width:30px;height=30px">未批准
        </div>
       	</div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">请假时间：</label>
        </div>
        <div class="field">
          <input type="date"  class="input w50" name="ltime"  size="50" placeholder="请选择请假时间"  />          
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
