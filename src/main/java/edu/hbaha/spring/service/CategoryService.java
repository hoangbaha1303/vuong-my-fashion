package edu.hbaha.spring.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.hbaha.spring.domain.Tb_ProductCategory;

@Service
public interface CategoryService {

	<S extends Tb_ProductCategory> S save(S entity);

	Tb_ProductCategory getById(Integer id);

	Optional<Tb_ProductCategory> findById(Integer id);

	<S extends Tb_ProductCategory, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Page<Tb_ProductCategory> findAll(Pageable pageable);

	boolean existsById(Integer id);

	<S extends Tb_ProductCategory> boolean exists(Example<S> example);

	void deleteById(Integer id);

	void deleteAllInBatch();

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteAllInBatch(Iterable<Tb_ProductCategory> entities);

	<S extends Tb_ProductCategory> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tb_ProductCategory> S saveAndFlush(S entity);

	void flush();

	<S extends Tb_ProductCategory> List<S> saveAll(Iterable<S> entities);

	List<Tb_ProductCategory> findAllById(Iterable<Integer> ids);

	List<Tb_ProductCategory> findAll(Sort sort);

	List<Tb_ProductCategory> findAll();

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAll(Iterable<? extends Tb_ProductCategory> entities);

	void deleteAll();

	void delete(Tb_ProductCategory entity);

	long count();

	List<Tb_ProductCategory> findByNameContaining(String name);

	Page<Tb_ProductCategory> findByNameContaining(String name, Pageable pageable);

	List<Tb_ProductCategory> findByParrentID(Integer parrentID);

}
