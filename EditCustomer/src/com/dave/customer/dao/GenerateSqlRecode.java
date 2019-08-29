package com.dave.customer.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dave.customer.domain.Customer;

import cn.itcast.commons.CommonUtils;

/**
 * 
* <p>Title: GenerateSqlRecode</p>
* <p>Description:创建一堆客户记录 </p>
* <p>Company: </p> 
* @author dave
* @date 2019年4月17日
 */
public class GenerateSqlRecode {

	@Test
	public void test() {
		CustomerDao customerDao = new CustomerDao();
		for(int i=1;i<300;i++){
			Customer c = new Customer();
			
			c.setCid(CommonUtils.uuid());
			c.setCname("dave_" + i);
			c.setBirthday("1998-08-24");
			c.setGender(i%2==0?"男":"女");
			c.setCellphone("182" + i);
			c.setEmail("dave_" + i + "@163.com");
			c.setDescription("我是你爸爸");
			
			customerDao.add(c);
			
		}
	}

}
