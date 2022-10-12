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

import edu.hbaha.spring.domain.Tb_Supplier;

@Service
public interface SupplierService {

	<S extends Tb_Supplier> List<S> findAll(Example<S> example, Sort sort);

	<S extends Tb_Supplier> List<S> findAll(Example<S> example);

	Tb_Supplier getReferenceById(Integer id);

	Tb_Supplier getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Tb_Supplier> entities);

	Tb_Supplier getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Tb_Supplier, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Tb_Supplier entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Tb_Supplier> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Tb_Supplier> entities);

	<S extends Tb_Supplier> long count(Example<S> example);

	void deleteInBatch(Iterable<Tb_Supplier> entities);

	<S extends Tb_Supplier> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Tb_Supplier> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tb_Supplier> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Tb_Supplier> List<S> saveAll(Iterable<S> entities);

	Optional<Tb_Supplier> findById(Integer id);

	List<Tb_Supplier> findAllById(Iterable<Integer> ids);

	List<Tb_Supplier> findAll(Sort sort);

	Page<Tb_Supplier> findAll(Pageable pageable);

	List<Tb_Supplier> findAll();

	<S extends Tb_Supplier> Optional<S> findOne(Example<S> example);

	<S extends Tb_Supplier> S save(S entity);

	Page<Tb_Supplier> findByNameContaining(String name, Pageable pageable);

	List<Tb_Supplier> findByNameContaining(String name);

}
