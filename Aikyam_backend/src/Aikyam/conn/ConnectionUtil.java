package Aikyam.conn;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public ConnectionUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
		Class.forName("com.mysql.jdbc.Driver"); // Load Driver
		String host="jdbc:mysql://localhost:3306/"; // Define Connection URL
        String user="root";
        String password="root@123";
        String db="aikyam";
        String url=host+db; //Establish Connection
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
	
	public static Connection GetConn() {
		Connection conn = null;
		try {
		conn = ConnectionUtil.getConnection();
		System.out.println("conn established");
		return conn;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}

	public static boolean CloseConn(Connection conn) {
		boolean ans = true;
		if(conn!=null) {
			try {
				conn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
				ans = false;
			}
		}
		return ans;
	}
}
