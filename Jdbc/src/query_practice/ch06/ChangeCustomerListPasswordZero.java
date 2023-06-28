package query_practice.ch06;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ChangeCustomerListPasswordZero {

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
		String id = "hr";
		String password = "hr";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, id, password);
			System.out.println("Connection OK");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Statement
		CallableStatement cstmt = null;
		StringBuffer query = new StringBuffer();
		query.append("{call change_customer_password_zero(?) }");

		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer("kimsw"));
		customerList.add(new Customer("yoonjh"));

		try {
			cstmt = conn.prepareCall(query.toString());

			int updateRows = 0; // 총 실행된 행의 수
			for (int i = 0; i < customerList.size(); i++) {

				Customer customer = customerList.get(i);
				String customerId = customer.getId();

				cstmt.setString(1, customerId);
				int row = cstmt.executeUpdate();
				System.out.println("실행된 행의 수 =" + row);
				
				updateRows = updateRows + row;
			}

			System.out.println("총 실행된 행의 수 :  " + updateRows);

			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
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