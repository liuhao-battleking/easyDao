package easybuy.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T> {
   public int save(T t)throws SQLException;
   
   public T get(int id)throws SQLException;
   
   public int update(T t)throws SQLException;
   
   public int delete(int id)throws SQLException;
   
   public List<T> getAll() throws SQLException;
   
   public List<T> getPage(int pageNo,int pageSize) throws SQLException;
   
   public int getCount() throws SQLException;
   
   public List<T> getDataParams(String sql,Object...objects)throws SQLException;
}
