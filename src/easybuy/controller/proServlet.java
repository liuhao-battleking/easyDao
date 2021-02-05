package easybuy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import easybuy.DBUtils.StringUtils;
import easybuy.model.MyCateItem;
import easybuy.model.MyPage;
import easybuy.model.Product;
import easybuy.model.User;
import easybuy.service.CateService;
import easybuy.service.ProductService;
import easybuy.service.UserService;
import easybuy.service.impl.CateServiceImpl;
import easybuy.service.impl.ProductServiceImpl;
import easybuy.service.impl.UserServiceImpl;

/**
 * Servlet implementation class userServlet
 */
@WebServlet("/proServlet")
public class proServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();
	CateService cateService = new CateServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public proServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] index = new String[5];
		for (int i = 0; i > index.length; i++) {
			index[i] = i + "";
		}
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		if (type != null && !type.equals("")) {
			if (type.equals("update")) {
				System.out.println("调用update");
				try {
					this.doUpdate(request, response);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (type.equals("beforeUpdate")) {
				try {
					String pid = request.getParameter("pid");
					Product product = productService.getProductById(Integer.valueOf(pid));
					request.setAttribute("product", product);
					request.getRequestDispatcher("manage/product-modify.jsp").forward(request, response);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (type.equals("delete")) {
				try {
					this.delete(request, response);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (type.equals("getPage")) {

				try {
					MyPage<Product> page = null;
					String pageNo = request.getParameter("pageNo");
					List<MyCateItem> categories = cateService.getAll(); // 分类
					page = productService.getPage(Integer.valueOf(pageNo), 8);
					request.setAttribute("categories", categories);
					request.setAttribute("page", page);
					request.getRequestDispatcher("product-all.jsp").forward(request, response);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (type.equals("detail")) {
				try {
					int pid = Integer.valueOf(request.getParameter("pid"));

					// Cookie productInfo = new Cookie("pid"+pid,pid+"");
					// productInfo.setMaxAge(5*60);
					response.addCookie(new Cookie("pid" + pid, pid + ""));
					System.out.println("pid" + pid + "************" + pid + "");
					this.boforeDetail(request, response);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (type.equals("getType")) {
				try {
					String typeid = request.getParameter("typeid");
					String pageNoStr = request.getParameter("pageNo");
					String typename = cateService.getCateById(Integer.valueOf(typeid)).getName();
					MyPage<Product> page = null;
					int pageNo = Integer.valueOf(pageNoStr);
					if(pageNo<1){
						pageNo = 1;
					}
					int totalNum = productService.getCountForType(Integer.valueOf(typeid));
					int totalPage = totalNum%8==0?totalNum/8:totalNum/8+1;
					if(pageNo>totalPage){
						pageNo=totalPage;
					}
					page = productService.getPageByType(Integer.valueOf(typeid),pageNo , 8);
					List<MyCateItem> categories = cateService.getAll(); // 分类
					request.setAttribute("categories", categories);
					request.setAttribute("page", page);
					request.setAttribute("typename", typename);
					request.setAttribute("typeid", typeid);
					request.getRequestDispatcher("product-list.jsp").forward(request, response);
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (type.equals("beforeManager")) {
				MyPage<Product> page = new MyPage<>();
				int pageNo = 1;
				try {
					page.setList(productService.getAll());
					page.setPageNo(pageNo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(page);
				request.setAttribute("page", page);
				request.getRequestDispatcher("manage/product.jsp").forward(request, response);
			}
		}
	}

	private void boforeDetail(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		Product product = null;
		try {
			product = productService.getProductById(Integer.parseInt(pid));
			request.setAttribute("product", product);
			request.getRequestDispatcher("product-view.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {
		String pid = request.getParameter("pid");
		if (pid != null && !pid.equals("")) {
			int i = productService.deleteProduct(Integer.parseInt(pid));
			if (i > 0) {
				System.out.println("删除成功");
				response.sendRedirect("manage/index.html");
			}
		}
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, SQLException {
		System.out.println("调用了doUpdate");
		Product product = new Product();

		String name = request.getParameter("name");
		String cateidstr = request.getParameter("cateid");
		String pricestr = request.getParameter("price");
		String stockstr = request.getParameter("stock");

		String pid = request.getParameter("pid");
		double price = 0;
		int stock = 0;
		int cateid = 0;

		if (!StringUtils.ifNull(pricestr)) {
			price = Double.parseDouble(pricestr);
		}
		if (!StringUtils.ifNull(cateidstr)) {
			cateid = Integer.parseInt(cateidstr);
		}
		if (!StringUtils.ifNull(stockstr)) {
			stock = Integer.parseInt(stockstr.trim());
		}
		if (!StringUtils.ifNull(pid)) {
			int productid = Integer.parseInt(pid);
			product.setProductid(productid);
		}
		product.setName(name);
		product.setCateid(cateid);
		product.setPrice(price);
		product.setStock(stock);
		System.out.println(product);
		if (product.getProductid() <= 0) {
			int j = productService.saveProduct(product);
			if (j > 0) {
				System.out.println("添加成功");
				response.sendRedirect("manage/index.html");
			}
		} else {

			int i = productService.updateProduct(product);
			if (i > 0) {
				System.out.println("更新成功");
				response.sendRedirect("manage/index.html");
			}
		}
	}
}
