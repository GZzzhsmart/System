<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改权限</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>  
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
    <script type="text/javascript">
	    function check(){
			if(form1.qxname.value==""){
				alert("请输入权限名称");
				form1.qxname.focus();
				return false;
			}
			return true;
		}
    </script>
  </head>
  
  <body>
     <div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 修改权限</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="<%=basePath%>qxservlet?order=updateSave" method="post" onsubmit="return check();">
      <input type="hidden" name="sno" value="${qx.qxno}">
       <div class="form-group">
        <div class="label">
          <label for="sitename">编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="qxno" size="50" value="${qx.qxno }" />          
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="qxname" size="50" value="${qx.qxname }" />          
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
