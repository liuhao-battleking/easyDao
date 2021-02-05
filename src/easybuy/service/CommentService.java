package easybuy.service;

import java.sql.SQLException;
import java.util.List;

import easybuy.model.Comment;
import easybuy.model.MyPage;


public interface CommentService {
	 public List<Comment> getAllComment()  throws SQLException;
		
		
		
		public MyPage getPage(int pageNo,int pageSize)throws SQLException;
		
		public int saveComment(Comment comment) throws SQLException;
		public int updateComment(Comment comment) throws SQLException;
		public int deleteComment(int cid) throws SQLException;
		
		public Comment getCommentById(int cid) throws SQLException;
}
