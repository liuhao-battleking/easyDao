package easybuy.dao.impl;

import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.Cookie;

import easybuy.dao.ProductDao;
import easybuy.model.MyPage;
import easybuy.model.Product;

public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao{
	
	
	
	@Override
	public List<Product> getHot() throws SQLException {
		Set<Integer> set = new HashSet<Integer>();
		Random ran = new Random();
		for (;;) {
		// 定义需要随机数的长度
		if (set.size() >= 8)
		break;
		// 定义随机数范围。1-100
		set.add(ran.nextInt(27) + 1);
		}
		
		
		List<Product> products = new ArrayList();
		for (Integer i : set)
			products.add(this.get(i));
		
		return products;
	}

	@Override
	public List<Product> getRecent() throws SQLException {
		

		return null;
	}

	@Override
	public List<Product> getType(int typeid) throws SQLException {
		
		return this.getDataParams("select * from product where catechildid = ?", typeid);
	}

	@Override
	public List<Product> getPageByType(int typeid, int pageNo,int pageSize) throws SQLException {
		
		return this.getDataParams("select * from product where catechildid = ? limit ?,?", typeid,(pageNo-1)*pageSize,pageSize);
	}

	@Override
	public int getCountForType(int typeid) throws SQLException {
		// TODO Auto-generated method stub
		return this.getDataParams("select * from product where catechildid = ? ", typeid).size();
	}

	
}
