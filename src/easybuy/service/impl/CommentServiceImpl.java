package easybuy.service.impl;

import java.sql.SQLException;
import java.util.List;

import easybuy.dao.CommentDao;
import easybuy.dao.impl.CommentDaoImpl;
import easybuy.model.Comment;
import easybuy.model.MyPage;
import easybuy.service.CommentService;

public class CommentServiceImpl implements CommentService {
    
	CommentDao commentDao = new CommentDaoImpl();
	
	@Override
	public List<Comment> getAllComment() throws SQLException {
		// TODO Auto-generated method stub
		return commentDao.getAll();
	}

	@Override
	public MyPage getPage(int pageNo, int pageSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveComment(Comment comment) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateComment(Comment comment) throws SQLException {
		// TODO Auto-generated method stub
		return commentDao.update(comment);
	}

	@Override
	public int deleteComment(int cid) throws SQLException {
		// TODO Auto-generated method stub
		return commentDao.delete(cid);
	}

	@Override
	public Comment getCommentById(int cid) throws SQLException {
		// TODO Auto-generated method stub
		return commentDao.get(cid);
	}

}
