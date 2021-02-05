package easybuy.service.impl;

import java.sql.SQLException;
import java.util.List;

import easybuy.dao.ProductDao;
import easybuy.dao.impl.ProductDaoImpl;
import easybuy.model.MyPage;
import easybuy.model.Product;
import easybuy.model.User;
import easybuy.service.ProductService;

public class ProductServiceImpl implements ProductService {
    
	ProductDao productDao = new ProductDaoImpl();

	@Override
	public List<Product> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return productDao.getAll();
	}
	
	@Override
	public List<Product> getHot() throws SQLException {
		// TODO Auto-generated method stub
		return productDao.getHot();
	}

	@Override
	public MyPage getPage(int pageNo, int pageSize) throws SQLException {
		
		MyPage page = new MyPage();
		page.setTotalNum(productDao.getCount());
		page.setPageNo(pageNo);
		page.setList(productDao.getPage(page.getPageNo(),page.getPageSize()));
		return page;
	}

	@Override
	public int saveProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return productDao.save(product);
	}

	@Override
	public int updateProduct(Product product) throws SQLException {
		// TODO Auto-generated method stub
		return productDao.update(product);
	}

	@Override
	public int deleteProduct(int uid) throws SQLException {
		// TODO Auto-generated method stub
		return productDao.delete(uid);
	}

	@Override
	public Product getProductById(int pid) throws SQLException {
		// TODO Auto-generated method stub
		return productDao.get(pid);
	}

	@Override
	public List<Product> getRecent() throws SQLException {
		// TODO Auto-generated method stub
		return productDao.getRecent();
	}

	@Override
	public List<Product> getType(int typeid) throws SQLException {
		// TODO Auto-generated method stub
		return productDao.getType(typeid);
	}

	@Override
	public MyPage getPageByType(int typeid, int pageNo, int pageSize) throws SQLException {
		MyPage page = new MyPage();
		page.setTotalNum(productDao.getCount());
		page.setPageNo(pageNo);
		page.setList(productDao.getPageByType(typeid, pageNo, pageSize));
		return page;
	}

	@Override
	public int getCountForType(int typeid) throws SQLException {
		// TODO Auto-generated method stub
		return productDao.getCountForType(typeid);
	}
	
	
}
