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
			<li><a href="index.html">首页</a></li>
			<li class="current"><a href="user.html">用户</a></li>
			<li><a href="product.html">商品</a></li>
			<li><a href="order.html">订单</a></li>
			<li><a href="guestbook.html">留言</a></li>
			<li><a href="news.html">新闻</a></li>
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
		<h2>留言管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>留言内容</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				
				
				
				<c:if test="${not empty page.list }">
		        <c:forEach items="${page.list }" var="comment" varStatus="status">
		      
		       <tr>
					<td class="first w4 c">${comment.commentid } </td>
					<td class="w1 c">${comment.nickname } </td>
					<td>${comment.content } </td>
					
					<c:choose>
					<c:when test="${comment.status==0 }">  
					<td class="w1 c">未回复 </td>
					</c:when>
					
					<c:otherwise> 
					<td class="w1 c">已回复 </td>
					</c:otherwise>
					</c:choose>
					
					
					<td class="w1 c"><a href="../commentServlet?type=beforeUpdate&cid=${comment.commentid }">回复</a> <a href="../commentServlet?type=delete&cid=${comment.commentid }">删除</a></td>
				</tr>
		    </c:forEach>
	
			</c:if>
				
			
			</table>
			<div class="pager">
				<ul class="clearfix">
					<li><a href="#">上一页</a></li>
					<li class="current">1</li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">下一页</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
