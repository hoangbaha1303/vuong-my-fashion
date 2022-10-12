package edu.hbaha.spring.service.impl;

import java.util.Date;
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

import edu.hbaha.spring.domain.Tb_Order;
import edu.hbaha.spring.repository.OrderRepository;
import edu.hbaha.spring.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderRepository orderRepository;

	@Override
	public <S extends Tb_Order> S save(S entity) {
		return orderRepository.save(entity);
	}
	
	@Override
	public List<Tb_Order> findByCustomerID(Integer customerID) {
		return orderRepository.findByCustomerID(customerID);
	}

	@Override
	public <S extends Tb_Order> Optional<S> findOne(Example<S> example) {
		return orderRepository.findOne(example);
	}
	
	@Override
	public List<Tb_Order> findByOrderDateBetween(Date starDate, Date endDate) {
		return orderRepository.findByOrderDateBetween(starDate, endDate);
	}

	@Override
	public List<Tb_Order> findByCustomerIDAndOrderDateBetween(Integer customerID, Date starDate, Date endDate) {
		return orderRepository.findByCustomerIDAndOrderDateBetween(customerID, starDate, endDate);
	}

	@Override
	public List<Tb_Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Page<Tb_Order> findAll(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	@Override
	public List<Tb_Order> findAll(Sort sort) {
		return orderRepository.findAll(sort);
	}

	@Override
	public List<Tb_Order> findAllById(Iterable<Integer> ids) {
		return orderRepository.findAllById(ids);
	}

	@Override
	public Optional<Tb_Order> findById(Integer id) {
		return orderRepository.findById(id);
	}

	@Override
	public <S extends Tb_Order> List<S> saveAll(Iterable<S> entities) {
		return orderRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		orderRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return orderRepository.existsById(id);
	}

	@Override
	public <S extends Tb_Order> S saveAndFlush(S entity) {
		return orderRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Tb_Order> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Tb_Order> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Tb_Order> entities) {
		orderRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Tb_Order> long count(Example<S> example) {
		return orderRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Tb_Order> entities) {
		orderRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return orderRepository.count();
	}

	@Override
	public <S extends Tb_Order> boolean exists(Example<S> example) {
		return orderRepository.exists(example);
	}

	@Override
	public void deleteById(Integer id) {
		orderRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		orderRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Tb_Order entity) {
		orderRepository.delete(entity);
	}

	@Override
	public <S extends Tb_Order, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return orderRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		orderRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		orderRepository.deleteAllInBatch();
	}

	@Override
	public Tb_Order getOne(Integer id) {
		return orderRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Tb_Order> entities) {
		orderRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		orderRepository.deleteAll();
	}

	@Override
	public Tb_Order getById(Integer id) {
		return orderRepository.getById(id);
	}

	@Override
	public Tb_Order getReferenceById(Integer id) {
		return orderRepository.getReferenceById(id);
	}

	@Override
	public <S extends Tb_Order> List<S> findAll(Example<S> example) {
		return orderRepository.findAll(example);
	}

	@Override
	public <S extends Tb_Order> List<S> findAll(Example<S> example, Sort sort) {
		return orderRepository.findAll(example, sort);
	}
	
	
}
