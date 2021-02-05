package easybuy.service.impl;

import java.sql.SQLException;
import java.util.List;

import easybuy.dao.NewsDao;
import easybuy.dao.impl.NewsDaoImpl;
import easybuy.model.MyPage;
import easybuy.model.News;
import easybuy.model.Product;
import easybuy.service.NewsService;

public class NewsServiceImpl implements NewsService {

	NewsDao newsDao = new NewsDaoImpl();
	
	@Override
	public List<News> getAllNews() throws SQLException {
		// TODO Auto-generated method stub
		return newsDao.getAll();
	}

	@Override
	public MyPage getPage(int pageNo, int pageSize) throws SQLException {
		MyPage<News> mypage = new MyPage<>();
		mypage.setList(newsDao.getPage(pageNo, pageSize));
		return mypage;
	}

	@Override
	public int saveNews(News news) throws SQLException {
		// TODO Auto-generated method stub
		return newsDao.save(news);
	}

	@Override
	public int updateNews(News news) throws SQLException {
		// TODO Auto-generated method stub
		return newsDao.update(news);
	}

	@Override
	public int deleteNews(int nid) throws SQLException {
		// TODO Auto-generated method stub
		return newsDao.delete(nid);
	}

	@Override
	public News getNewsById(int nid) throws SQLException {
		// TODO Auto-generated method stub
		return newsDao.get(nid);
	}

}
