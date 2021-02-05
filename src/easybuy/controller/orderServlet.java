package easybuy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easybuy.DBUtils.StringUtils;
import easybuy.model.MyPage;
import easybuy.model.OrderShow;
import easybuy.model.Product;
import easybuy.model.User;
import easybuy.service.OrderService;
import easybuy.service.ProductService;
import easybuy.service.UserService;
import easybuy.service.impl.OrderServiceImpl;
import easybuy.service.impl.ProductServiceImpl;
import easybuy.service.impl.UserServiceImpl;



/**
 * Servlet implementation class userServlet
 */
@WebServlet("/orderServlet")
public class orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderServlet() {
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
				System.out.println("调用update");
				try {
					this.doUpdate(request, response);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("beforeUpdate")){
				try {
					String oid = request.getParameter("oid");
					OrderShow orderShow = orderService.getOrderById(Integer.valueOf(oid));
					request.setAttribute("ordershow", orderShow);
					request.getRequestDispatcher("manage/order-modify.jsp").forward(request, response);
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
			}else if(type.equals("detail")){
				try {
					this.boforeDetail(request, response);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("beforeManager")){
				System.out.println("调用了");
				MyPage<OrderShow> page = new MyPage<>();
				int pageNo = 1;
				try {
					page.setPageNo(pageNo);
					page.setList(orderService.getAllOrder());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(page);
				request.setAttribute("page", page);
				request.getRequestDispatcher("manage/order.jsp").forward(request, response);
			}
		}
		
		
		
	}

	

	private void boforeDetail(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		Product product = null;
//		try {
//			product = productService.getProductById(Integer.parseInt(pid));
//			request.setAttribute("product", product);
//			request.getRequestDispatcher("product-view.jsp").forward(request, response);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException{
		String oid = request.getParameter("oid");
		if(oid!=null&&!oid.equals("")){
			int i =orderService.deleteOrder(Integer.valueOf(oid));
			if(i>0){
				System.out.println("删除成功");
				response.sendRedirect("manage/index.html");
			}
		}
	}
	
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException{
		System.out.println("调用了doUpdate");
		String oid = request.getParameter("oid");
		OrderShow orderShow = orderService.getOrderById(Integer.valueOf(oid));
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String status = request.getParameter("status");
		orderShow.setUsername(name);
		orderShow.setUseraddress(address);
		orderShow.setStatus(Integer.valueOf(status));
		System.out.println(orderShow);
	
			int i = orderService.updateOrder(orderShow);
			if(i>0){
				System.out.println("更新成功");
				response.sendRedirect("manage/index.html");
			}

	}
}
