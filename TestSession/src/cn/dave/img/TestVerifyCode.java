package cn.dave.img;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestVerifyCode {
	public static void main(String[] args){
		
		VerifyCode verifyCode = new VerifyCode();
		BufferedImage image = verifyCode.getImage();
		System.out.println(verifyCode.getText());
		try {
			VerifyCode.output(image,new FileOutputStream("f:/haha.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
