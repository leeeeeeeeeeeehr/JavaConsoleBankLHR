package banking;

/* 높은 이율의 계좌를 표현한 클래스,
신용도가 높은 고객에게 개설이 허용되며 생성자를 통해 이자 비율의 정보를 초기화한다. */
public class HighCreditAccount extends Account {
	// 확장한 멤버변수: 이자율, 등급
	int rate;
	char grade;
	
	public HighCreditAccount(String accNumber, String name, int balance, int rate, char grade) {
		super(accNumber, name, balance);
		this.rate = rate;
		this.grade = grade;
	}
	
	public void highCalculate(int depo) {
		double normalRate = this.rate / 100.0;
		double highRate = 0.0;
		
		switch (Character.toUpperCase(grade)) {
		case ICustomDefine.gradeA: 
			highRate = 0.07;
			break;
		case ICustomDefine.gradeB:
			highRate = 0.04;
			break;
		case ICustomDefine.gradeC:
			highRate = 0.02;
			break;
		default:
			System.out.println("\n[예외] A, B, C만 입력할 수 있습니다.");
		}

		double newBalance =
				Math.floor(getBalance() + (getBalance() * normalRate) + (getBalance() * highRate) + depo);
		// 계산한 금액으로 잔액 초기화
		setBalance((int)newBalance);
		
		System.out.println("********* 입금 완료 *********");
	}
	
	@Override
	public void showAccount() {
		System.out.println("계좌 번호: " + getNumber());
		System.out.println("고객 이름: " + getName());
		System.out.println("잔고: " + getBalance() + "원");
		System.out.println("기본 이자: " + this.rate + "%");
		System.out.println("신용 등급: " + Character.toUpperCase(this.grade) + "등급");
	}
}
