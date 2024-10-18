package MainApp;

public interface Menu {
	
	// 메인메뉴
	void movie_reservaiton();
	void movie_reservation_check();
	void movie_reservation_cancle();
	void user_register();
	boolean user_login(String id, String passwd);
	void admin_menu();
	
	// 관리자메뉴
	void admin_movie_add();
	void admin_movie_list();
	void admin_movie_delete();
}
