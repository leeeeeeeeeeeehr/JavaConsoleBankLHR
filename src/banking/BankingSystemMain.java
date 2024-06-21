package banking;

import java.util.InputMismatchException;
import java.util.Scanner;

/* main 메서드를 포함한 클래스,
프로그램은 여기서 실행한다. */
public class BankingSystemMain {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		AccountManager manager = new AccountManager();
		AutoSaver saver = null;
		int choice = 0;
		
		// 프로그램 시작 시 계좌 정보가 들어있는 파일 역직렬화
		manager.readInfo();
		System.out.println();
		
		while (true) {
			try {
				manager.showMenu();
				choice = scanner.nextInt();
				System.out.println("==============================");
				
				if (choice < 1 || choice > 7) {
					throw new MenuSelectException();
				}

				switch (choice) {
				case ICustomDefine.MAKE:
					manager.makeAccount();
					System.out.println();
					break;
				case ICustomDefine.DEPOSIT:
					manager.depositMoney();
					break;
				case ICustomDefine.WITHDRAW:
					manager.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE:
					manager.showAccInfo();
					System.out.println();
					break;
				case ICustomDefine.DELETE:	
					manager.deleteAccount();
					System.out.println();
					break;
				case ICustomDefine.AUTO:
					try {
						// saver 인스턴스가 죽어있으면 생성해줌
						if (!saver.isAlive()) {
							saver = new AutoSaver(manager);
						}
					}
					// 인스턴스가 없는데 isAlive 쓰면 예외 발생함
					catch (Exception e) {
						System.out.println("\n[예외] saver 인스턴스가 없습니다.");
						// saver 인스턴스가 없으면 생성해줌
						saver = new AutoSaver(manager);
					}
					manager.autoOption(saver);
					
					break;
				case ICustomDefine.EXIT:
					System.out.println("\n******* 프로그램 종료 *******");
					
					// 프로그램 종료 시 계좌 정보를 파일로 직렬화
					manager.saveInfo();
					return;
				}
				
			}
			catch (InputMismatchException e) {
				System.out.println("\n[예외] 문자를 입력할 수 없습니다.\n");
				scanner.nextLine();
			}
			catch (MenuSelectException e) {
				System.out.println(e.getMessage());
				scanner.nextLine();
			}
		}
		
	}

}
