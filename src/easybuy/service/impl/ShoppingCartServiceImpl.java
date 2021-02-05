package easybuy.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import easybuy.controller.shoppingcart;
import easybuy.dao.ShoppingCartDao;
import easybuy.dao.impl.ShoppingCartDaoImpl;
import easybuy.model.MyPage;
import easybuy.model.Orderdetail;
import easybuy.service.ShoppingCartService;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    ShoppingCartDao shoppingcartDao = new ShoppingCartDaoImpl();
	
	@Override
	public List<Orderdetail> getAllOrder(int uid) throws SQLException {
		List<Orderdetail> list = shoppingcartDao.getAll();
		List<Orderdetail> shoppingcart = new ArrayList<>();
		for(Orderdetail orderitem:list){
			if(orderitem.getOrderid()==uid)
				shoppingcart.add(orderitem);
		}
		return shoppingcart;
	}

	@Override
	public MyPage getPage(int pageNo, int pageSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveOrder(Orderdetail order) throws SQLException {
		// TODO Auto-generated method stub
		return shoppingcartDao.save(order);
	}

	@Override
	public int updateOrder(Orderdetail order) throws SQLException {
		// TODO Auto-generated method stub
		return shoppingcartDao.update(order);
	}

	@Override
	public int deleteOrder(int oid) throws SQLException {
		
		return shoppingcartDao.delete(oid);
	}

	@Override
	public Orderdetail getOrderById(int oid) throws SQLException {
		// TODO Auto-generated method stub
		return shoppingcartDao.get(oid);
	}

}
