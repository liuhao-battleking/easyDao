package easybuy.dao;

import java.sql.SQLException;
import java.util.List;

import easybuy.model.Category;

public interface CateDao extends BaseDao<Category>{
	public List<Category> getCatesById(int parentId) throws SQLException;
}
