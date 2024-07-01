package system.function;

import system.Data;
import system.Showable;
import system.market.Balance;
import system.market.User;

public interface Money {
	default void calcSalary() {
		long l = Long.sum(System.currentTimeMillis(), -User.getUser().getStartWorkTime());
		int salary = (int)l / 60_000 * Balance.SALARY;
		int[] time = Data.time(l);
		Showable.printLine("알 림");
		System.out.printf("%s님은 ", User.getUser().getId());
		System.out.printf("%d시간 %d분 %d초 일했습니다.\n", time[0], time[1], time[2]);
		System.out.printf("오늘의 일당은 %d원 입니다.\n", salary);
	}
	
	default void printBalance() {
		Showable.printLine("잔 고");
		System.out.printf("현재 잔고는 %d입니다.\n", 
				Balance.getInstance().getNewBalance());
	}
	
	default void printSales() {
		int balance = Balance.getInstance().getNewBalance()
				- Balance.getInstance().getOldBalance();
			Showable.printLine("매 출");
			System.out.printf("오늘의 매출은 %d입니다.\n", balance);
	}
}
