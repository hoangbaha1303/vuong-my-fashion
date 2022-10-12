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

import edu.hbaha.spring.domain.Tb_Product;

@Service
public interface ProductService {

	<S extends Tb_Product> List<S> findAll(Example<S> example, Sort sort);

	<S extends Tb_Product> List<S> findAll(Example<S> example);

	Tb_Product getReferenceById(Integer id);

	Tb_Product getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Tb_Product> entities);

	Tb_Product getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Tb_Product, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Tb_Product entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Tb_Product> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Tb_Product> entities);

	<S extends Tb_Product> long count(Example<S> example);

	void deleteInBatch(Iterable<Tb_Product> entities);

	<S extends Tb_Product> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Tb_Product> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tb_Product> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Tb_Product> List<S> saveAll(Iterable<S> entities);

	Optional<Tb_Product> findById(Integer id);

	List<Tb_Product> findAllById(Iterable<Integer> ids);

	List<Tb_Product> findAll(Sort sort);

	Page<Tb_Product> findAll(Pageable pageable);

	List<Tb_Product> findAll();

	<S extends Tb_Product> Optional<S> findOne(Example<S> example);

	<S extends Tb_Product> S save(S entity);

	Page<Tb_Product> findByNameContaining(String name, Pageable pageable);

	List<Tb_Product> findByNameContaining(String name);

	List<Tb_Product> findByCateID(Integer cateID);

	List<Tb_Product> findTop7ByOrderByCreateDateDesc();

	List<Tb_Product> findTop8ByOrderByViewCountDesc();

	List<Tb_Product> findTop4ByHotAfterOrderByHotAsc(Date date);

}
