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

import edu.hbaha.spring.domain.Tb_Brand;

@Service
public interface BrandService {

	<S extends Tb_Brand> List<S> findAll(Example<S> example, Sort sort);

	<S extends Tb_Brand> List<S> findAll(Example<S> example);

	Tb_Brand getReferenceById(Integer id);

	Tb_Brand getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Tb_Brand> entities);

	Tb_Brand getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Tb_Brand, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Tb_Brand entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Tb_Brand> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Tb_Brand> entities);

	<S extends Tb_Brand> long count(Example<S> example);

	void deleteInBatch(Iterable<Tb_Brand> entities);

	<S extends Tb_Brand> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Tb_Brand> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tb_Brand> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Tb_Brand> List<S> saveAll(Iterable<S> entities);

	Optional<Tb_Brand> findById(Integer id);

	List<Tb_Brand> findAllById(Iterable<Integer> ids);

	List<Tb_Brand> findAll(Sort sort);

	Page<Tb_Brand> findAll(Pageable pageable);

	List<Tb_Brand> findAll();

	<S extends Tb_Brand> Optional<S> findOne(Example<S> example);

	<S extends Tb_Brand> S save(S entity);

	Page<Tb_Brand> findByNameContaining(String name, Pageable pageable);

	List<Tb_Brand> findByNameContaining(String name);

}
