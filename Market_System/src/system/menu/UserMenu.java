package system.menu;

import system.Menu_Type;
import system.Scannable;
import system.Showable;
import system.function.Money;
import system.market.Market;
import system.market.Product;

public interface UserMenu extends OpenMenu, CloseMenu, Money {
	default void userMenu() {
		boolean isRun = true;
		do {
			Market.menuType = Menu_Type.USER;
			Showable.printUserMenu();
			int menu = Scannable.scanNum();
			switch(menu) {
				// 종료
				case 0: isRun = false; break;
				// 재고
				case 1: Market.menuType = Menu_Type.SHOW;
					Showable.printList(Product.products()); break;
				// 잔고
				case 2: Market.menuType = Menu_Type.BALANCE;
					printBalance(); break;
				// 매출
				case 3: Market.menuType = Menu_Type.SALES;
					printSales(); break;
				// 유통기한
				case 4: Market.menuType = Menu_Type.EXPIRE;
					Showable.printList(Product.products());  break;
				// 다음 메뉴
				case 5: openMenu(); break;
				default: System.out.println("정확한 번호를 입력해주세요."); break;
			}
			if(menu == 1 || menu == 2 ||
					menu == 3 || menu == 4) {
				Scannable.scan();
			}
		} while(isRun);
		close();
	}
}
