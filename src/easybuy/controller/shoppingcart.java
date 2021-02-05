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

import easybuy.model.Category;
import easybuy.model.MyCateItem;
import easybuy.model.MyPage;
import easybuy.model.News;
import easybuy.model.Orderdetail;
import easybuy.model.Product;
import easybuy.model.User;
import easybuy.service.CateService;
import easybuy.service.NewsService;
import easybuy.service.ProductService;
import easybuy.service.ShoppingCartService;
import easybuy.service.impl.CateServiceImpl;
import easybuy.service.impl.NewsServiceImpl;
import easybuy.service.impl.ProductServiceImpl;
import easybuy.service.impl.ShoppingCartServiceImpl;

/**
 * Servlet implementation class getAllServlet
 */
@WebServlet("/shoppingcart")
public class shoppingcart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl(); 
	NewsService newsService = new NewsServiceImpl();
	CateService cateService = new CateServiceImpl();  
	ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shoppingcart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType( "text/html" );
		response.setCharacterEncoding( "UTF-8" );
		String type = request.getParameter("type");
		if(type!=null&&!type.equals("")){
			if(type.equals("getAll")){
				
				if(request.getParameter("userid")!=null){
					
					String userid = request.getParameter("userid");
					try {
						List<Orderdetail> list = shoppingCartService.getAllOrder(Integer.valueOf(userid));
						request.setAttribute("orderdetails", list);
						request.getRequestDispatcher("shopping.jsp").forward(request, response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					System.out.println("尚未登录，请先登录");
					request.getRequestDispatcher("login.html").forward(request, response);
				}
				
			}else if(type.equals("add")){
				System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				System.out.println(request.getSession().getAttribute("user")!=null);
				if(request.getSession().getAttribute("user")!=null){
					User user = (User) request.getSession().getAttribute("user");
					System.out.println();
					System.out.println();
					System.out.println(user);
					String pid = request.getParameter("pid");
					Orderdetail orderdetail = new Orderdetail();//一个购物车单项目
					orderdetail.setOrderid(user.getUserid());
					orderdetail.setProductid(Integer.valueOf(pid));
					orderdetail.setQuantity(1);
					try {
						orderdetail.setCost(productService.getProductById(Integer.valueOf(pid)).getPrice());
						orderdetail.setProductpic(productService.getProductById(Integer.valueOf(pid)).getFilename());
						orderdetail.setProductname(productService.getProductById(Integer.valueOf(pid)).getName());
						int i = shoppingCartService.saveOrder(orderdetail);
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.getSession().setAttribute("userid", user.getUserid());
					System.out.println("添加购物车成功");
					request.getRequestDispatcher("shoppingcart?type=getAll&userid="+user.getUserid()).forward(request, response);
				}else{
					System.out.println("尚未登录，请先登录");
					request.getRequestDispatcher("login.html").forward(request, response);
				}
				
			}else if(type.equals("delete")){
				System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				System.out.println(request.getSession().getAttribute("user")!=null);
				if(request.getSession().getAttribute("user")!=null){
					User user = (User) request.getSession().getAttribute("user");
					System.out.println();
					System.out.println();
					System.out.println(user);
					String oid = request.getParameter("oid");
					int i = 0;
					try {
						i = shoppingCartService.deleteOrder(Integer.valueOf(oid));
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(i>0){
						System.out.println("删除商品成功");
						response.sendRedirect("shoppingcart?type=getAll&userid="+user.getUserid());
						

					}
				}else{
					request.getRequestDispatcher("mainPage.jsp").forward(request, response);
				}
				
			}else if(type.equals("updatequantity")){
				
				String orderdetailid = request.getParameter("orderdetailid");
				String quantity = request.getParameter("quantity").trim();
				System.out.println(quantity.length());
				int a = Integer.valueOf(quantity);
	            try {
					Orderdetail orderdetail = shoppingCartService.getOrderById(Integer.valueOf(orderdetailid));
					System.out.println(orderdetail);
					orderdetail.setQuantity(a);
					double price = productService.getProductById(Integer.valueOf(orderdetail.getProductid())).getPrice();
					orderdetail.setCost(Integer.valueOf(quantity)*price);
					int i = shoppingCartService.updateOrder(orderdetail);
					List<Orderdetail> list = new ArrayList<>();
					list.add(orderdetail);
					Gson gson = new Gson();
					String pro = gson.toJson(list);
					PrintWriter out = null;
					out = response.getWriter();
					out.write(pro);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("quantity:"+quantity);
				System.out.println("orderdetailid:"+orderdetailid);
				
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

}
