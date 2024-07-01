package system;

import java.text.SimpleDateFormat;
import java.util.Date;

import system.market.Market;
import system.market.Product_Data;

public interface Showable {
	
	
	static void printLine(String str) {
		System.out.println();
		System.out.printf("---------------   %-6s-----------------------------\n", str);
	}
	
	static void printLine() {
		System.out.println("------------------------------------------------------");
	}
	
	static void printProduct(Product_Data pd) {
		
		boolean isStale = Checkable.checkStale(pd);
		int[] t = Data.time(Data.getSeconds());
		
		// 메뉴 타입이 SELL이면 청소년 판매 금지 물품과 상한 음식은 출력 금지
		if(Market.menuType == Menu_Type.SELL) {
			if(pd.isAdult() || (pd.isExpire() == true && isStale))
				return;
		}
		
		// 메뉴 타입이 ADULT면 청소년 판매 가능 물품과 상한 음식은 출력 금지
		else if(Market.menuType == Menu_Type.ADULT) {
			if(!pd.isAdult() || (pd.isExpire() == true && isStale))
				return;
		}
		
		// 메뉴 타입이 EXPIRE면 유통기한이 있는 물품만 출력
		else if(Market.menuType == Menu_Type.EXPIRE )
			if(!pd.isExpire())
				return;
		
		// 메뉴 타입이 SEARCH_QUANTITY면 상품의 개수와 검색하는 숫자가 같은 상품만 출력
		else if(Market.menuType == Menu_Type.SEARCH_QUANTITY)
			if(pd.getQuantity() != Data.getValue())
				return;
					
		// 상품의 개수가 0이 아니면 출력
		if(pd.getQuantity() > 0) {
			System.out.printf("%-5s\t%9s원\t%4s개", pd.getName(), 
					pd.getPrice(), pd.getQuantity());
			
			if(pd.isExpire()) {
				
				if(isStale) {
					System.out.printf("\t%3s%s\t%5s", "", "판매 불가 상태", "");
					
				} else {
					if(t[0] > 72)
						System.out.printf("\t%8d일\t%5s", t[0] / 24, "");
					else
						System.out.printf("%5s%02d시 %02d분 %02d초\t%5s", "", t[0], t[1], t[2], "");
				}
			
			} else {
				System.out.printf("\t%9s\t%5s", "없 음", "");
			}
			if (Market.menuType != Menu_Type.SELL && Market.menuType != Menu_Type.ADULT) {
				System.out.println(pd.isAdult());
			} else {
				System.out.println();
			}
		}
	}
	
	public static void printList(Product_Data[] pds) {
		printLine("목 록");
		if (pds != null) {
			printListBar();
			for(Product_Data pd : pds) {
				printProduct(pd);
			}
		}
		System.out.println();
		printLine();
	}
	
	public static void printSimpleList(Product_Data[] pds) {
		printLine("남은 재고");
		for(Product_Data pd : pds) {
			if (pd.getQuantity() != 0) {
				System.out.print(pd.getName() + "\t");
				printAsterisk(pd.getQuantity());
				System.out.println();
			}
		}
	}
	
	public static void printAsterisk(int value) {
		for(int i = 0; i < value; i++) {
			System.out.print("*");
		}
	}
	
	public static void printUserMenu() {
		printLine("메 뉴");
		System.out.println(" 1. 재고 | 2. 현재 잔고 | 3. 매출액 | 4. 유통기한 | 5. 업무시작 ");
		printLine();
	}
	
	public static void printOpenMenu() {
		printLine("메 뉴");
		System.out.println(" 1. 판매 | 2. 환불 | 3. 19금 물품 | 4. 물품 입고 | 5. 물품 검색 ");
		printLine();
	}
	
	public static void printListBar() {
		System.out.printf(" %-11s%-10s%4s%12s", "이 름", "가 격", "개 수", "유통 기한");
		if (Market.menuType != Menu_Type.SELL && Market.menuType != Menu_Type.ADULT) {
			System.out.printf("%5s%s\n", "", "청소년 판매 불가");
		} else {
			System.out.println();
		}
	}
	
	public static void printDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		String strDate = sdf.format(date);
		
		System.out.println("(" + strDate + " 로그인)");
	}
}
