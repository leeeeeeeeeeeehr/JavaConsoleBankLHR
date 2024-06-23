package banking;

public class SpecialAccount extends NormalAccount {
	int round = 0;

	public SpecialAccount(String accNumber, String name, int balance, int rate) {
		super(accNumber, name, balance, rate);
	}
	
	
	public void specialCalculate(int depo) {
		double normalRate = this.rate / 100.0;
		round += 1;
		
		if (round % 2 == 0) {
			double newBalance = Math.floor(getBalance() + (getBalance() * normalRate) + depo + 500);
			setBalance((int)newBalance);
		}
		else {
			double newBalance = Math.floor(getBalance() + (getBalance() * normalRate) + depo);
			setBalance((int)newBalance);
		}

		System.out.println("\n" + round + "회차 입금입니다.");
		System.out.println("********* 입금 완료 *********");
	}
	
	@Override
	public void showAccount() {
		System.out.println("계좌 번호: " + getNumber());
		System.out.println("고객 이름: " + getName());
		System.out.println("잔고: " + getBalance() + "원");
		System.out.println("기본 이자: " + this.rate + "%");
	}
}
