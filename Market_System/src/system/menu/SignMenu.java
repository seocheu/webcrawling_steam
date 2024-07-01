package system.menu;

import java.util.Date;

import system.Menu_Type;
import system.Scannable;
import system.Showable;
import system.function.Sign;
import system.market.Market;
import system.market.User;

public interface SignMenu extends Sign, UserMenu{
	
	default void logInMenu() {
		boolean isRun = true;
		System.out.println("마켓 시스템");
		do {	
			Market.menuType = Menu_Type.SIGN;
			Showable.printLine("로그인");
			System.out.print("사용자 이름: ");
			String id = Scannable.scan();
			System.out.print("비밀번호: ");
			String pw = Scannable.scan();
		
			if(check(User.users(), id, pw)) {	// id와 pw가 모두 같으면 true
				Showable.printLine();
				Date date = new Date();
				Showable.printDate(date);
				System.out.println(id + "님 환영합니다.");
				User.getInstance().setUser();
				User.getUser().setWorkDate(date);
				userMenu();
			} else {
				System.out.println("아이디 또는 비밀번호가 잘못됐습니다.");
			}
		} while(isRun);
	}
}
