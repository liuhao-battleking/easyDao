package easybuy.DBUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class DBUtils {
	private static InputStream in = null;
	private static Properties pro = new Properties();
	private static Connection con = null;
	private static String DRIVER = null;
	private static String USERNAME = null;
	private static String PASSWORD = null;
	private static String URL = null;
	static {
		try {
			in = DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			pro.load(in);
			URL = pro.getProperty("jdbc.url");
			USERNAME = pro.getProperty("jdbc.username");
			PASSWORD = pro.getProperty("jdbc.password");
			DRIVER = pro.getProperty("jdbc.driver");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	}

	public static Connection getConnection() {
		
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return con;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return null;

	}

	public static void closeConn(Connection con, Statement st, ResultSet rs) throws SQLException {
		if (rs != null)
			rs.close();
		if (st != null)
			st.close();
		if (con != null)
			con.close();
	}
	public static void main(String[] args) {
		System.out.println(DBUtils.getConnection());	
	}
}
