package MainApp;

import java.util.Scanner;

public class MainMenu extends AbstractMenu{
	private String loginID = "guest";
	public void UserMenu() {
		Scanner sc = new Scanner(System.in);
		String input = "";
		boolean ProgramEnable = true;
		
		while(ProgramEnable) {
			System.out.println("=====================");
			System.out.println("    영화예매 프로그램	 ");
			System.out.println("=====================");
			System.out.println("현재 로그인 ID: " + loginID);
			System.out.println("1: 영화 예매하기");
			System.out.println("2: 예매 확인하기");
			System.out.println("3: 예매 취소하기");
			System.out.println("4: 관리자 메뉴로 이동");
			if(!loginID.equals("guest")) {
				System.out.println("5: 로그아웃");
			} else {
				System.out.println("5: 로그인");
				System.out.println("6: 회원 가입");
			}
			System.out.println("q: 종료");
			System.out.print("메뉴를 선택하세요:");
			input = sc.nextLine();
			
			switch(input) {
			case "1":
				if(!loginID.equals("guest")) {
				movie_reservaiton(loginID);
				} else {
					System.out.println("회원만 예매 가능합니다. 로그인 후 진행바랍니다.");
				}
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
			case "5":
				if(!loginID.equals("guest")) {
					loginID = "guest";
					System.out.println("정상적으로 로그아웃되었습니다.");
				} else {
					System.out.print("로그인할 아이디를 입력하세요: ");
					String id = sc.nextLine();
					System.out.print("비밀번호를 입력하세요: ");
					String passwd = sc.nextLine();
					if(user_login(id, passwd)) {
						loginID = id;
						System.out.println("환영합니다! " + loginID + "회원님!");
					}else {
						System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
					}
				}
				break;
			case "6":
				if(loginID.equals("guest"))
					user_register();
				break;
			case "q":
				System.out.println("프로그램을 종료합니다.");
				ProgramEnable = false;
				break;
			}
		}
	}
	public void movie_reservaiton(String loginID) {
		Reservation res = new Reservation();
		try {
			res.reservationSelect(loginID);
			
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
	public void user_register() {
		User usr = new User();
		try {
			usr.userRegister();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean user_login(String id, String passwd) {
		User usr = new User();
		try {
			return usr.userLogin(id,passwd);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void admin_menu() {
		AdminMenu admin = new AdminMenu();
		admin.adminMenu();
	}
}
