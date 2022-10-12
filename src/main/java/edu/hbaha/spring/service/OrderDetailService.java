package edu.hbaha.spring.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.hbaha.spring.domain.Tb_OrderDetail;

@Service
public interface OrderDetailService {

	<S extends Tb_OrderDetail> List<S> findAll(Example<S> example, Sort sort);

	<S extends Tb_OrderDetail> List<S> findAll(Example<S> example);

	Tb_OrderDetail getReferenceById(Integer id);

	Tb_OrderDetail getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Tb_OrderDetail> entities);

	Tb_OrderDetail getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Tb_OrderDetail, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Tb_OrderDetail entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Tb_OrderDetail> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Tb_OrderDetail> entities);

	<S extends Tb_OrderDetail> long count(Example<S> example);

	void deleteInBatch(Iterable<Tb_OrderDetail> entities);

	<S extends Tb_OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Tb_OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tb_OrderDetail> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Tb_OrderDetail> List<S> saveAll(Iterable<S> entities);

	Optional<Tb_OrderDetail> findById(Integer id);

	List<Tb_OrderDetail> findAllById(Iterable<Integer> ids);

	List<Tb_OrderDetail> findAll(Sort sort);

	Page<Tb_OrderDetail> findAll(Pageable pageable);

	List<Tb_OrderDetail> findAll();

	<S extends Tb_OrderDetail> Optional<S> findOne(Example<S> example);

	<S extends Tb_OrderDetail> S save(S entity);

	BigDecimal getAmount(Integer id);

	List<Tb_OrderDetail> findByid_OrderID(Integer id);

}
