package easybuy.dao.impl;

import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import easybuy.DBUtils.DBUtils;
import easybuy.dao.BaseDao;



public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class clazz;

	public BaseDaoImpl() {
		ParameterizedType ps = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取T
		clazz = (Class) ps.getActualTypeArguments()[0];// 得到实际参数列表
		System.out.println(clazz.getName());
	}

	@Override
	public int save(T t) throws SQLException {
		Connection conn = DBUtils.getConnection();
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(clazz.getSimpleName());
		sql.append(" values(null");
		Field[] fs = clazz.getDeclaredFields();
		for (int i = 1; i < fs.length; i++) {
			sql.append(",?");
		}
		sql.append(")");
		System.out.println(sql.toString());
		// String sql = "insert into book values(null,?,?,?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql.toString());
		for (int i = 1; i < fs.length; i++) {
			String methodName = "get" + fs[i].getName().substring(0, 1).toUpperCase() + fs[i].getName().substring(1);
			try {
				Method m = clazz.getDeclaredMethod(methodName);
				System.out.println(m + "method");
				// Object o = clazz.newInstance();
				pstm.setObject(i, m.invoke(t));

			} catch (NoSuchMethodException | SecurityException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// DBUtils.closeConn(conn, pstm, null);
			}
		}

		int i = pstm.executeUpdate();
		return i;

	}

	@Override
	public T get(int id) throws SQLException {
		Connection conn = DBUtils.getConnection();
		StringBuffer sql = new StringBuffer("select * from ");
		sql.append(clazz.getSimpleName());
		sql.append(" where " + clazz.getSimpleName() + "id = ?");
		Field[] fs = clazz.getDeclaredFields();
		PreparedStatement pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, id);
		System.out.println(sql.toString());
		ResultSet rs = pstm.executeQuery();
		T o = null;
		
		if (rs.next()) {
			try {
				o = (T) clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int i = 0; i < fs.length; i++) {
//				System.out.println(fs[i].getName());
				String methodName = "set" + fs[i].getName().substring(0, 1).toUpperCase()
						+ fs[i].getName().substring(1);
				// System.out.println(methodName+"method");
				try {
					Class[] parameterTypes = new Class[1];
					// Field field = fs[i];
					// parameterTypes[0] = field.getType();
					// Method m = clazz.getMethod(methodName, parameterTypes);
					Method m = clazz.getMethod(methodName, fs[i].getType());
//					System.out.println(rs.getObject(i + 1));
					m.invoke(o, rs.getObject(i + 1));// m.invoke(o,rs.getObject(fs[i].getName()));
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		DBUtils.closeConn(conn, pstm, null);
		return (T) o;
	}

	@Override
	public int update(T t) throws SQLException {
		Connection conn = DBUtils.getConnection();
		StringBuffer sql = new StringBuffer("update ");
		sql.append(clazz.getSimpleName());
		sql.append(" set ");
		Field[] fs = clazz.getDeclaredFields();
		for (int i = 1; i < fs.length; i++) {
			sql.append(fs[i].getName() + " = ?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" where " + fs[0].getName() + "= ? ");
		System.out.println(sql.toString());
		PreparedStatement pstm = conn.prepareStatement(sql.toString());
		for (int i = 1; i < fs.length; i++) {
			String methodName = "get" + fs[i].getName().substring(0, 1).toUpperCase() + fs[i].getName().substring(1);
			try {
				Method m = clazz.getDeclaredMethod(methodName);
				
				pstm.setObject(i, m.invoke(t));
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		String methodid = "get" + fs[0].getName().substring(0, 1).toUpperCase() + fs[0].getName().substring(1);
		try {
			Method m = clazz.getDeclaredMethod(methodid);
			pstm.setObject(fs.length, m.invoke(t));
		} catch (Exception e) {
			e.printStackTrace();
		}
		int i = pstm.executeUpdate();
		DBUtils.closeConn(conn, pstm, null);
		return i;

	}

	@Override
	public int delete(int id) throws SQLException {
		Connection conn = DBUtils.getConnection();
		StringBuffer sql = new StringBuffer("delete from ");
		sql.append(clazz.getSimpleName());
		Field[] fs = clazz.getDeclaredFields();
		sql.append(" where " + fs[0].getName() + "= ? ");

		System.out.println(sql.toString());
		// String sql = "insert into book values(null,?,?,?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql.toString());
		String methodid = "get" + fs[0].getName().substring(0, 1).toUpperCase() + fs[0].getName().substring(1);
		try {
			Method m = clazz.getDeclaredMethod(methodid);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pstm.setInt(1, id);

		int i = pstm.executeUpdate();
		return i;

	}

	@Override
	public List<T> getAll() throws SQLException {
		Connection conn = DBUtils.getConnection();
		List<T> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer("select * from ");
		sql.append(clazz.getSimpleName());
		Field[] fs = clazz.getDeclaredFields();
		PreparedStatement pstm = conn.prepareStatement(sql.toString());
		System.out.println(sql.toString());
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			T o = null;
			try {
				o = (T) clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int i = 0; i < fs.length; i++) {

				String methodName = "set" + fs[i].getName().substring(0, 1).toUpperCase()
						+ fs[i].getName().substring(1);

				try {
					Class[] parameterTypes = new Class[1];
					Field field = fs[i];
					parameterTypes[0] = field.getType();
					Method m = clazz.getMethod(methodName, parameterTypes);
					m.invoke(o, rs.getObject(i + 1));

				} catch (NoSuchMethodException | SecurityException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			list.add(o);
		}
		DBUtils.closeConn(conn, pstm, null);
		return list;
	}

	@Override
	public List<T> getPage(int pageNo, int pageSize) throws SQLException {
		Connection conn = DBUtils.getConnection();
		System.out.println(pageNo + " pageNo");
		System.out.println(pageSize + " pageSize");
		List<T> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer("select * from ");
		sql.append(clazz.getSimpleName());
		sql.append(" limit ?,?");
		Field[] fs = clazz.getDeclaredFields();
		PreparedStatement pstm = conn.prepareStatement(sql.toString());
		pstm.setInt(1, (pageNo - 1) * pageSize);
		pstm.setInt(2, pageSize);
		ResultSet rs = pstm.executeQuery();
		System.out.println(sql.toString() + "sql语句");
		System.out.println(fs.length + "fs长度");

		while (rs.next()) {
			T o = null;
			try {
				o = (T) clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int i = 0; i < fs.length; i++) {

				String methodName = "set" + fs[i].getName().substring(0, 1).toUpperCase()
						+ fs[i].getName().substring(1);

				try {
					Class[] parameterTypes = new Class[1];
					Field field = fs[i];
					parameterTypes[0] = field.getType();
					Method m = clazz.getMethod(methodName, parameterTypes);
					m.invoke(o, rs.getObject(i + 1));

				} catch (NoSuchMethodException | SecurityException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			list.add(o);
		}
		DBUtils.closeConn(conn, pstm, null);
		return list;
	}

	@Override
	public int getCount() throws SQLException {
		Connection conn = DBUtils.getConnection();
		StringBuffer sql = new StringBuffer("select count(*) from ");
		sql.append(clazz.getSimpleName());

		// String sql = "insert into book values(null,?,?,?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql.toString());

		System.out.println(sql.toString());
		ResultSet rs = pstm.executeQuery();
		int i = 0;
		if (rs.next()) {
			i = rs.getInt(1);
		}

		return i;
	}

	
	/*
	 * sql select * from book where author = ? and bookname = ?   [zhangsan,"zzz"]
	 * */
	@Override
	public List<T> getDataParams(String sql, Object... objects) throws SQLException {
		Connection conn = DBUtils.getConnection();
		List<T> list = new ArrayList<>();
		Field[] fs = clazz.getDeclaredFields();
		PreparedStatement pstm = conn.prepareStatement(sql);
		for(int i = 0;i<objects.length;i++){
			pstm.setObject(i+1, objects[i]);
		}
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			T o = null;
			try {
				o = (T) clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int i = 0; i < fs.length; i++) {

				String methodName = "set" + fs[i].getName().substring(0, 1).toUpperCase()
						+ fs[i].getName().substring(1);

				try {
					Class[] parameterTypes = new Class[1];
					Field field = fs[i];
					parameterTypes[0] = field.getType();
					Method m = clazz.getMethod(methodName, parameterTypes);
					m.invoke(o, rs.getObject(i + 1));

				} catch (NoSuchMethodException | SecurityException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			list.add(o);
		}
		DBUtils.closeConn(conn, pstm, null);
		return list;
	}

}
