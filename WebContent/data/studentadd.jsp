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
    
    <title>新增学生</title>
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
  <div class="panel-head"><strong><span class="icon-key"></span> 添加学生</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="<%=basePath%>studentservlet?order=addsave" method="post" onsubmit="return check();">
       <div class="form-group">
        <div class="label">
          <label for="sitename">编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="sno" size="50" placeholder="请输入学生编号"  />          
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="sname" size="50" placeholder="请输入学生姓名"  />          
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">性别：</label>
        </div>
        <div class="field">
         <div class="button-group radio">
          <input type="radio" name="sex" value="男" checked="checked" style="height=35px">男
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="sex" value="女" style="width:30px;height=30px">女
        </div>
       	</div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">班级：</label>
        </div>
        <div class="field">
          <select name="clno" class="input w50">
            <option value="">请选择班级</option>
            <c:forEach items="${classList}" var="b">
            	<option value="${b.clno}">${b.department}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label for="sitename">生日：</label>
        </div>
        <div class="field">
          <input type="date"  class="input w50" name="birth" id="birth" size="50" placeholder="请选择出生日期"  />          
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
