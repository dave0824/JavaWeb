/**
 * 
 */
package cn.dave.test;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.dave.service.TestTransaction;

/**
 * @author dave
 *
 */
public class TestZhuanZhang {

	@Test
	public void test() {
		TestTransaction tton = new TestTransaction();
		 tton.zhuanzhang("小红", "小华", 8888);
	}

}
