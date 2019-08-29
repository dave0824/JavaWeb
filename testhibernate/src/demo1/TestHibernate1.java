package demo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.dave.domain.Customer;
import utils.HibernateUtils;

public class TestHibernate1 {

	@Test
	public void test() {
		//1.加载hibernate核心配置文件
		Configuration configuration = new Configuration().configure();
		//2.创建一个sessionFactory对象
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		//3.通过sessionFactory得到session对象
		Session session = sessionFactory.openSession();
		
		//4.开启事务
		Transaction transaction = session.beginTransaction();
		//5.编写代码
		Customer customer = new Customer();
		customer.setCust_name("小木瓜");
		
		session.save(customer);
		
		//6.提交事务
		transaction.commit();
		//7.释放资源
		session.close();
	}

	@Test
	public void test2() {
		Session session = HibernateUtils.openSession();
		Transaction transaction =  session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setCust_name("小黄瓜");
		session.save(customer);
		
		transaction.commit();
		session.close();
	}
	
	@Test
	public void test3() {
		Session session = HibernateUtils.openSession();
		Transaction transaction =  session.beginTransaction();
		
		Customer customer = new Customer();
		
		/*
		 * get方法采用的是立即加载，即执行到这条代码后立即查询
		 * 返回的是对象本身
		 * 当查询不存在的时候，返回null
		 */
		/*customer = session.get(Customer.class,1l);
		System.out.println(customer);*/
		
		/*
		 * load方法采用的是延迟加载（lazy模加载）执行到这条语句是不发送代码，知道使用它时才会发送代码
		 * 查询返回的是代理对象，使用了javassist技术
		 * 查找一个不存在的对象时，返回ObjectNotFoundException
		 */
		customer = session.load(Customer.class, 1l);
		System.out.println(customer);
		
		transaction.commit();
		session.close();
	}
	
	@Test
	public void test4() {
		Session session = HibernateUtils.openSession();
		Transaction transaction =  session.beginTransaction();
		
		Customer customer = new Customer();
		/*
		 * 直接修改
		 */
		/*customer.setCust_name("小西瓜");
		customer.setCust_id(1l);
		session.update(customer);
		*/
		/**
		 * 先查再改
		 */
		customer = session.load(Customer.class, 1l);
		customer.setCust_name("小黄瓜");
		session.update(customer);
		
		transaction.commit();
		session.close();
	}
}
