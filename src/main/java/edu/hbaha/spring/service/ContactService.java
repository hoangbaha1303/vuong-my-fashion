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

import edu.hbaha.spring.domain.Tb_Contact;

@Service
public interface ContactService {

	<S extends Tb_Contact> List<S> findAll(Example<S> example, Sort sort);

	<S extends Tb_Contact> List<S> findAll(Example<S> example);

	Tb_Contact getReferenceById(Integer id);

	Tb_Contact getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Tb_Contact> entities);

	Tb_Contact getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Tb_Contact, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Tb_Contact entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Tb_Contact> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Tb_Contact> entities);

	<S extends Tb_Contact> long count(Example<S> example);

	void deleteInBatch(Iterable<Tb_Contact> entities);

	<S extends Tb_Contact> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Tb_Contact> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tb_Contact> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Tb_Contact> List<S> saveAll(Iterable<S> entities);

	Optional<Tb_Contact> findById(Integer id);

	List<Tb_Contact> findAllById(Iterable<Integer> ids);

	List<Tb_Contact> findAll(Sort sort);

	Page<Tb_Contact> findAll(Pageable pageable);

	List<Tb_Contact> findAll();

	<S extends Tb_Contact> Optional<S> findOne(Example<S> example);

	<S extends Tb_Contact> S save(S entity);

}
