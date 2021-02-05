package easybuy.service;

import java.sql.SQLException;
import java.util.List;

import easybuy.model.MyPage;

import easybuy.model.Orderdetail;

public interface ShoppingCartService {
	 public List<Orderdetail> getAllOrder(int uid)  throws SQLException;
		
		
		
		public MyPage getPage(int pageNo,int pageSize)throws SQLException;
		
		public int saveOrder(Orderdetail order) throws SQLException;
		public int updateOrder(Orderdetail order) throws SQLException;
		public int deleteOrder(int oid) throws SQLException;
		
		public Orderdetail getOrderById(int oid) throws SQLException;
}
