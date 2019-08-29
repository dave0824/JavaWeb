package cn.dave.domain;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class demo {

	@Test
	public void test() {
		/**
		 * 我们现在写两个配置文件，一个文件中存放中文信息，另一个存放英文信息
		 * 我们使用一个类，来通过语言环境，最终识别加载哪一个文件的信息：ResourceBundle
		 * 什么语言环境：Locale
		 *   > zh_CN
		 *   > en_US
		 *   new Locale("zh", "CN");
		 *   Locale.getDefault();
		 *   Locale.US
		 * @author cxf
		 *
		 * 资源文件名称的格式：基本名称+Locale部份+.properties
		 * 例如：res_zh_CN.properties，其中基本名称为res，而zh_CN是Locale部份
		 * 必须所有的资源文件基石名称要相同！不同之处就是Locale部件
		 */
		Locale locale = Locale.US;
		// 得到ResourceBundle
		// 第一个参数是：基本名称
		// 第二个参数是：Locale
		ResourceBundle rb = ResourceBundle.getBundle("res",locale);
		// 使用ResourceBundle来获取资源信息
		System.out.println(rb.getString("username"));
		System.out.println(rb.getString("password"));
		System.out.println(rb.getString("login"));
	}

}
