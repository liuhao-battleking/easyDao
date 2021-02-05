package easybuy.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
	
	//不需要过滤的文件类型 ,所有以 .css 结尾的文件
    String excludeUrl = ".*\\.css$,.*\\.js$,.*\\.png$,.*\\.jpg$,.*\\.gif$";
	
    List<Pattern>  patterns ;
	
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest	request2 = (HttpServletRequest)request;
		HttpServletResponse response2 = (HttpServletResponse)response;
       
        System.out.println(request2.getRequestURI());
       
       //先判断是否需要过滤
       String url = request2.getRequestURI();
       if(!isUrlExclude(url)){
    	   chain.doFilter(request, response); 
       }else{   //需要过滤
    	   Object o =request2.getSession().getAttribute("user");
    	   if(o==null){
        	   response2.sendRedirect(request2.getServletContext().getContextPath()+"/login.html");   // 转发中斜杠是项目根目录，重定向中斜杠是tomcat根目录
           }else{
        	   chain.doFilter(request, response); 
        	   
           }
       }
       //获取请求方式，准备设置字符集
//      String method =  request2.getMethod();
//      if(method.equalsIgnoreCase("post")){
//    	  request2.setCharacterEncoding("UTF-8");
//      }
//      if(method.equalsIgnoreCase("get")){
//    	  request2.setCharacterEncoding("UTF-8");
//      }
      
      
//       
//       //获取请求路径，登录页面和首页不过滤，避免重定向循环
//       if(request2.getRequestURI().equals("/easybuy/login.html")||request2.getRequestURI().contains("image.jsp")){
//    	   chain.doFilter(request, response); 
//    	   return;
//       }
       
       
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		
		//获取配置文件中的参数
	   String s = fConfig.getInitParameter("excludeUrl");
	   System.out.println(s);
	   
	   if(s!=null&&!s.equals("")){
		   excludeUrl+=","+s;
	   }
	   
		patterns = new ArrayList<Pattern>();
		String[] tokens = excludeUrl.split(",");
		for(String str:tokens){
			patterns.add(Pattern.compile(str));
		}
		
	}
  
	//判断一个请求是否需要过滤的方法,如果不需要过滤返回false直接放行
	public boolean isUrlExclude(String url){
	    boolean flag = true;
	   //get URL 网络资源定位符，全球唯一，从协议开始; URI 从项目开始找，项目内部的资源
	    for(Pattern pattern : patterns){
	    	if(pattern.matcher(url).matches()){
	    		return false;
	    	}
	    }
	    return flag;
		
	}

} 