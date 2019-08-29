package cn.dave.base64;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

import org.junit.Test;

public class TestBase64 {

	/**
	 * 
	* @Title: test 
	* @Description: 测试BASC64Encoder编码 
	* @param @throws UnsupportedEncodingException
	* @return void
	* @throws
	 */
	@Test
	public void test() throws UnsupportedEncodingException {
		
		String s= "Username";
		BASE64Encoder encoder = new BASE64Encoder();
		s = encoder.encode(s.getBytes("UTF-8"));
		System.out.println(s);
	}
	
	@Test
	public void test2() throws IOException{
		String s ="VXNlcm5hbWU=";
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] a = decoder.decodeBuffer(s);
		System.out.println(new String(a,"UTF-8"));
	}
}
