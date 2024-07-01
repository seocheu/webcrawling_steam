package system.market;

public class Balance {
	private static Balance data = new Balance();
	private Balance() { }
	public static Balance getInstance() {
		return data;
	}
	
	public static final int SALARY = 9800;
	private int oldBalance = 390500;
	private int newBalance = oldBalance;

	public int getOldBalance() {
		return oldBalance;
	}
	
	public void setOldBalance(int oldBalance) {
		this.oldBalance = oldBalance;
	}

	public int getNewBalance() {
		return newBalance;
	}

	public void addNewBalance(int newBalance) {
		this.newBalance += newBalance;
	}
	
	public void subNewBalance(int newBalance) {
		this.newBalance -= newBalance;
	}
}
