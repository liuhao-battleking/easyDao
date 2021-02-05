package easybuy.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import easybuy.DBUtils.DBUtils;
import easybuy.dao.UserDao;
import easybuy.model.User;


public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	@Override
	public User login(String username, String password) throws SQLException {
		Connection conn = DBUtils.getConnection() ;
		User user = null;
		String sql = "select * from user where username = ? and password = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery() ;
		if(rs.next()){

			user = new User();
			user.setUserid(rs.getInt(1));
			user.setUsername(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setStatus(rs.getInt(10));
			
		}
		DBUtils.closeConn(conn, pstm, rs);

		return user;
	}
}
