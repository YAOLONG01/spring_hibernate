package cn.hjycjc.spring.spring_hibernate;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hjycjc.spring.spring_hibernate.service.BookShopService;
import javassist.expr.NewArray;
import sun.print.resources.serviceui;

public class TestApp {
	private	ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private BookShopService bookShopService = ctx.getBean(BookShopService.class);
	/*
	 * 测试能否连接数据库和数据源
	 */
	@Test
	public void test() throws SQLException{
		DataSource dataSource = (DataSource) ctx.getBean("dataSource");
		
		System.out.println(dataSource.getConnection());

		
	}
	/*
	 * 测试Spring整合Hibernate是否成功
	 */
	@Test
	public void testBookShopService(){
		bookShopService.purchase("AA", "1001");
		
		
	}

}
