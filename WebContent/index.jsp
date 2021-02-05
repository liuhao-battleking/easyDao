<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
  Cookie[] cookies =  request.getCookies();
  String[] pids = {"","","","",""};
  if(cookies!=null){
	  int index = 0;
	 for(Cookie c:cookies){
		  
		  if(c.getName().startsWith("pid")){
			  System.out.println("pid"+c.getName()+"************"+c.getValue()+"");
			  String str = c.getValue();
			  pids[index] = str;
			  index++;
			  if(index == 5){
				  break;
			  }
			  
			}
	  }
	
 } 

%>
<jsp:forward page="/getAllServlet">  
        <jsp:param name="type" value="getAll"></jsp:param>  
         <jsp:param name="pid0" value="<%= pids[0] %>"></jsp:param> 
         <jsp:param name="pid1" value="<%= pids[1] %>"></jsp:param>  
         <jsp:param name="pid2" value="<%= pids[2] %>"></jsp:param>  
         <jsp:param name="pid3" value="<%= pids[3] %>"></jsp:param>  
         <jsp:param name="pid4" value="<%= pids[4] %>"></jsp:param>   
</jsp:forward>
</body>
</html>