package system.market;

public class Product_Data {
	private String name;
	private int price;
	private int quantity = 0;	// 수량
	private long expireTime;
	private boolean isExpire;
	private boolean isAdult;
//	private boolean isStale = false;
//	
//	public boolean isStale() {
//		return isStale;
//	}
//
//	public void setStale(boolean isStale) {
//		this.isStale = isStale;
//	}

	public Product_Data(String name, int price, 
			int quantity, long expireTime, boolean isAdult) {
		
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		if(expireTime > 0) {
			isExpire = true;
		}
		this.expireTime = Long.sum(System.currentTimeMillis(), (long)expireTime * 1000);
		this.isAdult = isAdult;
	}

	public void addQuantity(int value) {
		this.quantity += value;
	}
	
	public void subQuantity(int value) {
		this.quantity -= value;
	}
	
	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public long getExpireTime() {
		return expireTime;
	}

	public boolean isExpire() {
		return isExpire;
	}

	public boolean isAdult() {
		return isAdult;
	}
}
