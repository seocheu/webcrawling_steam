package system.market;

import system.DataManager;

public class Product implements DataManager{
	private static Product product = new Product();
	private Product() { }
	public static Product getInstance() {
		return product;
	}
	
	private static Product_Data[] products;
	public static Product_Data[] products() {
		return products;
	}
	public static void reDataProducts(Product_Data[] pd) {
		products = pd;
	}
	
	private static Product_Data[] sales;
	public static Product_Data[] sales() {
		return sales;
	}
	public static void reDataSales(Product_Data[] pds) {
		sales = pds;
	}
	
	public void addProduct(Product_Data[] pds, Product_Data pd) {
		putIn(pds, pd);
	}
	
	public void subProduct(Product_Data[] pds, int value) {
		putOut(pds, value);
	}
	
	public void addQuan(Product_Data[] pds, int value) {
		addQuantity(pds, value);
	}
	
	public void subQuan(Product_Data[] pds, int value) {
		subQuantity(pds, value);
	}
}
