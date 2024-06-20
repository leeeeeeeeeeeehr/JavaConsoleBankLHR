package banking;

/* 메뉴 선택과 이자율 지정을 위한 인터페이스형 상수를 정의한다.
- 메뉴: 계좌 개설, 입금, 출금, 전체 계좌 정보 출력, 종료를 1 ~ 5로 지정한다.
- 이자율: 고객의 신용 등급을 A, B, C로 나눠서 7%, 4%, 2%로 지정한다. */
public interface ICustomDefine {
	// 메뉴 선택 시 사용할 인터페이스형 상수
	int MAKE = 1, DEPOSIT = 2, WITHDRAW = 3, INQUIRE = 4, EXIT = 5; 
	
	// 신용도 등급 선택
	char gradeA = 'A';
	char gradeB = 'B';
	char gradeC = 'C';
}
