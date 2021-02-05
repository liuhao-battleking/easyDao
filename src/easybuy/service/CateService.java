package easybuy.service;

import java.sql.SQLException;
import java.util.List;

import easybuy.model.Category;
import easybuy.model.MyCateItem;
import easybuy.model.MyPage;


public interface CateService {
public List<MyCateItem>  getAll()  throws SQLException;
	
	
	
	public MyPage<Category> getPage(int pageNo,int pageSize)throws SQLException;
	
	public int saveCate(Category category) throws SQLException;
	public int updateCate(Category category) throws SQLException;
	public int deleteCate(int cid) throws SQLException;
	
	public Category getCateById(int cid) throws SQLException;
	
	public List<Category> getCatesById(int parentId) throws SQLException;
}
