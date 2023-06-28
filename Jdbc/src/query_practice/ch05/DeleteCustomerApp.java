package query_practice.ch05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteCustomerApp {

	public static void main(String[] args) {

		// Driver
		String driverName = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driverName);
			System.out.println("Driverloading OK");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		// Connection
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String dbId = "hr";
		String dbPassword = "hr";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, dbId, dbPassword);
			System.out.println("Connection OK");
		} catch (SQLException e) {
			e.printStackTrace();
		}


		// Statement
		PreparedStatement pstmt = null;
		
		// Query		
		StringBuffer query = new StringBuffer();
        query.append("delete customer where id=?");
		try {
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, "kimsa");
			
	
			int row = pstmt.executeUpdate();
			System.out.println("실행된 행의 수=" + row);
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}