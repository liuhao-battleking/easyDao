<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<%

    String path = request.getRequestURI();

    String basePath = request.getScheme() + "://"

            + request.getServerName() + ":" + request.getServerPort()

            + path;
    // http://localhost:8080/easybuy/
    

%>

<base
href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="../images/logo.gif" /></div>
	<div class="help"><a href="../index.html">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="index.html">首页</a></li>
			<li><a href="../userServlet?type=beforeManager">用户</a></li>
			<li><a href="../proServlet?type=beforeManager">商品</a></li>
			<li><a href="../orderServlet?type=beforeManager">订单</a></li>
			<li><a href="../commentServlet?type=beforeManager">留言</a></li>
			<li><a href="../newsServlet?type=beforeManager">新闻</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员pillys您好，今天是2012-12-21，欢迎回到管理后台。
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.html">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><em><a href="user-add.html">新增</a></em><a href="../userServlet?type=beforeManager">用户管理</a></dd>
				<dt>商品信息</dt>
				<dd><em><a href="productClass-add.html">新增</a></em><a href="../cateServlet?type=beforeManager">分类管理</a></dd>
				<dd><em><a href="product-add.html">新增</a></em><a href="../proServlet?type=beforeManager">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="../orderServlet?type=beforeManager">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="../commentServlet?type=beforeManager">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="news-add.html">新增</a></em><a href="../newsServlet?type=beforeManager">新闻管理</a></dd>
			</dl>
		</div>
	</div>
		<div class="main">
		<h2>新闻管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>新闻标题</th>
					<th>操作</th>
				</tr>
				
				
				<c:if test="${not empty page.list }">
		    <c:forEach items="${page.list }" var="news" varStatus="status">
		      
		      <tr>
					<td class="first w4 c">${news.newsid }</td>
					<td>${news.title }</td>
					<td class="w1 c"><a href="../newsServlet?type=beforeUpdate&nid=${news.newsid }">修改</a> <a href="../newsServlet?type=delete&nid=${news.newsid }">删除</a></td>
				</tr>
		    </c:forEach>
	
			</c:if>
				
				
			</table>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
