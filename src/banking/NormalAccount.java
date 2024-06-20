package banking;

/* 보통 예금 계좌를 표현한 클래스,
생성자를 통해서 이자 비율의 정보를 초기화한다. */
public class NormalAccount extends Account {
	// 확장한 멤버변수: 이자율
	int rate;

	public NormalAccount(String accNumber, String name, int balance, int rate) {
		super(accNumber, name, balance);
		this.rate = rate;
	}
	
	// depo = 입금액
	public void normalCalculate(int depo) {
		// 이자율 소수로 변경
		double normalRate = this.rate / 100.0;
		
		double newBalance = Math.floor(getBalance() + (getBalance() * normalRate) + depo);
		// 계산한 금액으로 잔액 초기화
		setBalance((int)newBalance);
		
		System.out.println("********* 입금 완료 *********");
	}
	
	@Override
	public void showAccount() {
		super.showAccount();
		System.out.println("기본 이자: " + this.rate + "%");
	}
}
