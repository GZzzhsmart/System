<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>用户注册</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
    <script src="${pageContext.request.contextPath }/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath }/js/pintuer.js"></script>  
   
<c:if test="${msg!=null}">
	<script type="text/javascript">
		alert("${msg}");
	</script>
</c:if>
   
    <script type="text/javascript">
	$(function(){
		$("#password").bind("click",function(){
			var un=$("#username").val();
			var pw=$("#password").val();
			if(un==""){
				alert("请输入用户名");
				$("#username").focus();
				return;
			}
			if(pw==""){
				alert("请输入密码");
				$("#password").focus();
				return false;
			}
		});
		
	});
	function btntest(){
			var un=$("#username").val();
			var pw=$("#password").val();
			var code=$("#code").val();
			if(un==""){
				alert("请输入用户名");
				$("#password").focus();
				return false;
			}
			if(pw==""){
				alert("请输入密码");
				$("#password").focus();
				return false;
			}
			return true;
	}
</script>
    
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">           
            </div>         
            <form action="${pageContext.request.contextPath }/userservlet?order=register" method="post" name="form1" onsubmit="return btntest();">
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>用户注册</h1></div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="text" class="input input-big" name="username" id="username" placeholder="用户名" data-validate="required:请填写用户名" />
                            <span class="icon icon-user margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field field-icon-right">
                            <input type="password" class="input input-big" name="password" id="password"  placeholder="登录密码" data-validate="required:请填写密码" />
                            <span class="icon icon-key margin-small"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="field">
                          <c:forEach  items="${qxList }" var="d">
                           		<input type="radio" name="qxno" value="${d.qxno}" style="width:30px;height=30px">${d.qxname}
					      </c:forEach>
                        </div>
                    </div>
                </div>
                <div style="padding:30px;"><input type="submit" onclick="btnTest();" class="button button-block bg-main text-big input-big" value="注册"></div>
            </div>
            </form>          
        </div>
    </div>
</div>
</body>
</html>