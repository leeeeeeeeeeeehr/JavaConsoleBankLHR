package banking;

import java.util.Scanner;

/* main 메서드를 포함한 클래스,
프로그램은 여기서 실행한다. */
public class BankingSystemMain {
	
	public static void main(String[] args) {
			
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			AccountManager.showMenu();
			int choice = scanner.nextInt();
			
			switch (choice) {
			case ICustomDefine.MAKE:
				AccountManager.makeAccount();
				System.out.println("******************\n");
				break;
			case ICustomDefine.DEPOSIT:
				AccountManager.depositMoney();
				break;
			case ICustomDefine.WITHDRAW:
				AccountManager.withdrawMoney();
				break;
			case ICustomDefine.INQUIRE:
				AccountManager.showAccInfo();
				System.out.println("******************\n");
				break;
			case ICustomDefine.EXIT:
				System.out.println("\n=== 프로그램 종료 ===");
				return;
			}
		}
		
	}

}
