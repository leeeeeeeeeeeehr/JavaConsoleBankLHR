package banking;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/* 컨트롤 클래스,
전반적인 기능을 구현한다. */
public class AccountManager {
	
		// Account 인스턴스를 저장할 수 있는 배열
//		public static Account accArr[] = new Account[50];
		// 인덱스로 사용할 변수
//		public static int accCnt = 0;
	
		HashSet<Account> hashSet;
		
		public AccountManager() {
			hashSet = new HashSet<Account>(); 
		}
	
		// 메뉴 출력
		public void showMenu() {
			System.out.println("============ Menu ============");
			System.out.print("1. 계좌 개설  ");
			System.out.print("2. 입  금  ");
			System.out.println("3. 출  금  ");
			System.out.print("4. 계좌 정보 출력   ");
			System.out.println("5. 계좌 정보 삭제   ");
			System.out.print("6. 저장   옵션    ");
			System.out.println("7. 프로그램   종료");
			System.out.print("\n메뉴 선택 >>> ");
		}
		
		// 계좌 개설
		public void makeAccount() {
			System.out.println("\n****** 신규 계좌 개설 ******\n");
			
			Scanner scanner = new Scanner(System.in);
			String accNum, name, inGrade;
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
				// set에 추가하기 전에 중복인지 확인
				for (Account acc : hashSet) {
					if (acc.equals(normal)) {
						System.out.println("\n### 중복 계좌가 발견되었습니다. ###");
						System.out.println("#### 새롭게 저장하시겠습니까? ####");
						System.out.print("\nYes / No >>> ");
						
						String YoN = scanner.nextLine();
						
						if (YoN.equalsIgnoreCase("y")) {
							// 중복 계좌있으면 삭제 후 새로운 계좌 추가 (덮어쓰기와 같은 기능)
							hashSet.remove(acc);
							hashSet.add(normal);
							System.out.println("### 새로운 계좌 정보가 업데이트 됐습니다. ###");
						}
						else if (YoN.equalsIgnoreCase("n")) {
							System.out.println("### 기존의 계좌 정보가 유지됩니다. ###");
						}
					}
				}
				hashSet.add(normal);
			}
			else if (choice == 2) {
				System.out.print("신용 등급 (A, B, C 중): ");
				inGrade = scanner.nextLine();
				char grade = inGrade.charAt(0);

				// 입력받은 정보를 통해 인스턴스 생성
				HighCreditAccount high = new HighCreditAccount(accNum, name, bal, rate, grade);
				// 인스턴스 배열에 추가
//				accArr[accCnt++] = high;
				hashSet.add(high);
			}
			System.out.println("\n*** 계좌가 개설되었습니다. ***");
		}
		
		// 입금
		public void depositMoney() {
			System.out.println("\n********** 입  금 **********");
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("---------------------------");
			System.out.println("계좌번호와 입금할 금액을 입력해주세요.");
			System.out.println("---------------------------");
			
			System.out.print("계좌번호: ");
			String accNum = scanner.nextLine();
			
			boolean isAcc = false;
			
			
			for (Account acc : hashSet) {
				if (acc.getNumber().equals(accNum)) {
					isAcc = true;
					
					try {
						System.out.print("입금할 금액: ");
						int depo = scanner.nextInt();
						scanner.nextLine();
						
						if (depo <= 0) {
							System.out.println("\n[예외] 금액을 잘못 입력하였습니다.");
						}
						else if (depo % 500 != 0) {
							System.out.println("\n[예외] 입금은 500원 단위로 가능합니다.");
						}
						else {
							if (acc instanceof NormalAccount) {
								((NormalAccount) acc).normalCalculate(depo);
							}
							else if (acc instanceof HighCreditAccount) {
								((HighCreditAccount) acc).highCalculate(depo);
							}
						}
					}
					catch (InputMismatchException e) {
						System.out.println("\n[예외] 금액만 입력할 수 있습니다.");
					}
				}
			}
			if (!isAcc) {
				System.out.println("\n**** 존재하지 않는 계좌입니다. ****");
			}
			
			System.out.println();
		}
		
		// 출금
		public void withdrawMoney() {
			System.out.println("\n********** 출  금 **********");
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("---------------------------");
			System.out.println("계좌번호와 출금할 금액을 입력해주세요.");
			System.out.println("---------------------------");
			
			System.out.print("계좌번호: ");
			String accNum = scanner.nextLine();
			
			boolean isAcc = false;
			
			
			for (Account acc : hashSet) {
				if (acc.getNumber().equals(accNum)) {
					isAcc = true;
					
					try {
						System.out.print("출금할 금액: ");
						int draw = scanner.nextInt();
						scanner.nextLine();
						
						if (draw <= 0) {
							System.out.println("\n[예외] 금액을 잘못 입력하였습니다.");
						}
						else if (draw % 1000 != 0) {
							System.out.println("\n[예외] 출금은 1000원 단위로 가능합니다.");
						}
						else if (draw > acc.getBalance()) {
							System.out.println("\n##### 잔고가 부족합니다. #####");
							System.out.println("### 금액 전체를 출금할까요? ###");
							System.out.print("\nYes / No >>> ");
							
							String YoN = scanner.nextLine();
							
							if (YoN.equalsIgnoreCase("y")) {
								acc.setBalance(acc.getBalance() - acc.getBalance());
								
								System.out.println("\n#### 전체 금액 출금 완료 ####");
								System.out.println("#### 현재 잔고는 0원입니다. ####");
							}
							else if (YoN.equalsIgnoreCase("n")) {
								
							}
						}
						else {
							acc.setBalance(acc.getBalance() - draw);
							
							System.out.println("********* 출금 완료 *********");					
						}
					}
					catch (InputMismatchException e) {
						System.out.println("\n[예외] 금액만 입력할 수 있습니다.");
					}
				}
			}
			if (!isAcc) {
				System.out.println("**** 존재하지 않는 계좌입니다. ****");
			}
			
			System.out.println();
		}
		
		// 전체 계좌 정보 출력
		public void showAccInfo() {
			System.out.println("\n*** 계좌 정보 출력 ***");
			
			for (Account acc : hashSet) {
				acc.showAccount();
				System.out.println("******************");
			}
		}
		
		// 계좌 정보 삭제
		public void deleteAccount() {
			System.out.println("\n********* 계좌 정보 삭제 *********");
			
			Scanner scanner = new Scanner(System.in);
			String accNum;
			
			System.out.println("\n-----------------------");
			System.out.println("삭제할 계좌 번호를 입력해주세요.");
			System.out.println("-----------------------");
			
			System.out.print("삭제할 계좌번호: ");
			accNum = scanner.nextLine();
			
			
			boolean isAcc = false;
			
			// for-each문은 요소 삭제를 지원하지 않으므로 Iterator 사용
			Iterator<Account> itr = hashSet.iterator();
			
			while (itr.hasNext()) {
				Account acc = itr.next();
				
				if (acc.getNumber().equals(accNum)) {
					isAcc = true;
					
					hashSet.remove(acc);
					System.out.println("\n*********** 삭제 완료 ***********");
				}
			}
			if (!isAcc) {
				System.out.println("\n***** 존재하지 않는 계좌입니다. *****");
			}
		}
		
		// 직렬화
		public void saveInfo() {
			try {
				ObjectOutputStream out =
						new ObjectOutputStream(new FileOutputStream("src/banking/AccountInfo.obj"));
				
				for (Account acc : hashSet) {
					out.writeObject(acc);
				}
				out.close();
			}
			catch (IOException e) {
				System.out.println("[ERROR] 직렬화 실패 [ERROR]");
			}
		}
		
		// 역직렬화
		public void readInfo() {
			try {
				ObjectInputStream in = 
						new ObjectInputStream(new FileInputStream("src/banking/AccountInfo.obj"));
				
				System.out.println("*** 저장된 계좌 목록 ***");

				while (true) {
					try {
						Account acc = (Account) in.readObject();
						hashSet.add(acc);
						
						
						acc.showAccount();
						System.out.println("********************");
					}
					catch (EOFException e) {
						break;
					}
				}
			}
			catch (Exception e) {
				System.out.println("\n[ERROR] 역직렬화 실패 [ERROR]\n");
			}
		}
		
		public void autoSave() {
			
		}
}