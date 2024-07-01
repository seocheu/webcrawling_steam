package system;

import system.market.Product;
import system.market.Product_Data;
import system.market.User;
import system.market.User_Data;

public interface DataManager {
	
	static Object[] addObj(Object[] objs, Object obj) {
		int size = Checkable.checkSize(objs);
		Object[] newObjs;
		
		// obj가 User_Data 타입인가? 맞으면 User_Data로 배열 생성 
		// 아니면 Product_Data로 배열 생성
		if(obj instanceof User_Data)
			newObjs = new User_Data[size + 1];
		else
			newObjs = new Product_Data[size + 1];
		
		// 신규 배열에 기존 데이터 입력
		for(int i = 0; i < size; i++) {
			newObjs[i] = objs[i];
		}
		// 새로운 데이터 추가
		newObjs[size] = obj;
		
		return newObjs;
	}
	
	default void addUser(User_Data ud) {
		User.reDataUsers((User_Data[])DataManager.addObj(User.users(), ud));
	}
	
	default void putIn(Product_Data[] pds, Product_Data pd) {
		if (Checkable.checkName(pds, pd.getName())) {
			addQuantity(pds, pd.getQuantity());
		} else {
			Product.reDataProducts((Product_Data[])addObj(pds, pd));
		}
	}
	
	default void putOut(Product_Data[] pds, int value) {
		subQuantity(pds, value);
		int index = Data.getIndex();
		if(Checkable.checkName(Product.sales(), pds[index].getName())) {
			Product.getInstance().addQuan(Product.sales(), value);
		}
		else {
			Checkable.checkStale(pds[index]);
			Object[] obj = addObj(Product.sales(), new Product_Data(pds[index].getName(), 
					pds[index].getPrice(), value, Checkable.checkStale(pds[index]) ? 1 :(Long.sum(pds[index].getExpireTime(),
							-System.currentTimeMillis()) / 1000), pds[index].isAdult()));
			Product.reDataSales((Product_Data[])obj);
		}
	}
	
	default void addQuantity(Product_Data[] pds, int value) {
		pds[Data.getIndex()].addQuantity(value);
	}
	
	default void subQuantity(Product_Data[] pds, int value) {
		pds[Data.getIndex()].subQuantity(value);
	}
	
}
