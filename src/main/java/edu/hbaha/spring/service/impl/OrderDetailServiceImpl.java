package edu.hbaha.spring.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.hbaha.spring.domain.Tb_OrderDetail;
import edu.hbaha.spring.models.CartItem;
import edu.hbaha.spring.repository.OrderDetailRepository;
import edu.hbaha.spring.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Override
	public <S extends Tb_OrderDetail> S save(S entity) {
		return orderDetailRepository.save(entity);
	}

	@Override
	public <S extends Tb_OrderDetail> Optional<S> findOne(Example<S> example) {
		return orderDetailRepository.findOne(example);
	}
	
	@Override
	public List<Tb_OrderDetail> findByid_OrderID(Integer id) {
		return orderDetailRepository.findByid_OrderID(id);
	}

	
	
	@Override
	public BigDecimal getAmount(Integer id) {
		BigDecimal sum = BigDecimal.ZERO;
		
		for (Tb_OrderDetail item : orderDetailRepository.findByid_OrderID(id)) {
			sum = sum.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
		}
		return sum;
	}
	
	@Override
	public List<Tb_OrderDetail> findAll() {
		return orderDetailRepository.findAll();
	}

	@Override
	public Page<Tb_OrderDetail> findAll(Pageable pageable) {
		return orderDetailRepository.findAll(pageable);
	}

	@Override
	public List<Tb_OrderDetail> findAll(Sort sort) {
		return orderDetailRepository.findAll(sort);
	}

	@Override
	public List<Tb_OrderDetail> findAllById(Iterable<Integer> ids) {
		return orderDetailRepository.findAllById(ids);
	}

	@Override
	public Optional<Tb_OrderDetail> findById(Integer id) {
		return orderDetailRepository.findById(id);
	}

	@Override
	public <S extends Tb_OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return orderDetailRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		orderDetailRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return orderDetailRepository.existsById(id);
	}

	@Override
	public <S extends Tb_OrderDetail> S saveAndFlush(S entity) {
		return orderDetailRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Tb_OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderDetailRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Tb_OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderDetailRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Tb_OrderDetail> entities) {
		orderDetailRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Tb_OrderDetail> long count(Example<S> example) {
		return orderDetailRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tb_OrderDetail> entities) {
		orderDetailRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return orderDetailRepository.count();
	}

	@Override
	public <S extends Tb_OrderDetail> boolean exists(Example<S> example) {
		return orderDetailRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		orderDetailRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		orderDetailRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Tb_OrderDetail entity) {
		orderDetailRepository.delete(entity);
	}

	@Override
	public <S extends Tb_OrderDetail, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return orderDetailRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		orderDetailRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		orderDetailRepository.deleteAllInBatch();
	}

	@Override
	public Tb_OrderDetail getOne(Integer id) {
		return orderDetailRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Tb_OrderDetail> entities) {
		orderDetailRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		orderDetailRepository.deleteAll();
	}

	@Override
	public Tb_OrderDetail getById(Integer id) {
		return orderDetailRepository.getById(id);
	}

	@Override
	public Tb_OrderDetail getReferenceById(Integer id) {
		return orderDetailRepository.getReferenceById(id);
	}

	@Override
	public <S extends Tb_OrderDetail> List<S> findAll(Example<S> example) {
		return orderDetailRepository.findAll(example);
	}

	@Override
	public <S extends Tb_OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
		return orderDetailRepository.findAll(example, sort);
	}
	
	
}
