package system.function;

import system.Checkable;
import system.Scannable;

public interface Payment {
	default void pay(int value) {
		String creditPattern = "\\d{4}-\\d{4}-\\d{8}";
		String pay;
		boolean isRun = true;
		
		System.out.print("결제 수단을 입력해주세요: ");
		do {
			pay = Scannable.scan();
			if(Checkable.stringVerify(pay, creditPattern)) {
				System.out.println("카드 결제가 완료되었습니다.");
				isRun = false;
			} else {
				try {
					if(cash(Integer.parseInt(pay), value))
						isRun = false;
				} catch (NumberFormatException e) {
					System.out.print("결제 수단을 확인해주세요: ");
				}
			}
		}while(isRun);
	}
	
	default boolean cash(int money, int value) {
		int left = money - value;
		if(left >= 0) {
			System.out.println("현금 결제가 완료되었습니다.");
			System.out.println("거스름돈은 " + left + "원 입니다." );
			return true;
		} else {
			System.out.println("금액이 부족합니다.");
			System.out.print("결제 수단을 입력해주세요: ");
			return false;
		}
	}
}
