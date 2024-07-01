package system.function;

import system.Checkable;
import system.Data;
import system.Scannable;
import system.Showable;
import system.market.Balance;
import system.market.Product;

public interface Refund {
	default void refund() {
		Showable.printList(Product.sales());
		if(Product.sales() != null) {
			System.out.print("환불할 물품을 입력해주세요: ");
			String name = Scannable.scan();
			
			if(Checkable.checkName(Product.sales(), name)) {
				System.out.print("환불할 물품의 수량을 입력해주세요: ");
				int value = Scannable.scanNum();
				if(Product.sales()[Data.getIndex()].getQuantity() < value) {
					System.out.println("수량을 잘못 입력하었습니다.");
				}
				else {
					Product.sales()[Data.getIndex()].subQuantity(value);
					int price = value * Product.sales()[Data.getIndex()].getPrice();
					Balance.getInstance().subNewBalance(price);
					if(Checkable.checkName(Product.products(), name)) {
						Product.products()[Data.getIndex()].addQuantity(value);
					}
					System.out.printf("%s %d개를 환불하였습니다.(%d원)\n", 
							Product.products()[Data.getIndex()].getName(), value, price);
				}
			} else {
				System.out.println("판매되지 않은 상품입니다");
			}
		}
	}
}
