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
import easybuy.model.Product;
import easybuy.service.CateService;
import easybuy.service.NewsService;
import easybuy.service.ProductService;
import easybuy.service.impl.CateServiceImpl;
import easybuy.service.impl.NewsServiceImpl;
import easybuy.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class getAllServlet
 */
@WebServlet("/getAllServlet")
public class getAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl(); 
	NewsService newsService = new NewsServiceImpl();
	CateService cateService = new CateServiceImpl();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllServlet() {
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
		  String str0 = request.getParameter("pid0");
		  
		  String str1 = request.getParameter("pid1");
		  String str2 = request.getParameter("pid2");
		  String str3 = request.getParameter("pid3");
		  String str4 = request.getParameter("pid4");
		 
		  String[] strs = {str0,str1,str2,str3,str4};
		if(type!=null&&!type.equals("")){
			if(type.equals("getAll")){
			  try {
				  MyPage<Product> products = productService.getPage(1, 8);
				  request.setAttribute("products", products);
				  MyPage<News> news = newsService.getPage(1, 7);
				  request.setAttribute("news", news);
				  MyPage<News> broadcasts = newsService.getPage(2, 7);//公告
				  request.setAttribute("broadcasts", broadcasts);
				  List<MyCateItem> categories = cateService.getAll();  //分类
				  request.setAttribute("categories", categories);
				  List<Product> hots = productService.getHot();
				  request.setAttribute("hots", hots);
				  List<Product> recents = new ArrayList<>();
				  for(int i = 0;i<5;i++){
					  System.out.println(strs[i]);
				  }
				  for(int i = 0;i<5;i++){
					  if(!strs[i].equals("")){
						  System.out.println("str[i]"+Integer.valueOf(strs[i]));
						  Product product = productService.getProductById(Integer.valueOf(strs[i]));
						  recents.add(product);
					  }
				  }
				  System.out.println(strs.length+"length");
				  System.out.println(recents.size()+"size");
				  request.setAttribute("recents", recents);
				  System.out.println(hots);
				  request.getRequestDispatcher("mainPage.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else if(type.equals("ajaxhots")){
				
				try {
					List<Product> hot = productService.getHot();
					
					Gson gson = new Gson();
					String pro = gson.toJson(hot);
					PrintWriter out = null;
					out = response.getWriter();
					out.write(pro);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("shoppingcart")){
				if(request.getSession().getAttribute("userid")!=null&&!request.getSession().getAttribute("userid").equals("")){
					String uid = (String) request.getSession().getAttribute("userid");
					request.getRequestDispatcher("shopping.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("mainPage.jsp").forward(request, response);
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

}
