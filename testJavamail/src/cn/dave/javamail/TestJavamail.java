package cn.dave.javamail;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;

import cn.itcast.mail.AttachBean;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

public class TestJavamail {

	@Test
	public void test() throws Exception {
		/*
		 * 1.得到session
		 */
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		
		Authenticator auth = new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("dave_0824", "dave763081703");
			}
		};
		Session session = Session.getInstance(props,auth);
		
		/*
		 * 创建MimeMessage
		 */
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("dave_0824@163.com"));//设置发件人
		msg.setRecipients(RecipientType.TO, "1109682530@qq.com");//设置收件人
		//msg.setRecipients(RecipientType.CC, "763081703@qq送.com");//设置抄
		//msg.setRecipients(RecipientType.BCC, "763081703@qq.com");//设置暗送
		
		msg.setSubject("这是一封来自帅哥的邮件");
		msg.setContent("我是大帅哥!","text/html;charset=UTF-8");
		
		/*
		 * 3.发送
		 */
		for(int i=0;i<200;i++){
			Transport.send(msg);
		}
		//Transport.send(msg);
	}
	

	@Test
	public void test2() throws Exception{
		Properties prop = new Properties();
		prop.setProperty("mail.host", "smtp.163.com");
		prop.setProperty("mail.smtp.auth", "true");
		
		Authenticator auth = new Authenticator(){
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("dave_0824","dave763081703");
			}
		};
		
		Session session = Session.getInstance(prop,auth);
		
		/*
		 * 2.创建MimeMessage
		 */
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("dave_0824@163.com"));//设置发件人
		msg.setRecipients(RecipientType.TO, "763081703@qq.com");//设置收件人
		msg.setRecipients(RecipientType.CC, "dave_0824@163.com");//设置抄
		
		msg.setSubject("这是来自dave大帅哥的测试邮件有附件");
		
		/*********************这部分是添加附件与格式与上面测试的区别*************************/
		
		/*
		 * 当发送包含附件的邮件时，邮件体就为多部件形式！
		 * 1. 创建一个多部件的部件内容！MimeMultipart
		 *   MimeMultipart就是一个集合，用来装载多个主体部件！
		 * 2. 我们需要创建两个主体部件，一个是文本内容的，另一个是附件的。
		 *   主体部件叫MimeBodyPart
		 * 3. 把MimeMultipart设置给MimeMessage的内容！
		 */
		MimeMultipart list = new MimeMultipart();
		
		//创建MimeBodyPart
		MimeBodyPart part1 = new MimeBodyPart();
		//设置主体的内容
		part1.setContent("我是大美女！！","text/html;charset=utf-8");
		list.addBodyPart(part1);
		
		//创建MimeBodyPart
		MimeBodyPart part2 = new MimeBodyPart();
		//设置附件的内容
		part2.attachFile(new File("f:/大美女.jpg"));
		//设置显示的文件名称，其中encodeText用来处理中文乱码问题
		part2.setFileName(MimeUtility.encodeText("大美女.jpg"));
		//把主题部件添加到集合
		list.addBodyPart(part2);
		msg.setContent(list);
		/**********************我是有范围的！！！！！！！！！！！！！*************************/
		
		Transport.send(msg);
	}
	
	@Test
	public void test3() throws Exception {
		/*
		 * 1. 得到session
		 */
		Session session = MailUtils.createSession("smtp.163.com", 
				"dave_0824", "dave763081703");
		/*
		 * 2. 创建邮件对象
		 */
		Mail mail = new Mail("dave_0824@163.com",
				"763081703@qq.com",
				"不是垃圾邮件能是什么呢？", "这里是正文");
		
		/*
		 * 创建两个附件对象
		 */
		AttachBean ab1 = new AttachBean(new File("f:/大美女.jpg"), "小美女.jpg");
		//AttachBean ab2 = new AttachBean(new File("F:/f/big.jpg"), "我的羽绒服.jpg");
		
		// 添加到mail中
		mail.addAttach(ab1);
		//mail.addAttach(ab2);
		
		/*
		 * 3. 发送
		 */
		MailUtils.send(session, mail);
	}

}
