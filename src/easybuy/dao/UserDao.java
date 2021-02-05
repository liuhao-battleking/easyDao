package easybuy.dao;

import java.sql.SQLException;

import easybuy.model.User;

public interface UserDao extends BaseDao<User> {
	public User login(String username, String password) throws SQLException;
}
