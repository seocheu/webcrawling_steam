package system.function;

import system.Checkable;
import system.Data;
import system.DataManager;
import system.market.User_Data;

public interface Sign extends DataManager{
	default void signUp(User_Data[] users, String id, String pw) {
		if(!Checkable.checkName(users, id)) {
			addUser(new User_Data(id, pw));
		}
	}
	
	default boolean check(User_Data[] users, String id, String pw) {
		if(Checkable.checkName(users, id)) {
			if(users[Data.getIndex()].getPw().equals(pw)) {
				return true;
			}
		}
		return false;
	}
}
