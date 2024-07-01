package system.menu;

import system.Menu_Type;
import system.Scannable;
import system.Showable;
import system.function.Refund;
import system.function.Search;
import system.function.Sell;
import system.function.Store;
import system.market.Market;
import system.market.Product;

public interface OpenMenu extends Sell, Refund, Store, Search{
	default void openMenu() {
		boolean isRun = true;
		do {
			Market.menuType = Menu_Type.OPEN;
			Showable.printOpenMenu();
			int menu = Scannable.scanNum();
			switch(menu) {
				case 0: isRun = false; break;
				// 판매
				case 1: Market.menuType = Menu_Type.SELL; 
					sell(Product.products(), false); break;
				// 환불
				case 2: Market.menuType = Menu_Type.REFUND; 
					refund(); break;
				// 청소년 판매 불가 물품
				case 3: Market.menuType = Menu_Type.ADULT; 
					sell(Product.products(), true); break;
				// 입고
				case 4: Market.menuType = Menu_Type.STORE;
					store(); break;
				// 검색
				case 5: Market.menuType = Menu_Type.SEARCH;
					search(); break;
				default:
					isRun = true;
					System.out.println("정확한 번호를 입력해주세요."); break;
			}
			if(menu == 1 || menu == 2 || menu == 3 ||
					menu == 4 || menu == 5) {
				Scannable.scan();
			}
		} while(isRun);
	}
}
