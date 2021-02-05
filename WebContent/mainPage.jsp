<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<%

    String path = request.getRequestURI();

    String basePath = request.getScheme() + "://"

            + request.getServerName() + ":" + request.getServerPort()

            + path;

%>

<base
href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
		
		
		$("#hots").click(function(){
			$("#hotpro").html("");
			$.ajax({
	 			url:'getAllServlet?type=ajaxhots',
	 			data:{
	 				
	 			},
	 			type:'post',
	 			dataType:'json',
	 			success:function(data){
	 				$.each(data,function(i,d){
	 					
	 					$("#hotpro").append("<li><dl><dt><a target='_blank' href='proServlet?type=detail&pid="+d.productid+"'><img src='"+d.filename+"' /></a></dt><dd class='title'><a href='proServlet?type=detail&pid="+d.productid+"' target='_blank'>"+d.name+"</a></dd><dd class='price'>"+d.price+"</dd></dl></li>");
	 					
	 					
	 					
	 				});
	 			},
	 			
	 		});
			
		});
		
		
	});
</script>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help">
	
	<c:choose>
		          <c:when test="${not empty sessionScope.user }">
		             <a href="shoppingcart?type=getAll&userid=${sessionScope.user.userid }" class="shopping">购物车</a>
		          </c:when>
		          <c:otherwise>
		           <a href="shoppingcart?type=getAll" class="shopping">购物车</a>
		          </c:otherwise>
		       </c:choose>
	
	
	
	
	   
		  
		       <c:choose>
		          <c:when test="${not empty sessionScope.user }">
		                      您好 ${sessionScope.user.username }   <a href="userServlet?type=out">退出登录</a>
		          </c:when>
		          <c:otherwise>
		           <a href="login.html">登录</a><a href="register.html">注册</a>
		          </c:otherwise>
		       </c:choose>
		   
	
	
			
	<a href="guestbook.html">留言</a>
	</div>
	
	
	
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
		           <dd><a href="proServlet?type=getType&typeid=${childitem.cateid}&pageNo=1">${childitem.catename }</a></dd>
		      </c:forEach>
		      </c:if>
			</c:forEach>
	      </c:if>
			
			
				
			</dl>
		</div>
		<div class="spacer"></div>
		<div class="last-view">
			<h2>最近浏览</h2>
			<dl class="clearfix">
			
			
			<c:if test="${not empty recents }">
		    <c:forEach items="${recents }" var="product" varStatus="status">
		      
		      
						<dt><a href="proServlet?type=detail&pid=${product.productid }" target="_blank"><img src="${product.filename }" /></a></dt>
						<dd><a href="proServlet?type=detail&pid=${product.productid }" target="_blank">${product.name }</a></dd>
						
				
		      
		    </c:forEach>
	        </c:if>
			</dl>
		</div>
	</div>
	<div class="main">
		<div class="price-off">
			<h2>今日特价<span style="float: right;font-style: italic;"><a id="hots" style="color: blue;" href="javascript:void(0)">换一换</a></span></h2>  
			<ul id="hotpro" class="product clearfix">
			
			<c:if test="${not empty products.list }">
		    <c:forEach items="${products.list }" var="product" varStatus="status">
		      
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
		</div>
		<div class="side">
			<div class="news-list">
				<h4>最新公告</h4>
				<ul>
				
				
				<c:if test="${not empty broadcasts.list }">
		    <c:forEach items="${broadcasts.list }" var="broadcast" varStatus="status">
		      
		      <li><a href="newsServlet?type=detail&nid=${broadcast.newsid }" target="_blank">${broadcast.title }</a></li>
		      
		    </c:forEach>
	
			</c:if>
				
			
				</ul>
			</div>
			<div class="spacer"></div>
			<div class="news-list">
				<h4>新闻动态</h4>
				<ul>
				
				<c:if test="${not empty news.list }">
		    <c:forEach items="${news.list }" var="newitem" varStatus="status">
		      
		      <li><a href="newsServlet?type=detail&nid=${newitem.newsid }" target="_blank">${newitem.title }</a></li>
		      
		    </c:forEach>
	
			</c:if>
				
					
					
				</ul>
			</div>
		</div>
		<div class="spacer clear"></div>
		<div class="hot">
			<h2>热卖推荐</h2>
			<ul class="product clearfix">
			
			
			   <c:if test="${not empty hots }">
		    <c:forEach items="${hots }" var="product" varStatus="status">
		      
		      <li>
					<dl>
						<dt><a href="proServlet?type=detail&pid=${product.productid }" target="_blank"><img src="${product.filename }" /></a></dt>
						<dd class="title"><a href="proServlet?type=detail&pid=${product.productid }" target="_blank">${product.name }</a></dd>
						<dd class="price">${product.price }</dd>
					</dl>
				</li>
		      
		    </c:forEach>
	
			</c:if>
			</ul>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号  <a href="login-manager.html" class="shopping">管理员登录</a>
</div>
</body>
</html>