package banking;

import java.util.InputMismatchException;
import java.util.Scanner;

/* main 메서드를 포함한 클래스,
프로그램은 여기서 실행한다. */
public class BankingSystemMain {
	
	public static void main(String[] args) {
			
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		
		while (true) {
			try {
				AccountManager.showMenu();
				choice = scanner.nextInt();
				
				if (choice < 1 || choice > 5) {
					throw new MenuSelectException();
				}

				switch (choice) {
				case ICustomDefine.MAKE:
					AccountManager.makeAccount();
					System.out.println();
					break;
				case ICustomDefine.DEPOSIT:
					AccountManager.depositMoney();
					break;
				case ICustomDefine.WITHDRAW:
					AccountManager.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE:
					AccountManager.showAccInfo();
					System.out.println();
					break;
				case ICustomDefine.EXIT:
					System.out.println("\n=== 프로그램 종료 ===");
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
