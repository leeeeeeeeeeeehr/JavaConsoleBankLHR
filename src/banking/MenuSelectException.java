package banking;

// 개발자가 직접 정의한 예외 처리 클래스
public class MenuSelectException extends Exception {

	public MenuSelectException() {
		super("\n[예외] 1 ~ 6 사이의 숫자만 입력할 수 있습니다.\n");
	}
	
}