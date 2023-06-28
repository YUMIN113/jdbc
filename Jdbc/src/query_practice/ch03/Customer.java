package query_practice.ch03;

public class Customer {

	private int seq; // 순번
	private String id; // 아이디
	private String password; // 패스워드
	private String name; // 이름
	private String email; // 이메일
	private String address; // 주소
	private String regdate; // 등록일시. yyyy.mm.dd

	// Constructors
	public Customer() {

	}
	
	public Customer(String id) {
		this.id = id;
	}
	
	public Customer(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public Customer(String id, String password, String email) {
		this.id = id;
		this.password = password;
		this.email = email;
	}

	public Customer(String id, String password, String name, String email, String address) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
	}

	public Customer(int seq, String id, String password, String name, String email, String address, String regdate) {
		this.seq = seq;
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.address = address;
		this.regdate = regdate;
	}

	public int getSeq() {
		return this.seq;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public String getRegdate() {
		return regdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void printInfo() {
		System.out.print("seq=[" + seq + "]");
		System.out.print(" | id=[" + id + "]");
		System.out.print(" | password=[" + password + "]");
		System.out.print(" | name=[" + name + "]");
		System.out.print(" | address=[" + address + "]");
		System.out.println(" | regdate=[" + regdate + "]");
	}
}
