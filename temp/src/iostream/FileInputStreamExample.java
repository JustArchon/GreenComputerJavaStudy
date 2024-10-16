package iostream;

import java.io.FileInputStream;

public class FileInputStreamExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fis = new FileInputStream("C:/Gyujin/temp/src/iostream/FileInputStreamExample.java");
			int data;
			while((data = fis.read())!= -1) {
				System.out.write(data);
			}
			fis.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
