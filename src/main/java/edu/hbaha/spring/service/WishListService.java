package edu.hbaha.spring.service;

import java.math.BigDecimal;
import java.util.Collection;

import edu.hbaha.spring.models.CartItem;

public interface WishListService {

	int getCount();

	void clear();

	Collection<CartItem> getCartItem();

	void remove(Integer productID);

	void add(CartItem item);


}
