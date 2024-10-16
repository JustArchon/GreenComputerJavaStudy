package MainApp;

import java.util.Scanner;

public class MainMenu extends AbstractMenu{
	public void UserMenu() {
		Scanner sc = new Scanner(System.in);
		String input = "";
		boolean ProgramEnable = true;
		
		while(ProgramEnable) {
			System.out.println("=====================");
			System.out.println("    영화예매 프로그램	 ");
			System.out.println("=====================");
			System.out.println("1: 영화 예매하기");
			System.out.println("2: 예매 확인하기");
			System.out.println("3: 예매 취소하기");
			System.out.println("4: 관리자 메뉴로 이동");
			System.out.println("q: 종료");
			System.out.print("메뉴를 선택하세요:");
			input = sc.nextLine();
			
			switch(input) {
			case "1":
				movie_reservaiton();
				break;
			case "2":
				movie_reservation_check();
				break;
			case "3":
				movie_reservation_cancle();
				break;
			case "4":
				System.out.print("관리자 비밀번호를 입력하세요: ");
				String paswd = sc.nextLine();
				if(paswd.equals("admin1234")) {
					admin_menu();
				}else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
				break;
			case "q":
				System.out.println("프로그램을 종료합니다.");
				ProgramEnable = false;
				break;
			}
		}
	}
	@Override
	public void movie_reservaiton() {
		Reservation res = new Reservation();
		try {
			res.reservationSelect();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void movie_reservation_check() {
		Reservation res = new Reservation();
		try {
			res.inquiryReservation();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void movie_reservation_cancle() {
		Reservation res = new Reservation();
		try {
			res.reservationCancle();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void admin_menu() {
		AdminMenu admin = new AdminMenu();
		admin.adminMenu();
	}
}
