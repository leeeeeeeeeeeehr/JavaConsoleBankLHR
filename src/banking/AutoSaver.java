package banking;

import java.util.HashSet;

public class AutoSaver extends Thread {
	/* 프로그램 시작 시 인스턴스를 이미 생성했기 때문에 또 생성하면 안됨
	새롭게 생성하면 입력은 main에서 생성한 인스턴스에서,
	출력은 여기서 생성한 인스턴스에서 실행되기 때문에 제대로 이뤄지지 않음 */
	AccountManager manager;
	
	public AutoSaver(AccountManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void run() {
		try {
			/* 데몬 쓰레드이기 때문에 프로그램 종료 시 종료됨
			따라서 무한 루프로 만들었음 */
			while (true) {
				manager.autoSave();
				sleep(3000);
			}
		}
		catch (Exception e) {
			
		}
	}
}
