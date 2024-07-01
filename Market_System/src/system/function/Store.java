package system.function;

import system.Checkable;
import system.Data;
import system.Scannable;
import system.Showable;
import system.market.Product;
import system.market.Product_Data;

public interface Store {
	default void store() {
		Showable.printLine("입 고");
		System.out.print("제품 이름을 입력해주세요: ");
		String name = Scannable.scan();
		switch(name) {
			case "": return;
		}
		if(Checkable.checkName(Product.products(), name)) {
			System.out.println("목록에 존재하는 상품입니다.");
			System.out.print("추가할 수량을 입력해주세요: ");
			int value = Scannable.scanNum();
			Product.getInstance().addQuan(Product.products(), value);
			Showable.printLine("추가 품목");
			Showable.printListBar();
			Showable.printProduct(Product.products()[Data.getIndex()]);
			System.out.println();
			System.out.println(name + "이(가) " + value + "개 추가되었습니다.");
			Showable.printLine();
		} else {
			System.out.print("가격을 입력해주세요: ");
			int price = Scannable.scanNum();
			System.out.print("수량을 입력해주세요: ");
			int quantity = Scannable.scanNum();
			System.out.println("유통기한을 입력해주세요.");
			System.out.println("YYYY-MM-DD HH:mm 또는");
			System.out.print("0 - 없음, 3600 - 1시간: ");
			boolean isCurrect = false;
			int expireTime = 0;
			do {
				String expireDate = Scannable.scan();
				int time[];
				String datePattern = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";
				if(Checkable.stringVerify(expireDate, datePattern)) {
					time = Checkable.parseDate(expireDate);
					expireTime = Data.seconds(time);
					isCurrect = true;
				} else {
					
					try {
						expireTime = Integer.parseInt(expireDate);
						isCurrect = true;
					} catch (NumberFormatException e) {
						// TODO: handle exception
						System.out.print("값을 확인해주세요: ");
					}
				}
			} while(!isCurrect);
			System.out.println("청소년 판매 가능 여부를 입력해주세요.");
			boolean isRun = true;
			boolean isAdult = false;
			do {
				System.out.print("1. 가능, 2. 불가능: ");
				int num = Scannable.scanNum();
				switch(num) {
					case 1: isRun = false; break;
					case 2: isRun = false; isAdult = true; break;
					default:
						System.out.println("입력값을 확인해주세요.");
				}
			} while(isRun);
			
			Product_Data pd = new Product_Data(name, price, quantity, expireTime, isAdult);
			Product.getInstance().addProduct(Product.products(), pd);
			Showable.printLine("추가 품목");
			Showable.printListBar();
			Showable.printProduct(pd);
			Showable.printLine();
			
		}
	}
}
