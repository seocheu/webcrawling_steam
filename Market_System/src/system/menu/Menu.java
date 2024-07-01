package system.menu;

public interface Menu extends SignMenu{	
	
	default void showMenu() {
		logInMenu();
	}
}
