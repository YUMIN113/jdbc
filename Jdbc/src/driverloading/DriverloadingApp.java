package driverloading;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverloadingApp {

	public static void main(String[] args) {
		
		System.out.println("===== Driverloading =====");
		String driverName = "oracle.jdbc.driver.OracleDriver";
		
		try {
			Class.forName(driverName);
			System.out.println("Driverloading : OK");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("\n===== Connection =====");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String id = "hr";
		String password = "hr";
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, id, password);
			System.out.println("Connection : OK");
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
