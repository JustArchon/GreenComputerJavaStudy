package MainApp;

import java.util.Scanner;

public class AdminMenu extends AbstractMenu{
	public void adminMenu() {
		Scanner sc = new Scanner(System.in);
		String input = "";
		boolean adminEnable = true;
		
		while(adminEnable) {
			System.out.println("=====================");
			System.out.println("      관리자 모드		 ");
			System.out.println("=====================");
			System.out.println("1: 영화 등록하기");
			System.out.println("2: 영화 목록보기");
			System.out.println("3: 영화 삭제하기");
			System.out.println("b: 메인 메뉴로 이동");
			System.out.print("메뉴를 선택하세요:");
			input = sc.nextLine();
			
			switch(input) {
			case "1":
				admin_movie_add();
				break;
			case "2":
				admin_movie_list();
				break;
			case "3":
				admin_movie_delete();
				break;
			case "b":
				adminEnable = false;
				break;
			}
		}
	}
	
	public void admin_movie_add() {
		Movie movie = new Movie();
		try {
			movie.adminMovieAdd();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void admin_movie_list() {
		Movie movie = new Movie();
		try {
			movie.adminMovieListView();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void admin_movie_delete() {
		Movie movie = new Movie();
		try {
			movie.adminMovieDelete();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
