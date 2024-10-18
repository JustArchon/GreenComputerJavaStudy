package MainApp;

public abstract class AbstractMenu implements Menu{
	// 메인메뉴
	public void movie_reservaiton() {}
	public void movie_reservation_check() {}
	public void movie_reservation_cancle() {}
	public void user_register() {}
	public boolean user_login(String id, String passwd) {
		return true;}
	public void admin_menu() {}
	
	// 관리자메뉴
	public void admin_movie_add() {}
	public void admin_movie_list() {}
	public void admin_movie_delete() {}
	
}
