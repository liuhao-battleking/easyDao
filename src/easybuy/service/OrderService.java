package easybuy.service;

import java.sql.SQLException;
import java.util.List;

import easybuy.model.MyPage;
import easybuy.model.OrderShow;
import easybuy.model.User;

public interface OrderService {
    public List<OrderShow> getAllOrder()  throws SQLException;
	
	
	
	public MyPage getPage(int pageNo,int pageSize)throws SQLException;
	
	public int saveOrder(OrderShow order) throws SQLException;
	public int updateOrder(OrderShow order) throws SQLException;
	public int deleteOrder(int oid) throws SQLException;
	
	public OrderShow getOrderById(int oid) throws SQLException;
}
