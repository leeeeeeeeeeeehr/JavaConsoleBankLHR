package banking;

/* 높은 이율의 계좌를 표현한 클래스,
신용도가 높은 고객에게 개설이 허용되며 생성자를 통해 이자 비율의 정보를 초기화한다. */
public class HighCreditAccount extends Account {
	// 확장한 멤버변수: 이자율, 등급
	double rate;
	String grade;
	
	public HighCreditAccount(String accNumber, String name, int balance, int rate, String grade) {
		super(accNumber, name, balance);
		this.rate = rate / 100.0;
		this.grade = grade;
	}
	
	public void highCalculate(int depo) {
		int addBalance = getBalance() + (int)(getBalance() * this.rate) + depo;
		setBalance(addBalance);
		
	}
	
	@Override
	public void showAccount() {
		super.showAccount();
		System.out.println("기본 이자: " + (this.rate * 100) + "%");
		System.out.println("신용 등급: " + this.grade + "등급");
	}
	
	
	
	

}
