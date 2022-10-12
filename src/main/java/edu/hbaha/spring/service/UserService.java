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
import edu.hbaha.spring.domain.Tb_User;

@Service
public interface UserService {
	
	<S extends Tb_User> List<S> findAll(Example<S> example, Sort sort);

	<S extends Tb_User> List<S> findAll(Example<S> example);

	Tb_User getReferenceById(Integer id);

	Tb_User getById(Integer id);

	void deleteAll();

	void deleteAll(Iterable<? extends Tb_User> entities);

	Tb_User getOne(Integer id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Integer> ids);

	<S extends Tb_User, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Tb_User entity);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void deleteById(Integer id);

	<S extends Tb_User> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Tb_User> entities);

	<S extends Tb_User> long count(Example<S> example);

	void deleteInBatch(Iterable<Tb_User> entities);

	<S extends Tb_User> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Tb_User> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Tb_User> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	void flush();

	<S extends Tb_User> List<S> saveAll(Iterable<S> entities);

	Optional<Tb_User> findById(Integer id);

	List<Tb_User> findAllById(Iterable<Integer> ids);

	List<Tb_User> findAll(Sort sort);

	Page<Tb_User> findAll(Pageable pageable);

	List<Tb_User> findAll();

	<S extends Tb_User> Optional<S> findOne(Example<S> example);

	<S extends Tb_User> S save(S entity);

	Page<Tb_User> findByNameContaining(String name, Pageable pageable);

	List<Tb_User> findByNameContaining(String name);

	Tb_User login(String username, String password);

	Optional<Tb_User> findByUserName(String username);

	boolean isEqualPassword(String password, String password2);

}
