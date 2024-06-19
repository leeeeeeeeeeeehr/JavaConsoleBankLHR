package banking;

/* 보통 예금 계좌를 표현한 클래스,
생성자를 통해서 이자 비율의 정보를 초기화한다. */
public class NormalAccount extends Account {
	// 확장한 멤버변수: 이자율
	double rate;

	public NormalAccount(String accNumber, String name, int balance, int rate) {
		super(accNumber, name, balance);
		this.rate = rate / 100.0;
	}
	
	public void normalCalculate(int depo) {
		int addBalance = getBalance() + (int)(getBalance() * this.rate) + depo;
		setBalance(addBalance);
	}
	
	@Override
	public void showAccount() {
		super.showAccount();
		System.out.println("기본 이자: " + (this.rate * 100) + "%");
	}
	
	


}
