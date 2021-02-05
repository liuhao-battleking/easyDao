package easybuy.service;

import java.sql.SQLException;
import java.util.List;

import easybuy.model.MyPage;
import easybuy.model.Product;
import easybuy.model.User;

public interface ProductService {
    public List<Product> getAll()  throws SQLException;
	
	
	
	public MyPage getPage(int pageNo,int pageSize)throws SQLException;
	
	public List<Product> getHot() throws SQLException ;
	public List<Product> getRecent() throws SQLException ;
	public int saveProduct(Product product) throws SQLException;
	public int updateProduct(Product product) throws SQLException;
	public int deleteProduct(int uid) throws SQLException;
	
	public Product getProductById(int uid) throws SQLException;
	
	public List<Product> getType(int typeid) throws SQLException;
	
	public MyPage getPageByType(int typeid, int pageNo,int pageSize) throws SQLException;
	
	public int getCountForType(int typeid) throws SQLException;
}
