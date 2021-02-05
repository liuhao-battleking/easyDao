package easybuy.dao;

import java.sql.SQLException;


import easybuy.model.Orderdetail;


public interface ShoppingCartDao extends BaseDao<Orderdetail>{
    
	public int deleteitem(int uid,int pid) throws SQLException ;
}
