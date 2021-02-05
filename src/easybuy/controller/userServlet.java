package easybuy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import easybuy.DBUtils.StringUtils;
import easybuy.model.MyPage;
import easybuy.model.User;
import easybuy.service.UserService;
import easybuy.service.impl.UserServiceImpl;



/**
 * Servlet implementation class userServlet
 */
@WebServlet("/userServlet")
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		if(type!=null&&!type.equals("")){
			if(type.equals("login")){
				try {
					String username = request.getParameter("username");
					String password = request.getParameter("password");
//					System.out.println("username:"+username); 
					String code = request.getParameter("code");
					if(!request.getSession().getAttribute("rand").equals(code)){
						request.setCharacterEncoding("gbk");
						response.setCharacterEncoding("gbk");
						PrintWriter out = response.getWriter();
						out.println("验证码错误，请重新输入");
					}else{
						User user = userService.login(username, password);
						if(user!=null){
							System.out.println("登录成功");
							request.getSession().setAttribute("user", user);
							System.out.println(request.getSession().getAttribute("user"));
							response.sendRedirect("index.jsp");
						}else{
							request.setCharacterEncoding("gbk");
							response.setCharacterEncoding("gbk");
							PrintWriter out = response.getWriter();
							out.println("用户名或者密码错误，请重新输入");
							System.out.println("用户名或者密码错误，请重新输入");
							
						}
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("out")){
				request.getSession().setAttribute("user", null);
				response.sendRedirect("index.jsp");
			}
			else if(type.equals("register")){
				try {
					String username = request.getParameter("username");
					String password = request.getParameter("password");
//					System.out.println("username:"+username); 
					String code = request.getParameter("code");
					if(!request.getSession().getAttribute("rand").equals(code)){
						request.setCharacterEncoding("gbk");
						response.setCharacterEncoding("gbk");
						PrintWriter out = response.getWriter();
						out.println("验证码错误，请重新输入");
					}else{
						User user = new User();
						user.setUsername(username);
						user.setPassword(password);
						int i = userService.saveUser(user);
						if(i>0){
							System.out.println("注册成功");
							response.sendRedirect("index.jsp");
						}else{
							request.setCharacterEncoding("gbk");
							response.setCharacterEncoding("gbk");
							PrintWriter out = response.getWriter();
							out.println("用户名或者密码错误，请重新输入");
							System.out.println("用户名或者密码错误，请重新输入");
						}
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(type.equals("manager")){
				try {
					String username = request.getParameter("username");
					String password = request.getParameter("password");
//					System.out.println("username:"+username); 
					String code = request.getParameter("code");
					if(!request.getSession().getAttribute("rand").equals(code)){
						request.setCharacterEncoding("gbk");
						response.setCharacterEncoding("gbk");
						PrintWriter out = response.getWriter();
						out.println("验证码错误，请重新输入");
					}else{
						User user = userService.login(username, password);
						if(user!=null&&user.getStatus()==1){
							System.out.println("管理员登录成功");
							
							response.sendRedirect("manage/index.html");
						}else{
							request.setCharacterEncoding("gbk");
							response.setCharacterEncoding("gbk");
							PrintWriter out = response.getWriter();
							out.println("用户名或者密码错误，请重新输入");
							System.out.println("用户名或者密码错误，请重新输入");
							
						}
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(type.equals("update")){
				try {
					this.doUpdate(request, response);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("addUser")){
				System.out.println("调用");
				try {
					this.add(request, response);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("beforeUpdate")){
				try {
					String uid = request.getParameter("uid");
					User user = userService.getUserById(Integer.valueOf(uid));
					request.setAttribute("user", user);
					request.getRequestDispatcher("manage/user-modify.jsp").forward(request, response);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("delete")){
				try {
					this.delete(request, response);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("beforeManager")){
				MyPage<User> page = new MyPage<>();
				int pageNo = 1;
				try {
					page.setList(userService.getAllUser());
					page.setPageNo(pageNo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(page);
				request.setAttribute("page", page);
				request.getRequestDispatcher("manage/user.jsp").forward(request, response);
				
			}
		}
		
		
		
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException{
		System.out.println("调用了doUpdate");
		User user = new User();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sexstr = request.getParameter("sex");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		
		String uid = request.getParameter("uid");
		int sex = 0;
		System.out.println(StringUtils.ifNull(uid));
		if(!StringUtils.ifNull(sexstr)){
			sex = Integer.parseInt(sexstr);
		}
		if(!StringUtils.ifNull(uid)){
			int userid = Integer.parseInt(uid);
			user.setUserid(userid);
		}
		user.setUsername(username);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setAddress(address);
		user.setSex(sex);
		if(user.getUserid()<=0){
			System.out.println(user);
			int j = userService.saveUser(user);
			if(j>0){
				System.out.println("添加成功");
				response.sendRedirect("manage/index.html");
			}
		}else{
			
			int i = userService.updateUser(user);
			if(i>0){
				System.out.println("更新成功");
				response.sendRedirect("manage/index.html");
			}
		}
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException{
		String uid = request.getParameter("uid");
		if(uid!=null&&!uid.equals("")){
			int i =userService.deleteUser(Integer.parseInt(uid));
			if(i>0){
				System.out.println("删除成功");
				response.sendRedirect("manage/index.html");
			}
		}
	}
	
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException{
		System.out.println("调用了doUpdate");
		User user = new User();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sexstr = request.getParameter("sex");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		
		String uid = request.getParameter("uid");
		int sex = 0;
		
		if(!StringUtils.ifNull(sexstr)){
			sex = Integer.parseInt(sexstr);
		}
		if(!StringUtils.ifNull(uid)){
			int userid = Integer.parseInt(uid);
			user.setUserid(userid);
		}
		user.setUsername(username);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setAddress(address);
		user.setSex(sex);
		System.out.println(user);
		if(user.getUserid()<=0){
			int j = userService.saveUser(user);
			if(j>0){
				System.out.println("添加成功");
				response.sendRedirect("manage/index.html");
			}
		}else{
			
			int i = userService.updateUser(user);
			if(i>0){
				System.out.println("更新成功");
				response.sendRedirect("manage/index.html");
			}
		}
	}
}
