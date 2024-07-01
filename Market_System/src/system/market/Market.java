package system.market;

import system.Menu_Type;
import system.menu.Menu;

public class Market implements Menu{
	public static Menu_Type menuType;
	
	public void init() {
		User user = User.getInstance();
		Product product = Product.getInstance();
		
		String[] ids = {"admin", "empl1", "empl2"};
		String[] pws = {"1234", "1q2w3e", "asd1234"};
		String[] names = {"담배", "맥주", "우유", "삼각김밥", "컵라면", 
				"과자", "빵", "캔커피", "아이스크림", "콜라"};
		int[] prices = {4500, 2100, 3200, 1400, 1600, 
				1200, 2500, 1300, 1000, 1900};
		int[] quantitys = {12, 7, 6, 2, 4,
				7, 5, 4, 5, 6};
		int[] expireDates = {0, 86400 * 4, 43200, 18000, 0,		// 1 => 1초; 86400 = 60 * 60 * 24(24시간);
				0, 86400, 70, 86400 * 7, 72000};
		boolean[] isAdult = {true, true, false, false, false,
				false, false, false, false, false};
		
		for(int i = 0; i < ids.length; i++) {
			user.sign(ids[i], pws[i]);
		}
		
		for(int i = 0; i < names.length; i++) {
			product.addProduct(Product.products(), new Product_Data(names[i], prices[i], 
					quantitys[i], expireDates[i], isAdult[i]));
		}
		
		showMenu();
	}
}
