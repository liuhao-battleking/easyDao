package easybuy.dao;

import java.sql.SQLException;
import java.util.List;

import easybuy.model.MyPage;
import easybuy.model.Product;

public interface ProductDao extends BaseDao<Product>{
	
	public List<Product> getHot() throws SQLException ;

	public List<Product> getRecent() throws SQLException;
	
	public List<Product> getType(int typeid) throws SQLException;
	
	public List<Product> getPageByType(int typeid, int pageNo,int pageSize) throws SQLException;
	
	public int getCountForType(int typeid) throws SQLException;
}
