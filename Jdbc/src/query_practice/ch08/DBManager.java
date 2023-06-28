package query_practice.ch08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

	private String driverName = "oracle.jdbc.driver.OracleDriver"; // Driver
	private String dbUrl; // DB URL
	private String dbId; // DB Id
	private String dbPassword; // DB Password
	
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	
	StringBuffer query = new StringBuffer();
	
	List<Customer> customerList = new ArrayList<>();
	
	// 반환할 row
	private int rows = 0;

	// Constructor
	public DBManager(String ip, String port, String sid, String id, String password) {
		System.out.println("DBManager()");
		
		this.dbUrl = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + sid;
		this.dbId = id;
		this.dbPassword = password;

		driverLoading();
		getConnection(dbUrl, dbId, dbPassword);
	}

	private void driverLoading() {
		System.out.println("driverLoading()");

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	private void getConnection(String dbUrl, String dbId, String dbPassword) {
		
		try {
			conn = DriverManager.getConnection(dbUrl, dbId, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int insertCustomer(Customer insertCustomer) {
		System.out.println("DBManager.insertCustomer()");
		// Query		
        query.append("insert into customer values(customer_seq.nextval,? ,? ,? ,? ,? ,sysdate)");
		try {
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, insertCustomer.getId());
			pstmt.setString(2, insertCustomer.getPassword());
			pstmt.setString(3, insertCustomer.getName());
			pstmt.setString(4, insertCustomer.getEmail());
			pstmt.setString(5, insertCustomer.getAddress());
	
			rows = pstmt.executeUpdate();
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
		return rows;
	}
	
	public Customer selectCustomerById(String id) {
		System.out.println("DBManager.selectCustomerById()");
		query.append("select seq, id, password, name, email, address");
		query.append(", to_char(regdate, 'yyyy.mm.dd') as regdate");
		query.append(" from customer");
		query.append(" where id=?");

		Customer customer = null;

		try {
			pstmt = conn.prepareStatement(query.toString());

			String customerId = id;
			pstmt.setString(1, customerId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int seq = rs.getInt("seq");
				String targetId = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String regdate = rs.getString("regdate");

				customer = new Customer(seq, targetId, password, name, email, address, regdate);

			}

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
		
		return customer;

	}
	
	public Customer selectCustomerBySeq(int sequence) {
		System.out.println("DBManager.selectCustomerBySeq()");
		query.append("select seq, id, password, name, email, address");
		query.append(", to_char(regdate, 'yyyy.mm.dd') as regdate");
		query.append(" from customer");
		query.append(" where seq=?");

		Customer customer = null;

		try {
			pstmt = conn.prepareStatement(query.toString());

			int customerSeq = sequence;
			pstmt.setInt(1, customerSeq);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int seq = rs.getInt("seq");
				String targetId = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String regdate = rs.getString("regdate");

				customer = new Customer(seq, targetId, password, name, email, address, regdate);

			}

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
		
		return customer;

	}
	
	public List<Customer> selectCustomerListAll() {
		System.out.println("DBManager.selectCustomerListAll()");
		query.append("select seq, id, password, name, email, address");
		query.append(", to_char(regdate, 'yyyy.mm.dd') as regdate");
		query.append(" from customer");

		Customer customer = null;

		try {
			pstmt = conn.prepareStatement(query.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int seq = rs.getInt("seq");
				String targetId = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String regdate = rs.getString("regdate");

				customer = new Customer(seq, targetId, password, name, email, address, regdate);
				customerList.add(customer);

			}

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
		
		return customerList;

	}
	
	public List<Customer> selectCustomerListByAddressLike(String address) {
		System.out.println("DBManager.selectCustomerListByAddressLike()");
		query.append("select seq, id, password, name, email, address");
		query.append(", to_char(regdate, 'yyyy.mm.dd') as regdate");
		query.append(" from customer");
		query.append(" where address=?");

		Customer customer = null;

		try {
			pstmt = conn.prepareStatement(query.toString());

			String customeraddress = address;
			pstmt.setString(1, customeraddress);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int seq = rs.getInt("seq");
				String targetId = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String targetAddress = rs.getString("address");
				String regdate = rs.getString("regdate");

				customer = new Customer(seq, targetId, password, name, email, targetAddress, regdate);
				customerList.add(customer);

			}

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
		
		return customerList;

	}
	
	public List<Customer> selectCustomerListByName(String name) {
		System.out.println("DBManager.selectCustomerListByAddressLike()");
		query.append("select seq, id, password, name, email, address");
		query.append(", to_char(regdate, 'yyyy.mm.dd') as regdate");
		query.append(" from customer");
		query.append(" where name=?");

		Customer customer = null;

		try {
			pstmt = conn.prepareStatement(query.toString());

			String customername = name;
			pstmt.setString(1, customername);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int seq = rs.getInt("seq");
				String targetId = rs.getString("id");
				String password = rs.getString("password");
				String targetName = rs.getString("name");
				String email = rs.getString("email");
				String targetAddress = rs.getString("address");
				String regdate = rs.getString("regdate");

				customer = new Customer(seq, targetId, password, targetName, email, targetAddress, regdate);
				customerList.add(customer);

			}

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
		
		return customerList;

	}


	public List<Customer> selectCustomerListByNameLike(String name) {
		System.out.println("DBManager.selectCustomerListByNameLike()");
		query.append("select seq, id, password, name, email, address");
		query.append(", to_char(regdate, 'yyyy.mm.dd') as regdate");
		query.append(" from customer");
		query.append(" where name like ? ");

		Customer customer = null;

		try {
			pstmt = conn.prepareStatement(query.toString());

			String customername = name;
			pstmt.setString(1, "%" + customername + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {

				int seq = rs.getInt("seq");
				String targetId = rs.getString("id");
				String password = rs.getString("password");
				String targetName = rs.getString("name");
				String email = rs.getString("email");
				String targetAddress = rs.getString("address");
				String regdate = rs.getString("regdate");

				customer = new Customer(seq, targetId, password, targetName, email, targetAddress, regdate);
				customerList.add(customer);

			}

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
		
		return customerList;

	}
	
	public int updateCustomer(Customer customer) {
		query.append("update customer set password=?, name=?, email=?, address=? where id=?");
		try {
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, customer.getPassword());
			pstmt.setString(2, customer.getName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getAddress());
			pstmt.setString(5, customer.getId());
	
			rows = pstmt.executeUpdate();

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
		return rows;
	}
	
	public int deleteCustomerById(String customerId) {
		query.append("delete customer where id=?");
		try {
			
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, customerId);
	
			rows = pstmt.executeUpdate();
			
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
		return rows;
	}
	
	public boolean existId(String customerId) {
		System.out.println("DBManager.existId()");
		query.append("select id");
		query.append(" from customer");
		query.append(" where id=?");
		
		boolean exist = false;

		try {
			pstmt = conn.prepareStatement(query.toString());

			String id = customerId;
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs != null) {
				exist = true;
			}

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
		
		return exist;

	}
	
}
