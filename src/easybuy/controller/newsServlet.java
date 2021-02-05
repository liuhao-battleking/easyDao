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
import easybuy.model.News;
import easybuy.model.Product;
import easybuy.model.User;
import easybuy.service.NewsService;
import easybuy.service.UserService;
import easybuy.service.impl.NewsServiceImpl;
import easybuy.service.impl.UserServiceImpl;



/**
 * Servlet implementation class userServlet
 */
@WebServlet("/newsServlet")
public class newsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NewsService newsService = new NewsServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newsServlet() {
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
				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("addNews")){
				System.out.println("调用");
				try {
					this.doUpdate(request, response);
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
			}else if(type.equals("beforeUpdate")){
				try {
					String nid = request.getParameter("nid");
					News news = newsService.getNewsById(Integer.valueOf(nid));
					request.setAttribute("news", news);
					request.getRequestDispatcher("manage/news-modify.jsp").forward(request, response);
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
				MyPage<News> page = new MyPage<>();
				int pageNo = 1;
				try {
					page.setList(newsService.getAllNews());
					page.setPageNo(pageNo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(page);
				request.setAttribute("page", page);
				request.getRequestDispatcher("manage/news.jsp").forward(request, response);
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
	
	private void boforeDetail(HttpServletRequest request, HttpServletResponse response) {
		String nid = request.getParameter("nid");
		News news = null;
		try {
			news = newsService.getNewsById(Integer.valueOf(nid));
			request.setAttribute("news", news);
			request.getRequestDispatcher("news-view.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException{
		String nid = request.getParameter("nid");
		if(nid!=null&&!nid.equals("")){
			int i = newsService.deleteNews(Integer.valueOf(nid));
			if(i>0){
				System.out.println("删除成功");
				response.sendRedirect("manage/index.html");
			}
		}
	}
//	
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException{
		System.out.println("调用了doUpdate");
		News news = null;
		String nid = request.getParameter("nid");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		
		if(!StringUtils.ifNull(nid)){
			news = newsService.getNewsById(Integer.valueOf(nid));
		}else{
			news = new News();
		}
		news.setTitle(title);
		news.setContent(content);
		if(news.getNewsid()<=0){
			int j =newsService.saveNews(news);
			if(j>0){
				System.out.println("添加成功");
				response.sendRedirect("manage/index.html");
			}
		}else{
			
			int i = newsService.updateNews(news);
			if(i>0){
				System.out.println("更新成功");
				response.sendRedirect("manage/index.html");
			}
		}
	}
}
