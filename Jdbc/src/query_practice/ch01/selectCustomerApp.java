package query_practice.ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectCustomerApp {

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
		ResultSet rs = null;

		// Query
		StringBuffer query = new StringBuffer();
		query.append("select seq, id, password, name, email, address");
		query.append(", to_char(regdate, 'yyyy.mm.dd') as regdate");
		query.append(" from customer");
		query.append(" where seq=?");


		try {
			pstmt = conn.prepareStatement(query.toString());

			int customerSeq = 2;
			pstmt.setInt(1, customerSeq);
			rs = pstmt.executeQuery();

			Customer customer = null;
			while (rs.next()) {

				int seq = rs.getInt("seq");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String regdate = rs.getString("regdate");

				customer = new Customer(seq, id, password, name, email, address, regdate);

			}

			customer.printInfo();

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}

	}

}