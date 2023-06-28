package query_practice.ch08;

import java.util.List;

public class App {

	public static void main(String[] args) {

		String ip = "127.0.0.1";
		String port = "1521";
		String sid = "orcl";
		String accountId = "hr";
		String accountPassword = "hr";
		
		DBManager dba = new DBManager(ip, port, sid, accountId, accountPassword);
		
//		System.out.println("\n===== insertCustomer() =====");
//		String id = "seoew";
//		String password = "seoew";
//		String name = "서은우";
//		String email = "seoew@email.com";
//		String address = "제주";
//		Customer insertCustomer = new Customer(id, password, name, email, address);
//		int rows = dba.insertCustomer(insertCustomer);
//		System.out.println("\n----- result -----");
//		
//		if(rows != 0) {
//			System.out.println("input success");
//		}
		
//		System.out.println("\n===== selectCustomerBySeq() =====");
//		int sequence = 38;
//		Customer customer = dba.selectCustomerBySeq(sequence);
//		
//		System.out.println("\n----- result -----");
//	    customer.printInfo();
		
//		System.out.println("\n===== selectCustomerById() =====");
//		String id = "kimsa";
//		Customer customer = dba.selectCustomerById(id);
//		
//		System.out.println("\n----- result -----");
//	    customer.printInfo();
			
//	    System.out.println("\n===== selectCustomerListAll() =====");
//	    List<Customer> customerList = dba.selectCustomerListAll();
//	    System.out.println("\n----- result -----");
//	    customerList.stream().forEach(it -> it.printInfo());
	    
//	    System.out.println("\n===== selectCustomerListByAddressLike() =====");
//	    String address = "대전";
//	    List<Customer> customerList = dba.selectCustomerListByAddressLike(address);
//	    int customerListSize = customerList.size();
//	    System.out.println("\n----- result -----");
//	    customerList.stream().forEach(it -> it.printInfo());
	    
//	    System.out.println("\n===== selectCustomerListByName() =====");
//	    String name = "김수아";
//	    List<Customer> customerList = dba.selectCustomerListByName(name);
//	    System.out.println("\n----- result -----");
//	    customerList.stream().forEach(it -> it.printInfo());
	    
//	    System.out.println("\n===== selectCustomerListByNamelike() =====");
//	    String name = "지원";
//	    List<Customer> customerList = dba.selectCustomerListByNameLike(name);
//	    System.out.println("\n----- result -----");
//	    customerList.stream().forEach(it -> it.printInfo());
	    
//	    System.out.println("\n===== updateCustomer() =====");
//		String id = "seoew";
//		String password = "seoew2";
//		String name = "서은우";
//		String email = "seoew@email.com";
//		String address = "제주";
//		Customer updateCustomer = new Customer(id, password, name, email, address);
//		int rows = dba.updateCustomer(updateCustomer);
//		System.out.println("\n----- result -----");
//		
//		if(rows != 0) {
//			System.out.println("update success");
//		}
		
//		System.out.println("\n===== deleteCustomerById() =====");
//		String customerId = "seoew";
//		int rows = dba.deleteCustomerById(customerId);
//		System.out.println("\n----- result -----");
//		
//		if(rows != 0) {
//			System.out.println("update success");
//		}
		
		System.out.println("\n===== existId() =====");
		String customerId = "kimsa";
		boolean exist = dba.existId(customerId);
		System.out.println("\n----- result -----");
		
		if(exist) {
			System.out.println("customerId=[" + customerId + "]" + " exist");
		}
	}

}
