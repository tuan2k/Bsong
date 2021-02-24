package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDBUlti {
	//RXAbpx82618  node9396-env-4681390.th.app.ruk-com.cloud
	public static final String URL = "jdbc:mysql://localhost:3306/bsong?useUnicode=yes&characterEncoding=UTF-8";
	//public static final String URL = "jdbc:mysql://node9396-env-4681390.th.app.ruk-com.cloud/bsong?useUnicode=yes&characterEncoding=UTF-8";
	public static final String USERNAME = "root";

	public static final String PASSWORD = "";

	public static Connection getConnection() {
		Connection conn = null;
		// Nạp driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Tạo đối tượng connection
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			// Lấy ra danh sách các danh mục
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement st) {
		if(st != null) {
			try {
				st.close();
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement pst) {
		if(pst != null) {
			try {
				pst.close();
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn, Statement st, ResultSet rs) {
		
		close(rs);
		close(st);
		close(conn);
	}
	
	public static void close(Connection conn, PreparedStatement pst, ResultSet rs) {
		
		close(rs);
		close(pst);
		close(conn);
	}
	
	public static void close(Connection conn, PreparedStatement pst) {
		
		close(pst);
		close(conn);
	}
}
