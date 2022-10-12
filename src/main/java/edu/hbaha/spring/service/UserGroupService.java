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
import edu.hbaha.spring.domain.Tb_UserGroup;

@Service
public interface UserGroupService {

	<S extends Tb_UserGroup> List<S> findAll(Example<S> example, Sort sort);

	<S extends Tb_UserGroup> List<S> findAll(Example<S> example);

	Tb_UserGroup getReferenceById(Integer id);

	Tb_UserGroup getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Tb_UserGroup> entities);

	Tb_UserGroup getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Tb_UserGroup, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Tb_UserGroup entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Tb_UserGroup> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Tb_UserGroup> entities);

	<S extends Tb_UserGroup> long count(Example<S> example);

	void deleteInBatch(Iterable<Tb_UserGroup> entities);

	<S extends Tb_UserGroup> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Tb_UserGroup> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tb_UserGroup> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Tb_UserGroup> List<S> saveAll(Iterable<S> entities);

	Optional<Tb_UserGroup> findById(Integer id);

	List<Tb_UserGroup> findAllById(Iterable<Integer> ids);

	List<Tb_UserGroup> findAll(Sort sort);

	Page<Tb_UserGroup> findAll(Pageable pageable);

	List<Tb_UserGroup> findAll();

	<S extends Tb_UserGroup> Optional<S> findOne(Example<S> example);

	<S extends Tb_UserGroup> S save(S entity);

	Page<Tb_UserGroup> findByNameContaining(String name, Pageable pageable);

	List<Tb_UserGroup> findByNameContaining(String name);

}
