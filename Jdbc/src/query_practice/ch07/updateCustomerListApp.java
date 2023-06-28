package query_practice.ch07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class updateCustomerListApp {

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
			conn.setAutoCommit(false); // 자동 커밋 끄기
			System.out.println("Connection OK");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Statement
		PreparedStatement pstmt = null;

		// Query
		StringBuffer query = new StringBuffer();
		query.append("update customer set password=?, email=? where id=?");

		try {
			pstmt = conn.prepareStatement(query.toString());

			List<Customer> customerList = new ArrayList<Customer>();
			customerList.add(new Customer("kimsw", "kimsw1234", "", "kimsunwoo@emial.com", ""));
			customerList.add(new Customer("yoonjh", "yoonjh1234", "yoonjiho@email.com"));

			int updateRows = 0; // 총 실행된 행의 수
			for (int i = 0; i < customerList.size(); i++) {

				Customer customer = customerList.get(i);
				String customerId = customer.getId();
				String customerPassword = customer.getPassword();
				String customerEmail = customer.getEmail();

				pstmt.setString(1, customerPassword);
				pstmt.setString(2, customerEmail);
				pstmt.setString(3, customerId);

				int row = pstmt.executeUpdate();
				System.out.println("실행된 행의 수 =" + row);

				updateRows = updateRows + row;
			}

			System.out.println("총 실행된 행의 수 :  " + updateRows);
			if (updateRows != 2) {	// 테스트 : 값을 3으로 변경하고 실행슽
				throw new SQLException("실행된 행의 수 불일치");
			}

			conn.commit();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

			try {
				if (conn != null) {
					conn.rollback();
					System.out.println("Rollback");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

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