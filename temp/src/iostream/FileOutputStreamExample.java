package iostream;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileOutputStreamExample {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String originalFileName = "C:/Gyujin/cake.jpg";
		String targetFileName = "C:/Temp/cake.jpg";
		
		FileInputStream fis = new FileInputStream(originalFileName);
		FileOutputStream fos = new FileOutputStream(targetFileName);
		
		int readByteNo;
		byte[] readBytes = new byte[100];
		while((readByteNo=fis.read(readBytes))!=-1) {
			fos.write(readBytes,0,readByteNo);
		}
		fos.flush();
		fos.close();
		fis.close();
		System.out.println("복사가 잘 되었습니다.");
	}

}
