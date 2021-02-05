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

import easybuy.DBUtils.StringUtils;
import easybuy.model.Category;
import easybuy.model.MyCateItem;
import easybuy.model.MyPage;
import easybuy.model.User;
import easybuy.service.CateService;
import easybuy.service.UserService;
import easybuy.service.impl.CateServiceImpl;
import easybuy.service.impl.UserServiceImpl;



/**
 * Servlet implementation class userServlet
 */
@WebServlet("/cateServlet")
public class cateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CateService cateService = new CateServiceImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cateServlet() {
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
		    if(type.equals("update")){
				try {
					this.doUpdate(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("add")){
				System.out.println("调用");
				try {
					this.doUpdate(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("beforeUpdate")){
				try {
					String cid = request.getParameter("cid");
					Category category = cateService.getCateById(Integer.valueOf(cid));
					List<Category> parents = cateService.getCatesById(0);
					request.setAttribute("parents", parents);
					request.setAttribute("category", category);
					request.getRequestDispatcher("manage/productClass-modify.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("delete")){
				try {
					this.delete(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("beforeManager")){
				System.out.println("调用cateServlet");
				
				List<MyCateItem> list = new ArrayList<>();
				
				try {
					List<Category> parents = cateService.getCatesById(0);
					
					for (Category category : parents) {
						MyCateItem myCateItem = new MyCateItem();
						myCateItem.setCateid(category.getCategoryid());
						myCateItem.setCatename(category.getName());
						List<Category> childs = cateService.getCatesById(category.getCategoryid());
						List<MyCateItem> childlist = new ArrayList<>();
						if(childs!=null&&childs.size()>0){
							for (Category child : childs) {
								MyCateItem childItem = new MyCateItem();
								childItem.setCateid(child.getCategoryid());
								childItem.setCatename(child.getName());
								childlist.add(childItem);
							}
						}
						myCateItem.setChild(childlist);
						list.add(myCateItem);
					}
					
					
					
					
					System.out.println(list);
					request.setAttribute("list", list);
					request.getRequestDispatcher("manage/productClass.jsp").forward(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
	
//	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException{
//		System.out.println("调用了doUpdate");
//		Category category = new Category();
//		
//		String classname = request.getParameter("classname");
//		
//		
//		
//		user.setUsername(username);
//		user.setPassword(password);
//		user.setMobile(mobile);
//		user.setAddress(address);
//		user.setSex(sex);
//		if(user.getUserid()<=0){
//			System.out.println(user);
//			int j = userService.saveUser(user);
//			if(j>0){
//				System.out.println("添加成功");
//				response.sendRedirect("manage/index.html");
//			}
//		}else{
//			
//			int i = userService.updateUser(user);
//			if(i>0){
//				System.out.println("更新成功");
//				response.sendRedirect("manage/index.html");
//			}
//		}
//	}
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException{
		String cid = request.getParameter("cid");
		if(cid!=null&&!cid.equals("")){
			int i =cateService.deleteCate(Integer.valueOf(cid));
			if(i>0){
				System.out.println("删除成功");
				response.sendRedirect("manage/index.html");
			}
		}
	}
	
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException{
		System.out.println("调用了doUpdate");
		Category category = null;
	
		String classname = request.getParameter("classname");
		
		String cid = request.getParameter("cid");
		
		if(!StringUtils.ifNull(cid)){
			int cateid = Integer.parseInt(cid);
			category = cateService.getCateById(cateid);
		}else{
			category = new Category();
		}
		category.setName(classname);
		System.out.println(category);
		if(category.getCategoryid()<=0){
			String parentid = request.getParameter("parentid");
			category.setParentid(Integer.valueOf(parentid));
			int j = cateService.saveCate(category);
			if(j>0){
				System.out.println("添加成功");
				response.sendRedirect("manage/index.html");
			}
		}else{
			String parentid = request.getParameter("parentid");
			category.setParentid(Integer.valueOf(parentid));
			int i = cateService.updateCate(category);
			if(i>0){
				System.out.println("更新成功");
				response.sendRedirect("manage/index.html");
			}
		}
	}
}
