<%@page import="SQLBean.LUserBean"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
		LUserBean user = (LUserBean)session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
    <script src="${pageContext.request.contextPath }/js/jquery.js"></script>   
</head>

<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
     <c:if test="${user.qxname=='管理员'}">
    	<h1><img src="${pageContext.request.contextPath }/image/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />管理员:${user.username}</h1>
  	 </c:if>
  	 <c:if test="${user.qxname=='学生'}">
    	<h1><img src="${pageContext.request.contextPath }/image/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />学生:${user.username}</h1>
  	 </c:if>
  	 <c:if test="${user.qxname=='教师'}">
    	<h1><img src="${pageContext.request.contextPath }/image/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />教师:${user.username}</h1>
  	 </c:if>
  </div>
  <div class="head-l">
   &nbsp;&nbsp;<a class="button button-little bg-red" href="${pageContext.request.contextPath }/userservlet?order=exit"><span class="icon-power-off"></span> 退出登录</a> 
  </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>学生管理</h2>
  <ul style="display:block">
    <c:if test="${user.qxname=='管理员'}">
	    <li><a href="${pageContext.request.contextPath }/studentservlet?order=studentlist" target="right"><span class="icon-caret-right"></span>学生列表</a></li>
	    <li><a href="${pageContext.request.contextPath }/adminservlet?order=adminlist" target="right"><span class="icon-caret-right"></span>管理员列表</a></li>
	    <li><a href="${pageContext.request.contextPath }/qxservlet?order=qxlist" target="right"><span class="icon-caret-right"></span>权限列表</a></li>
	    <li><a href="${pageContext.request.contextPath }/userservlet?order=userlist" target="right"><span class="icon-caret-right"></span>用户列表</a></li>
	    <li><a href="${pageContext.request.contextPath }/classservlet?order=classlist" target="right"><span class="icon-caret-right"></span>班级列表</a></li>
	    <li><a href="${pageContext.request.contextPath }/teacherservlet?order=teacherlist" target="right"><span class="icon-caret-right"></span>教师列表</a></li>
	    <li><a href="${pageContext.request.contextPath }/cardservlet?order=cardlist" target="right"><span class="icon-caret-right"></span>打卡表</a></li>        
	    <li><a href="${pageContext.request.contextPath }/courseservlet?order=courselist" target="right"><span class="icon-caret-right"></span>课程表</a></li>
	    <li><a href="${pageContext.request.contextPath }/scoreservlet?order=scorelist" target="right"><span class="icon-caret-right"></span>成绩表</a></li>
	    <li><a href="${pageContext.request.contextPath }/leaveservlet?order=leavelist" target="right"><span class="icon-caret-right"></span>请假表</a></li>
	    <li><a href="${pageContext.request.contextPath }/outservlet?order=outlist" target="right"><span class="icon-caret-right"></span>出勤表</a></li>        
  	</c:if>
  	<c:if test="${user.qxname=='学生'}">
  	 	<li><a href="${pageContext.request.contextPath }/cardservlet?order=listcard" target="right"><span class="icon-caret-right"></span>打卡记录</a></li> 
        <li><a href="${pageContext.request.contextPath }/outservlet?order=listout" target="right"><span class="icon-caret-right"></span>考勤记录</a></li>        
  	</c:if>
  	<c:if test="${user.qxname=='教师'}">
  		<li><a href="${pageContext.request.contextPath }/studentservlet?order=studentlist" target="right"><span class="icon-caret-right"></span>学生列表</a></li>
	    <li><a href="${pageContext.request.contextPath }/userservlet?order=userlist" target="right"><span class="icon-caret-right"></span>用户列表</a></li>
	    <li><a href="${pageContext.request.contextPath }/classservlet?order=classlist" target="right"><span class="icon-caret-right"></span>班级列表</a></li>
	    <li><a href="${pageContext.request.contextPath }/teacherservlet?order=teacherlist" target="right"><span class="icon-caret-right"></span>教师列表</a></li>
	    <li><a href="${pageContext.request.contextPath }/cardservlet?order=cardlist" target="right"><span class="icon-caret-right"></span>打卡表</a></li>        
	    <li><a href="${pageContext.request.contextPath }/courseservlet?order=courselist" target="right"><span class="icon-caret-right"></span>课程表</a></li>
	    <li><a href="${pageContext.request.contextPath }/scoreservlet?order=scorelist" target="right"><span class="icon-caret-right"></span>成绩表</a></li>
	    <li><a href="${pageContext.request.contextPath }/leaveservlet?order=leavelist" target="right"><span class="icon-caret-right"></span>请假表</a></li>
	    <li><a href="${pageContext.request.contextPath }/outservlet?order=outlist" target="right"><span class="icon-caret-right"></span>出勤表</a></li>        
  	</c:if>
  </ul>  
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="javascript:void(0);" target="right" class="icon-home"> 首页</a></li>
  <li><a href="javascript:void(0);" id="a_leader_txt">网站信息</a></li>
  <li><b>当前语言：</b><span style="color:red;">中文</php></span>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="javascript:void(0);">中文</a> &nbsp;&nbsp;<a href="javascript:void(0);">英文</a> </li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="" name="right" width="100%" height="100%"></iframe>
</div>
<div style="text-align:center;">

</div>
</body>
</html>