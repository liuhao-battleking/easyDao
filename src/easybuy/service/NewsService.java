package easybuy.service;

import java.sql.SQLException;
import java.util.List;

import easybuy.model.MyPage;
import easybuy.model.News;


public interface NewsService {
public List<News> getAllNews()  throws SQLException;
	
	
	
	public MyPage getPage(int pageNo,int pageSize)throws SQLException;
	
	public int saveNews(News news) throws SQLException;
	public int updateNews(News news) throws SQLException;
	public int deleteNews(int nid) throws SQLException;
	
	public News getNewsById(int nid) throws SQLException;
}
