package MainApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Seats {
	private int rows = 5;
	private int cols = 9;
	private char[][] seats = new char[rows][cols];
	
	private void initializeSeats(String movieID){
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				seats[i][j] = 'o';
			}
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:/Temp/reservations.txt"));
			String line;
						
			while((line=br.readLine())!= null) {
				String[] seatsData = line.split(",");
				if(seatsData[1].equals(movieID)) {
					int rows = seatsData[3].charAt(0)-'A';
					int cols = Character.getNumericValue(seatsData[3].charAt(2))-1;
					seats[rows][cols] = 'x';
				}
			}
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void seatView(String movieID) {
		initializeSeats(movieID);
		System.out.println("-----------------------");
		System.out.println("        SCREEN         ");
		System.out.println("-----------------------");
		for(int i = 0; i < rows; i++) {
			System.out.print((char)('A'+ i) + " ");
			for(int j = 0; j < cols; j++) {
				System.out.print(seats[i][j] + " ");
			}
			System.out.println();
		}
		System.out.print("  ");
		for (int i = 1; i <= cols; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
