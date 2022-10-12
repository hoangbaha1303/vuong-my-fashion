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

import edu.hbaha.spring.domain.Tb_ProductComment;

@Service
public interface CommentService {

	<S extends Tb_ProductComment> List<S> findAll(Example<S> example, Sort sort);

	<S extends Tb_ProductComment> List<S> findAll(Example<S> example);

	Tb_ProductComment getReferenceById(Integer id);

	Tb_ProductComment getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Tb_ProductComment> entities);

	Tb_ProductComment getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Tb_ProductComment, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Tb_ProductComment entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Tb_ProductComment> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Tb_ProductComment> entities);

	<S extends Tb_ProductComment> long count(Example<S> example);

	void deleteInBatch(Iterable<Tb_ProductComment> entities);

	<S extends Tb_ProductComment> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Tb_ProductComment> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tb_ProductComment> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Tb_ProductComment> List<S> saveAll(Iterable<S> entities);

	Optional<Tb_ProductComment> findById(Integer id);

	List<Tb_ProductComment> findAllById(Iterable<Integer> ids);

	List<Tb_ProductComment> findAll(Sort sort);

	Page<Tb_ProductComment> findAll(Pageable pageable);

	List<Tb_ProductComment> findAll();

	<S extends Tb_ProductComment> Optional<S> findOne(Example<S> example);

	<S extends Tb_ProductComment> S save(S entity);

	List<Tb_ProductComment> findByProductID(Integer productID);

	double avgCommentVote(Integer productID);

	int countComment(Integer productID);

}
