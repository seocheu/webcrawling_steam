package system.function;

import system.Checkable;
import system.Data;
import system.DataManager;
import system.Scannable;
import system.Showable;
import system.market.Balance;
import system.market.Product;
import system.market.Product_Data;

public interface Sell extends Payment, DataManager{
	default void sell(Product_Data[] pds, boolean isAdult) {
		Showable.printList(Product.products());
		System.out.print("판매하실 품목을 입력해주세요: ");
		String name = Scannable.scan();
		if(Checkable.checkName(Product.products(), name, isAdult)) {
			Checkable.checkStale(pds[Data.getIndex()]);
			int[] t = Data.time(Data.getSeconds());
			if(pds[Data.getIndex()].isExpire() && t[0] == 0 && t[1] < 1) {
				System.out.println("유통기한이 임박한 제품입니다.");
				return;
			}
			if(isAdult)
				if(!Checkable.adultVerify())
					return;
			System.out.print("판매하실 수량을 입력해주세요: ");
			int quantity = Scannable.scanNum();
			if(Product.products()[Data.getIndex()].getQuantity() < quantity) {
				System.out.println("재고가 부족합니다.");
			} else {
				int value = quantity * pds[Data.getIndex()].getPrice();
				pay(value);
				putOut(Product.products(), quantity);
			
				Balance.getInstance().addNewBalance(value);
				System.out.println();
				System.out.println(name + "이(가) " + quantity + "개 팔렸습니다.");
			}
		} else {
			System.out.println("일치하는 품목이 없습니다.");	
		}
	}
}
