package easybuy.service.impl;

import java.sql.SQLException;
import java.util.List;

import easybuy.dao.UserDao;
import easybuy.dao.impl.UserDaoImpl;
import easybuy.model.MyPage;
import easybuy.model.User;
import easybuy.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
	
	@Override
	public List<User> getAllUser() throws SQLException {
		// TODO Auto-generated method stub
		return userDao.getAll();
	}

	@Override
	public User login(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.login(username, password);
	}

	@Override
	public MyPage getPage(int pageNo, int pageSize) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	@Override
	public int updateUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.update(user);
	}

	@Override
	public int deleteUser(int uid) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.delete(uid);
	}

	@Override
	public User getUserById(int uid) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.get(uid);
	}

}
