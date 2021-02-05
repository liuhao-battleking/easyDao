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
import easybuy.model.Comment;
import easybuy.model.MyPage;
import easybuy.model.OrderShow;
import easybuy.model.Product;
import easybuy.model.User;
import easybuy.service.CommentService;
import easybuy.service.OrderService;
import easybuy.service.ProductService;
import easybuy.service.UserService;
import easybuy.service.impl.CommentServiceImpl;
import easybuy.service.impl.OrderServiceImpl;
import easybuy.service.impl.ProductServiceImpl;
import easybuy.service.impl.UserServiceImpl;



/**
 * Servlet implementation class userServlet
 */
@WebServlet("/commentServlet")
public class commentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommentService commentService = new CommentServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public commentServlet() {
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
					String cid = request.getParameter("cid");
					Comment comment = commentService.getCommentById(Integer.valueOf(cid));
					request.setAttribute("comment", comment);
					request.getRequestDispatcher("manage/guestbook-modify.jsp").forward(request, response);
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
				MyPage<Comment> page = new MyPage<>();
				int pageNo = 1;
				try {
					page.setList(commentService.getAllComment());
					page.setPageNo(pageNo);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(page);
				request.setAttribute("page", page);
				request.getRequestDispatcher("manage/guestbook.jsp").forward(request, response);
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
		String cid = request.getParameter("cid");
		if(cid!=null&&!cid.equals("")){
			int i =commentService.deleteComment(Integer.valueOf(cid));
			if(i>0){
				System.out.println("删除成功");
				response.sendRedirect("manage/index.html");
			}
		}
	}
	
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException, SQLException{
		System.out.println("调用了doUpdate");
		String cid = request.getParameter("cid");
		Comment comment = commentService.getCommentById(Integer.valueOf(cid));
		
		String reply = request.getParameter("reply");
		comment.setReply(reply);
		System.out.println(comment);
	
			int i = commentService.updateComment(comment);
			if(i>0){
				System.out.println("回复成功");
				response.sendRedirect("manage/index.html");
			}

	}
}
