package cn.hjycjc.spring.spring_hibernate.dao.Impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hjycjc.spring.spring_hibernate.BookStockException;
import cn.hjycjc.spring.spring_hibernate.UserAccountException;
import cn.hjycjc.spring.spring_hibernate.dao.BookShopDao;
//实现使用Hibernate
@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//注意session的获取
	private Session getSession(){
//		返回和当前线程绑定的Session
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int findBookPriceByIsbn(String isbn) {
		String hql ="SELECT b.price FROM Book b WHERE b.isbn = ?";
		Query query= getSession().createQuery(hql).setString(0, isbn);
		return  (Integer)query.uniqueResult();
	}

	@Override
	public void updateBookStock(String isbn) {
		//验证
		String hql2="SELECT bs.stock FROM BookStock bs WHERE isbn=?";
		int stock =(int) getSession().createQuery(hql2).setString(0, isbn).uniqueResult();
		if (stock==0){
		throw  new BookStockException("库存不足"); 
		}
		String hql ="UPDATE BookStock bs SET bs.stock=bs.stock - 1 WHERE bs.isbn =?";
		getSession().createQuery(hql).setString(0, isbn).executeUpdate();
	
	}

	@Override
	public void updateUserAccount(String userName, int price) {
		String hql2="SELECT a.balance FROM Account a WHERE a.name=? ";
		int balance = (int) getSession().createQuery(hql2).setString(0, userName).uniqueResult();
		if(balance <price){
			throw new UserAccountException("余额不足");
		}
		String hql = "UPDATE Account a SET a.balance= a.balance-? WHERE a.name=?";
		getSession().createQuery(hql).setFloat(0, price).setString(1, userName).executeUpdate();
		
		
	}

}
