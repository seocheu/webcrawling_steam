package system.menu;

import system.Showable;
import system.function.Money;
import system.market.Balance;
import system.market.Product;
import system.market.User;

public interface CloseMenu extends Money{
	default void close() {
		calcSalary();
		
		Showable.printDate(User.getUser().getWorkTime());
		Balance balance = Balance.getInstance();
		int value = balance.getNewBalance() - balance.getOldBalance();
		System.out.println("오늘의 매출은 " + value + "입니다.");
		Balance.getInstance().setOldBalance(balance.getNewBalance());
		
		Showable.printSimpleList(Product.products());
	}
}
