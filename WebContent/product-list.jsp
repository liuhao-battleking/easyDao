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
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function-manage.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help"><a href="#" class="shopping">购物车</a><a href="login.html">登录</a><a href="register.html">注册</a><a href="guestbook.html">留言</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="#">首页</a></li>
			<li><a href="#">图书</a></li>
			<li><a href="#">百货</a></li>
			<li><a href="#">品牌</a></li>
			<li><a href="#">促销</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			
			
			<c:if test="${not empty categories }">
		        <c:forEach items="${categories }" var="cateitem" varStatus="status">
		      
		        
		        <c:if test="${not empty cateitem.child }">
		   	   <c:forEach items="${cateitem.child }" var="childitem" varStatus="status">
		          <li><a href="proServlet?type=getType&typeid=${childitem.cateid}&pageNo=1">${childitem.catename }</a></li>
		      </c:forEach>
		      </c:if>
			</c:forEach>
	      </c:if>
			
			
			<li><a href="proServlet?type=getPage&pageNo=1">所有商品</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.html">易买网</a> &gt; ${typename }详情
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<dl>
			
			    <c:if test="${not empty categories }">
		        <c:forEach items="${categories }" var="cateitem" varStatus="status">
		      
		        <dt>${cateitem.catename }</dt>
		        <c:if test="${not empty cateitem.child }">
		   	   <c:forEach items="${cateitem.child }" var="childitem" varStatus="status">
		           <dd><a href="proServlet?type=getType&typeid=${childitem.cateid }&pageNo=1">${childitem.catename }</a></dd>
		      </c:forEach>
		      </c:if>
			</c:forEach>
	      </c:if>
			
			
				
			</dl>
		</div>
		<div class="spacer"></div>
		
	</div>
	<div class="main">
		<div class="product-list">
			<h2>全部${typename }商品</h2>
			
			<div class="clear"></div>
			<ul class="product clearfix">
			
			<c:if test="${not empty page.list }">
		    <c:forEach items="${page.list }" var="product" varStatus="status">
		      
		        <li>
					<dl>
						<dt><a href="proServlet?type=detail&pid=${product.productid }"><img src="${product.filename }" /></a></dt>
						<dd class="title"><a href="proServlet?type=detail&pid=${product.productid }">${product.name }</a></dd>
						<dd class="price">${product.price }</dd>
					</dl>
				</li>
		      </c:forEach>
	
			</c:if>
			
			
				
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
					<li><a href="proServlet?type=getType&typeid=${typeid}&pageNo=${page.pageNo-1 }">上一页</a></li>
					
					<li><a href="proServlet?type=getType&typeid=${typeid}&pageNo=${page.pageNo+1 }">下一页</a></li>
				<li><font>当前第${requestScope.page.pageNo}页</font></li>
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
