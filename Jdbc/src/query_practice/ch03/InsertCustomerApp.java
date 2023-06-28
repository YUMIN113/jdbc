package query_practice.ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCustomerApp {

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
        query.append("insert into customer values(customer_seq.nextval,? ,? ,? ,? ,? ,sysdate)");
		try {
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, "kimsa");
			pstmt.setString(2, "kimsa");
			pstmt.setString(3, "김수아");
			pstmt.setString(4, "kimsa@email.com");
			pstmt.setString(5, "서울");
	
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