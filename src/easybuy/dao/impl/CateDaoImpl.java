package easybuy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import easybuy.DBUtils.DBUtils;
import easybuy.dao.CateDao;
import easybuy.model.Category;


public class CateDaoImpl extends BaseDaoImpl<Category> implements CateDao{

	@Override
	public List<Category> getCatesById(int parentId) throws SQLException {
		Connection conn = DBUtils.getConnection() ;
		List<Category> list = new ArrayList<>();
		String sql = "select * from category where parentid = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, parentId);
		ResultSet rs = pstm.executeQuery() ;
		while(rs.next()){
//			book.setCanbrrow(rs.getInt("canbrow"));
			Category category = new Category();
			category.setCategoryid(rs.getInt(1));
			category.setName(rs.getString(2));
			category.setParentid(rs.getInt(3));
			list.add(category);
		}
		DBUtils.closeConn(conn, pstm, rs);
//		list = list.stream().sorted((User u1,User u2)->u2.getAge()-u1.getAge()).collect(Collectors.toList());
		return list;
	}

}
