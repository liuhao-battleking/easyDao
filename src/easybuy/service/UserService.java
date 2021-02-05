package easybuy.service;

import java.sql.SQLException;
import java.util.List;

import easybuy.model.MyPage;
import easybuy.model.User;



public interface UserService {
	public List<User> getAllUser()  throws SQLException;
	
	public User login(String username,String password)  throws SQLException;
	
	public MyPage getPage(int pageNo,int pageSize)throws SQLException;
	
	public int saveUser(User user) throws SQLException;
	public int updateUser(User user) throws SQLException;
	public int deleteUser(int uid) throws SQLException;
	
	public User getUserById(int uid) throws SQLException;
}
