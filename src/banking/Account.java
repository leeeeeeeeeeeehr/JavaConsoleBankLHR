package banking;

// 계좌 정보를 표현한 클래스
public class Account {
	// 계좌 번호 (String), 이름 (String), 잔액 (int)
	private String accNumber;
	private String name;
	private int balance;
	
	// 생성자
	public Account() {
		
	}
	public Account(String accNumber, String name, int balance) {
		this.accNumber = accNumber;
		this.name = name;
		this.balance = balance;
	}
	
	public void setNumber(String accNumber) {
		this.accNumber = accNumber;
	}
	public String getNumber() {
		return accNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getBalance() {
		return balance;
	}
	
	// 정보 출력용 메서드
	public void showAccount() {
		System.out.println("계좌 번호: " + this.accNumber);
		System.out.println("고객 이름: " + this.name);
		System.out.println("잔고: " + this.balance);
	}
	

}
