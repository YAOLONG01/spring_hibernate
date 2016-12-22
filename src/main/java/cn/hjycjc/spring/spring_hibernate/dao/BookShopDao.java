package cn.hjycjc.spring.spring_hibernate.dao;

public interface BookShopDao {
	
	//根据书的isbn获取书的单价
	public int findBookPriceByIsbn(String isbn);
		
	//根据书的isbn,使书的库存减 1，默认一次只购买1本
	public void updateBookStock(String isbn);
		
	//根据用户名更新用户的余额
	public void updateUserAccount(String userName, int price);

}
