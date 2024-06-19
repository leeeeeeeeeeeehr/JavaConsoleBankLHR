package banking;

import java.util.Scanner;

/* 컨트롤 클래스,
전반적인 기능을 구현한다. */
public class AccountManager {
	
	// Account 인스턴스를 저장할 수 있는 배열
		public static Account accArr[] = new Account[50];
		// 인덱스로 사용할 변수
		public static int accCnt = 0;
		
		// 메뉴 출력
		public static void showMenu() {
			System.out.println("============ Menu ============");
			System.out.print("1. 계좌 개설  ");
			System.out.print("2. 입  금  ");
			System.out.println("3. 출  금  ");
			System.out.print("4. 전체 계좌 정보    ");
			System.out.println("5. 프로그램 종료");
			System.out.print("\n메뉴 선택 >>> ");
		}
		
		// 계좌 개설
		public static void makeAccount() {
			System.out.println("\n****** 신규 계좌 개설 ******\n");
			
			Scanner scanner = new Scanner(System.in);
			String accNum, name, grade;
			int choice, bal, rate;
			
			System.out.println("[계좌 선택]");
			System.out.println("1. 보통 계좌");
			System.out.println("2. 신용 신뢰 계좌");
			System.out.println("--------------");
			System.out.print("선택 계좌: ");
			choice = scanner.nextInt();
			// 버퍼 정리
			scanner.nextLine();
			
			System.out.print("\n계좌 번호: ");
			accNum = scanner.nextLine();
			System.out.print("고객 이름: ");
			name = scanner.nextLine();
			System.out.print("잔고: ");
			bal = scanner.nextInt();
			System.out.print("기본 이자 (단위: %): ");
			rate = scanner.nextInt();
			// 버퍼 정리
			scanner.nextLine();
			
			if (choice == 1) {
				// 입력받은 정보를 통해 인스턴스 생성
				NormalAccount normal = new NormalAccount(accNum, name, bal, rate);
				// 인스턴스 배열에 추가
				accArr[accCnt++] = normal;
			}
			else if (choice == 2) {
				System.out.print("신용 등급 (A, B, C 중): ");
				grade = scanner.nextLine();

				// 입력받은 정보를 통해 인스턴스 생성
				HighCreditAccount high = new HighCreditAccount(accNum, name, bal, rate, grade);
				// 인스턴스 배열에 추가
				accArr[accCnt++] = high;
			}
			System.out.println("\n*** 계좌가 개설되었습니다. ***");
		}
		
		// 입금 => 금액은 무조건 0원 이상으로, 금액에 대한 오류 검사 X
		public static void depositMoney() {
			System.out.println("\n********** 입  금 **********");
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("---------------------------");
			System.out.println("계좌번호와 입금할 금액을 입력해주세요.");
			System.out.println("---------------------------");
			
			System.out.print("계좌번호: ");
			String accNum = scanner.nextLine();
			
			boolean isAcc = false;
			
			
			for (int i = 0; i < accCnt; i++) {
				if (accArr[i].getNumber().equals(accNum)) {
					isAcc = true;
					
							
					System.out.print("입금할 금액: ");
					int depo = scanner.nextInt();
					scanner.nextLine();
									
					if (depo <= 0) {
						System.out.println("[예외] 금액을 잘못 입력하였습니다.");
					}
					else {
						if (accArr[i] instanceof NormalAccount) {
							accArr[i].setBalance(accArr[i].getBalance() + depo);
							((NormalAccount) accArr[i]).normalCalculate(depo);
						}
						else if (accArr[i] instanceof HighCreditAccount) {
							accArr[i].setBalance(accArr[i].getBalance() + depo);
							((HighCreditAccount) accArr[i]).highCalculate(depo);
						}
						System.out.println("********* 입금 완료 *********");
					}
				}
			}
			if (!isAcc) {
				System.out.println("**** 존재하지 않는 계좌입니다. ****");
			}
			
			System.out.println();
		}
		
		// 출금 => 금액은 무조건 0원 이상으로, 금액에 대한 오류 검사 X
		public static void withdrawMoney() {
			System.out.println("\n********** 출  금 **********");
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("---------------------------");
			System.out.println("계좌번호와 출금할 금액을 입력해주세요.");
			System.out.println("---------------------------");
			
			System.out.print("계좌번호: ");
			String accNum = scanner.nextLine();
			
			boolean isAcc = false;
			
			
			for (int i = 0; i < accCnt; i++) {
				if (accArr[i].getNumber().equals(accNum)) {
					isAcc = true;
					
					System.out.print("출금할 금액: ");
					int draw = scanner.nextInt();
					
					if (draw <= 0) {
						System.out.println("[예외] 금액을 잘못 입력하였습니다.");
					}
					else {
						accArr[i].setBalance(accArr[i].getBalance() - draw);
						
						System.out.println("********* 출금 완료 *********");					
					}
				}
			}
			if (!isAcc) {
				System.out.println("**** 존재하지 않는 계좌입니다. ****");
			}
			
			System.out.println();
		}
		
		// 전체 계좌 정보 출력
		public static void showAccInfo() {
			System.out.println("\n*** 계좌 정보 출력 ***");
			
			for (int i = 0; i < accCnt; i++) {
				accArr[i].showAccount();
				System.out.println("******************");
			}
		}

}