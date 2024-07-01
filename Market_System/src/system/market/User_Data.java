package system.market;

import java.util.Date;

public class User_Data {
	private String id;
	private String pw;
	
	private long workTime;
	private Date workDate;
	
	private int salary;
	
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public User_Data(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	
	public long getStartWorkTime() {
		return workTime;
	}

	public void setStartWorkTime(long startWorkTime) {
		this.workTime = startWorkTime;
	}
	
	public Date getWorkTime() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	
}
