package cn.hjycjc.spring.spring_hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hjycjc.spring.spring_hibernate.dao.BookShopDao;

@Service
public class BookShopServiceImpl implements BookShopService {
	
	@Autowired
	private BookShopDao bookShopDao;
	@Override
	public void purchase(String userName, String isbn) {
		
	int price=	bookShopDao.findBookPriceByIsbn(isbn);
	
	bookShopDao.updateBookStock(isbn);
	
	bookShopDao.updateUserAccount(userName, price);
	}
	
}
