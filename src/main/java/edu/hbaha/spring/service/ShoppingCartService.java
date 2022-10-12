package edu.hbaha.spring.service;

import java.math.BigDecimal;
import java.util.Collection;

import edu.hbaha.spring.models.CartItem;

public interface ShoppingCartService {

	int getCount();

	BigDecimal getAmount();

	void update(Integer productID, int quantity);

	void clear();

	Collection<CartItem> getCartItem();

	void remove(Integer productID);

	void add(CartItem item);

}
