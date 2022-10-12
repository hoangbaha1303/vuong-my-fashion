package edu.hbaha.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.hbaha.spring.domain.Tb_Order;

@Service
public interface OrderService {

	<S extends Tb_Order> List<S> findAll(Example<S> example, Sort sort);

	<S extends Tb_Order> List<S> findAll(Example<S> example);

	Tb_Order getReferenceById(Integer id);

	Tb_Order getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Tb_Order> entities);

	Tb_Order getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Tb_Order, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Tb_Order entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Tb_Order> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Tb_Order> entities);

	<S extends Tb_Order> long count(Example<S> example);

	void deleteInBatch(Iterable<Tb_Order> entities);

	<S extends Tb_Order> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Tb_Order> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tb_Order> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Tb_Order> List<S> saveAll(Iterable<S> entities);

	Optional<Tb_Order> findById(Integer id);

	List<Tb_Order> findAllById(Iterable<Integer> ids);

	List<Tb_Order> findAll(Sort sort);

	Page<Tb_Order> findAll(Pageable pageable);

	List<Tb_Order> findAll();

	<S extends Tb_Order> Optional<S> findOne(Example<S> example);

	<S extends Tb_Order> S save(S entity);

	List<Tb_Order> findByCustomerID(Integer customerID);

	List<Tb_Order> findByCustomerIDAndOrderDateBetween(Integer customerID, Date starDate, Date endDate);

	List<Tb_Order> findByOrderDateBetween(Date starDate, Date endDate);


}
