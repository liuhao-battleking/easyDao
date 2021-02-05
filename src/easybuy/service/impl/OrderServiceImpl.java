package easybuy.service.impl;

import java.sql.SQLException;
import java.util.List;

import easybuy.dao.OrderDao;
import easybuy.dao.impl.OrderDaoImpl;
import easybuy.model.MyPage;
import easybuy.model.OrderShow;
import easybuy.model.User;
import easybuy.service.OrderService;

public class OrderServiceImpl implements OrderService {
    
	OrderDao orderDao = new OrderDaoImpl();
	
	@Override
	public List<OrderShow> getAllOrder() throws SQLException {
		// TODO Auto-generated method stub
		return orderDao.getAll();
	}

	@Override
	public MyPage getPage(int pageNo, int pageSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveOrder(OrderShow order) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOrder(OrderShow order) throws SQLException {
		// TODO Auto-generated method stub
		return orderDao.update(order);
	}

	@Override
	public int deleteOrder(int oid) throws SQLException {
		// TODO Auto-generated method stub
		return orderDao.delete(oid);
	}

	@Override
	public OrderShow getOrderById(int oid) throws SQLException {
		// TODO Auto-generated method stub
		return orderDao.get(oid);
	}

}
