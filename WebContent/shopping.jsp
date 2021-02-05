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
		
		
	  $("a[id^='change']").click(function(){
			var orderdetailid = $(this).attr("id");
			orderdetailid=orderdetailid.replace('change','');
			var quantitynum = $("#quantity"+orderdetailid).val();
			
			$("#cost"+orderdetailid).html("");
			$.ajax({
	 			url:'shoppingcart?type=updatequantity&orderdetailid='+orderdetailid+'&quantity='+quantitynum,
	 			data:{
	 			
	 			},
	 			type:'post',
	 			dataType:'json',
	 			success:function(data){
	 				$.each(data,function(i,d){
	 					
	 					$("#cost"+orderdetailid).append(d.cost);
	 					$("#quantity"+orderdetailid).val(d.quantity);
	 					
	 					
	 					
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
			<li class="current"><a href="index.jsp">首页</a></li>
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
			<li class="first"><a href="#">音乐</a></li>
			<li><a href="#">影视</a></li>
			<li><a href="#">少儿</a></li>
			<li><a href="#">动漫</a></li>
			<li><a href="#">小说</a></li>
			<li><a href="#">外语</a></li>
			<li><a href="#">数码相机</a></li>
			<li><a href="#">笔记本</a></li>
			<li><a href="#">羽绒服</a></li>
			<li><a href="#">秋冬靴</a></li>
			<li><a href="#">运动鞋</a></li>
			<li><a href="#">美容护肤</a></li>
			<li><a href="#">家纺用品</a></li>
			<li><a href="#">婴幼奶粉</a></li>
			<li><a href="#">饰品</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.jsp">易买网</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form action="shoppingcart?type=createorder&uid=${orderdetail.orderdetailid }">
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品总价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				
				<c:if test="${not empty orderdetails }">
		    <c:forEach items="${orderdetails }" var="orderdetail" varStatus="status">
		     
		      <tr id="product_id_1">
					<td class="thumb"><img src="${orderdetail.productpic }" /><a href="proServlet?type=detail&pid=${orderdetail.productid }">${orderdetail.productname }</a></td>
					<td class="price" id="price_id_1">
						<span id="cost${orderdetail.orderdetailid }">${orderdetail.cost }</span>
						
					</td>
					
					<td class="number">
						
						<dl>
							<dt><input id="quantity${orderdetail.orderdetailid }" type="text" name="quantity" value="${orderdetail.quantity }" /></dt>
							<dd><a id="change${orderdetail.orderdetailid }" href="javascript:void(0)">修改</a></dd>
						
						</dl>
					</td>
					
					<td class="delete"><a href="shoppingcart?type=delete&oid=${orderdetail.orderdetailid }">删除</a></td>
				</tr>
				
		     </c:forEach>
	        </c:if>
				
				
			</table>
			<div class="button"><input type="submit" value="" /></div>
		</form>
	</div>
	<script type="text/javascript">
		
	</script>
</div>
<div id="footer">
	Copyright &copy; 2010 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
