package edu.hbaha.spring.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import edu.hbaha.spring.models.CartItem;
import edu.hbaha.spring.service.ShoppingCartService;

@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService {
	private Map<Integer, CartItem> map = new HashMap<Integer, CartItem>();

	@Override
	public void add(CartItem item) {
		CartItem existedItem = map.get(item.getProductID());
		if (existedItem != null) {
			existedItem.setQuantity(item.getQuantity() + existedItem.getQuantity());
		} else {
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
	public void update(Integer productID, int quantity) {
		CartItem item = map.get(productID);
		item.setQuantity(quantity);
		if (item.getQuantity() <= 0) {
			map.remove(productID);
		}
	}

	@Override
	public BigDecimal getAmount() {
		BigDecimal sum = BigDecimal.ZERO;
		for (CartItem item : map.values()) {
			if (item.getPromotionPrice() != null) {
				sum = sum.add(item.getPromotionPrice().multiply(new BigDecimal(item.getQuantity())));
			} else {
				sum = sum.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
			}
		}
		return sum;
	}

	@Override
	public int getCount() {
		if (map.isEmpty()) {
			return 0;
		}
		return map.values().size();
	}
}
