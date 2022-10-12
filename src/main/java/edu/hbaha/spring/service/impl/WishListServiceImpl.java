package edu.hbaha.spring.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import edu.hbaha.spring.models.CartItem;
import edu.hbaha.spring.service.ShoppingCartService;
import edu.hbaha.spring.service.WishListService;

@Service
@SessionScope
public class WishListServiceImpl implements WishListService {
	private Map<Integer, CartItem> map = new HashMap<Integer, CartItem>();

	@Override
	public void add(CartItem item) {
		CartItem existedItem = map.get(item.getProductID());
		if (existedItem == null) {
			map.put(item.getProductID(), item);
		}
	}

	@Override
	public void remove(Integer productID) {
		map.remove(productID);
	}

	@Override
	public Collection<CartItem> getCartItem() {
		return map.values();
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public int getCount() {
		if (map.isEmpty()) {
			return 0;
		}
		return map.values().size();
	}
}
