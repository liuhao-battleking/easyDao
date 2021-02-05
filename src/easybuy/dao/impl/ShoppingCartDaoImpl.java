package easybuy.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import easybuy.DBUtils.DBUtils;
import easybuy.dao.ShoppingCartDao;
import easybuy.model.Orderdetail;

public class ShoppingCartDaoImpl extends BaseDaoImpl<Orderdetail> implements ShoppingCartDao{

	@Override
	public int deleteitem(int uid, int pid) throws SQLException {
		Connection conn = DBUtils.getConnection();
		
		String sql = "delete from orderdetail where orderid = ? and productid = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, uid);
		pstm.setInt(2, pid);
		int i  = pstm.executeUpdate();
		DBUtils.closeConn(conn, pstm, null);
		return i;
		
	}
    
	
}
