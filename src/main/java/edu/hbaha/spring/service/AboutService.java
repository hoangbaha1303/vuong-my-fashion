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

import edu.hbaha.spring.domain.Tb_About;

@Service
public interface AboutService {

	<S extends Tb_About> List<S> findAll(Example<S> example, Sort sort);

	<S extends Tb_About> List<S> findAll(Example<S> example);

	Tb_About getReferenceById(Integer id);

	Tb_About getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Tb_About> entities);

	Tb_About getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Tb_About, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Tb_About entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Tb_About> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Tb_About> entities);

	<S extends Tb_About> long count(Example<S> example);

	void deleteInBatch(Iterable<Tb_About> entities);

	<S extends Tb_About> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Tb_About> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tb_About> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Tb_About> List<S> saveAll(Iterable<S> entities);

	Optional<Tb_About> findById(Integer id);

	List<Tb_About> findAllById(Iterable<Integer> ids);

	List<Tb_About> findAll(Sort sort);

	Page<Tb_About> findAll(Pageable pageable);

	List<Tb_About> findAll();

	<S extends Tb_About> Optional<S> findOne(Example<S> example);

	<S extends Tb_About> S save(S entity);

}
