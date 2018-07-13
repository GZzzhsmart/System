<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改管理员</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>  
	<script src="${pageContext.request.contextPath }/js/pintuer.js"></script>
    <script type="text/javascript">
	    function check(){
			if(form1.ano.value==""){
				alert("请输入编号");
				form1.ano.focus();
				return false;
			}
			return true;
		}
    </script>
  </head>
  
  <body>
     <div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-key"></span> 修改管理员</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="<%=basePath%>adminservlet?order=updateSave" method="post" onsubmit="return check();">
      <input type="hidden" name="sno" value="${admin.ano}">
       <div class="form-group">
        <div class="label">
          <label for="sitename">编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="ano" size="50" value="${admin.ano }" />          
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label for="sitename">密码：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="aword" size="50" value="${admin.aword }" />          
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
