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

import edu.hbaha.spring.domain.Tb_FeedBack;

@Service
public interface FeedBackService {

	<S extends Tb_FeedBack> List<S> findAll(Example<S> example, Sort sort);

	<S extends Tb_FeedBack> List<S> findAll(Example<S> example);

	Tb_FeedBack getReferenceById(Integer id);

	Tb_FeedBack getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Tb_FeedBack> entities);

	Tb_FeedBack getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Tb_FeedBack, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Tb_FeedBack entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Tb_FeedBack> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Tb_FeedBack> entities);

	<S extends Tb_FeedBack> long count(Example<S> example);

	void deleteInBatch(Iterable<Tb_FeedBack> entities);

	<S extends Tb_FeedBack> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Tb_FeedBack> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tb_FeedBack> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Tb_FeedBack> List<S> saveAll(Iterable<S> entities);

	Optional<Tb_FeedBack> findById(Integer id);

	List<Tb_FeedBack> findAllById(Iterable<Integer> ids);

	List<Tb_FeedBack> findAll(Sort sort);

	Page<Tb_FeedBack> findAll(Pageable pageable);

	List<Tb_FeedBack> findAll();

	<S extends Tb_FeedBack> Optional<S> findOne(Example<S> example);

	<S extends Tb_FeedBack> S save(S entity);

	List<Tb_FeedBack> findByStatus(Boolean status);

}
