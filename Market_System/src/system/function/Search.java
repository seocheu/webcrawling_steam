package system.function;

import system.Checkable;
import system.Data;
import system.Menu_Type;
import system.Scannable;
import system.Showable;
import system.market.Market;
import system.market.Product;

public interface Search {
	default void search() {
		Showable.printLine("검 색");
		boolean isRun = true;
		System.out.print("0. 돌아가기, 1. 이름, 2. 재고의 양: ");

		do {
		int menu = Scannable.scanNum();
			switch(menu) {
				case 0:	return;	
				case 1: isRun = false; searchName(); break;
				case 2: isRun = false; searchQuantity(); break;
				default: System.out.print("입력이 잘못되었습니다: "); break;
			}
		} while(isRun);
	}
	
	private void searchName() {
		Market.menuType = Menu_Type.SEARCH_NAME;
		System.out.print("검색하실 이름을 입력해주세요: ");
		String name = Scannable.scan();
		if(Checkable.checkName(Product.products(), name)) {
			Showable.printLine("목 록");
			Showable.printListBar();
			Showable.printProduct(Product.products()[Data.getIndex()]);
			Showable.printLine();
		} else {
			System.out.println("일치하는 제품이 없습니다.");
		}
	}
	
	private void searchQuantity() {
		Market.menuType = Menu_Type.SEARCH_QUANTITY;
		System.out.print("검색하실 제고의 양을 입력해주세요: ");
		int value = Scannable.scanNum();
		Data.setValue(value);
		Showable.printList(Product.products());
	}
}
